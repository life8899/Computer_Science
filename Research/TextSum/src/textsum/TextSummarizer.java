package textsum;

import com.aliasi.util.CollectionUtils;

import util.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.StopTokenizerFactory;



public class TextSummarizer
{
	private static final int MAX_FREQUENCY_THRESHOLD = 1000;

	public static void main(String[] args) throws IOException
	{
		System.out.println("Starting at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));

		FileHandler.createDirectory(Paths.get(FilePath.OUT_DIRECTORY_4));
		List<Path> documentPaths = FileHandler.getPathsInDirectory(Paths.get(FilePath.DOCUMENTS_DIRECTORY_4));
		Set<String> stopSet = CollectionUtils.asSet("");
		stopSet.addAll(FileHandler.readFileAsList(Paths.get(FilePath.STOP_WORDS_LIST)));
		FrequencyAnalyzer[] analyzers = new FrequencyAnalyzer[documentPaths.size()];
		HashMap<String, Integer> aggregateFrequencyMap = new HashMap<>();
		for (int i = 0; i < documentPaths.size(); i++) {
			String docString = FileHandler.readFileAsString(documentPaths.get(i));
			analyzers[i] = new FrequencyAnalyzer(docString, stopSet);
			HashMap<String, Integer> freqTok = analyzers[i].getFrequentTokens();
			for (String key : freqTok.keySet()) {
				int value = freqTok.get(key);
				if (aggregateFrequencyMap.containsKey(key)) {
					aggregateFrequencyMap.put(key, freqTok.get(key) + aggregateFrequencyMap.get(key));
				} else {
					aggregateFrequencyMap.put(key, value);
				}
			}
		}
		System.out.println("Build Aggregate Frequency Map\n");


		Map<Integer, String> frequencyTree = new TreeMap<>(Collections.reverseOrder());
		for (String key : aggregateFrequencyMap.keySet()) {
			frequencyTree.put(aggregateFrequencyMap.get(key), key);
		}

		int topToTake = 30;
		Object[] treeMapAsArray = frequencyTree.entrySet().toArray();
		ArrayList<String> frequentTerms = new ArrayList<>(topToTake);
		for (int i = 0; i < topToTake; i++) {
			String entry = treeMapAsArray[i].toString();
			int frequency = Integer.parseInt(entry.split("=")[0]);
			String word = entry.split("=")[1];
			if (frequency > MAX_FREQUENCY_THRESHOLD) {
				topToTake++;
				continue;
			}
			frequentTerms.add(word);
		}

		System.out.println("Found Frequent Terms\n");

		ArrayList<String> interestingSentences = new ArrayList<>();
		for (Path documentPath : documentPaths) {
			SentenceExtractor extractor = new SentenceExtractor(FileHandler.readFileAsString(documentPath));
			ArrayList <String> sentences = extractor.extract();
			for (String frequentTerm : frequentTerms) {
				interestingSentences.addAll(sentences.stream().filter(sentence -> sentence.contains(frequentTerm)).collect(Collectors.toList()));
			}
		}

		System.out.println("Found Interesting Sentences\n");

		int sum = 0;
		int maxContainsCount = 0;
		for (String interestingSentence : interestingSentences) {
			int containsCount = 0;
			for (String term : frequentTerms) {
				if (interestingSentence.contains(term)) {
					containsCount++;
				}
			}
			if (containsCount > maxContainsCount) {
				maxContainsCount = containsCount;
			}
			sum += containsCount;
		}
		int avgContainsCount = (int)Math.round((double)sum / (double)interestingSentences.size());

		System.out.println("Calculated Average Contains Count\n");

		ArrayList<String> moreInterestingSentences = new ArrayList<>();
		for (String interestingSentence : interestingSentences) {
			int containsCount = 0;
			for (String frequentTerm : frequentTerms) {
				if (interestingSentence.contains(frequentTerm)) {
					containsCount++;
				}
			}
			if (containsCount >= avgContainsCount) {
				moreInterestingSentences.add(interestingSentence);
			}
		}

		System.out.println("Found More Interesting Sentences\n");


		CosineMatrix matrix = new CosineMatrix();
		for (String sentence : moreInterestingSentences) {
			matrix.addSentence(sentence.toLowerCase());
			String[] words = new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopSet).tokenizer(sentence.toCharArray(), 0, sentence.length()).tokenize();
			for (String word : words) {
				matrix.addWord(word.toLowerCase());
			}
		}

		System.out.println("Added All Word to Cosine Matrix\n");

		matrix.buildMatrix();

		System.out.println("Built Cosine Matrix\n");

		HashMap<SentencePoint, Double> similarities = matrix.calculateCosineSimilarities();

		System.out.println("Calculated All Cosine Similarities\n");

		LinkedHashMap<SentencePoint, Double> sortedSimilarities = new LinkedHashMap<>(similarities.size());
		similarities.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> sortedSimilarities.put(entry.getKey(), entry.getValue()));
		ArrayList<SentencePoint> sortedSentencePointsDesc = new ArrayList<>(sortedSimilarities.keySet());
		Collections.reverse(sortedSentencePointsDesc);
		ArrayList<Double> sortedSimsDesc = new ArrayList<>(sortedSimilarities.values());
		Collections.reverse(sortedSimsDesc);

		System.out.println("Sorted Similarities\n");

		String summary = "";
		int wordCount = 0;
		int counter = 0;
		while (wordCount < 250) {
			SentencePoint point = sortedSentencePointsDesc.get(counter);
			String[] sentences = matrix.getSentencesAtPoint(point);
			String sentence1 = sentences[0];
			String sentence2 = sentences[1];
			int sent1WordCount = sentence1.split(" ").length;
			int sent2WordCount = sentence2.split(" ").length;
			if (sent1WordCount < sent2WordCount) {
				wordCount += sent1WordCount;
				summary += sentence1;
			} else {
				wordCount += sent2WordCount;
				summary += sentence2;
			}
			counter++;
		}

		System.out.println("Created Summary\n");

		FileHandler.writeString(Paths.get(FilePath.OUT_DIRECTORY_4 + "summary.txt"), summary);

		System.out.println("Wrote Summary to File\n");

		List<Path> modelSummariesPath = FileHandler.getPathsInDirectory(Paths.get(FilePath.SUMMARIES_DIRECTORY_4));
		List<String> modelSummaries = new ArrayList<String>(modelSummariesPath.size());
		for (Path modelSummaryPath : modelSummariesPath) {
			modelSummaries.add(FileHandler.readFileAsString(modelSummaryPath));
		}

		System.out.println("Read Model Summaries\n");

		SummaryCompare[] compares = new SummaryCompare[modelSummaries.size()];
		int[] summarySimilarities = new int[modelSummaries.size()];
		for (int i = 0; i < modelSummaries.size(); i++) {
			compares[i] = new SummaryCompare(summary, modelSummaries.get(i));
			summarySimilarities[i] = compares[i].compare();
		}

		System.out.println("Calculate Summary Similarities\n");

		ArrayList<String> strings = new ArrayList<>(modelSummaries.size());
		for (int i = 0; i < modelSummaries.size(); i++) {
			strings.add("Model Summary " + (i+1) + " Similarity = " + summarySimilarities[i] + " / " + compares[i].getModelSummaryWordCount() + " Words");
		}

		FileHandler.writeList(Paths.get(FilePath.OUT_DIRECTORY_4 + "summarySimilarities.txt"), strings);

		System.out.println("Wrote Summary Similarities to File\n");

		System.out.println("All Done at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
	}
}