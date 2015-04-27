package textsum;

import com.aliasi.util.CollectionUtils;

import util.*;

import java.io.IOException;
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
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");
		List<Path> documentPaths = FileHandler.getPathsInDirectory(Paths.get(FilePath.DOCUMENTS_DIRECTORY));
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
		System.out.println("Build Aggregate Frequency Map");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");


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

		System.out.println("Found Frequent Terms");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");

		ArrayList<String> interestingSentences = new ArrayList<>();
		for (Path documentPath : documentPaths) {
				SentenceExtractor extractor = new SentenceExtractor(FileHandler.readFileAsString(documentPath));
				ArrayList <String> sentences = extractor.extract();
				for (String frequentTerm : frequentTerms) {
					interestingSentences.addAll(sentences.stream().filter(sentence -> sentence.contains(frequentTerm)).collect(Collectors.toList()));
			}
		}

		System.out.println("Found Interesting Sentences");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");

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

		System.out.println("Calculated Average Contains Count");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");

		ArrayList<String> moreInterestingSentences = new ArrayList<>();
		int count = 0;
		for (String interestingSentence : interestingSentences) {
			count++;
			int containsCount = 0;
			for (String frequentTerm : frequentTerms) {
				if (interestingSentence.contains(frequentTerm)) {
					containsCount++;
				}
			}
			if (containsCount >= avgContainsCount) {
				moreInterestingSentences.add(String.valueOf(count) + ") " + interestingSentence);
			}
		}

		System.out.println("Found More Interesting Sentences");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");


		CosineMatrix matrix = new CosineMatrix();
		for (String sentence : moreInterestingSentences) {
			matrix.addSentence(sentence.toLowerCase());
			String[] words = new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopSet).tokenizer(sentence.toCharArray(), 0, sentence.length()).tokenize();
			for (String word : words) {
				matrix.addWord(word.toLowerCase());
			}
		}

		System.out.println("Added All Word to Cosine Matrix");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");

		matrix.buildMatrix();

		System.out.println("Built Cosine Matrix");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");

		HashMap<SentencePoint, Double> similarities = matrix.calculateCosineSimilarities();

		System.out.println("Calculated All Cosine Similarities");
		System.out.println("Memory: " + StringHandler.addThousandsSeparator(Runtime.getRuntime().freeMemory()) + " / " + StringHandler.addThousandsSeparator(Runtime.getRuntime().totalMemory()) + "\n");

		/**List<String> strings = new ArrayList<>();
		similarities.keySet().stream().forEach(point -> strings.add(String.format("Sentence %12s = %.5f", point.toString(), similarities.get(point))));

		FileHandler.writeList(Paths.get(FilePath.COSINE_SIMILARITIES), strings);

		System.out.println("Wrote Similarities to File");*/

		LinkedHashMap<SentencePoint, Double> sortedSimilarities = new LinkedHashMap<>(similarities.size());
		similarities.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> sortedSimilarities.put(entry.getKey(), entry.getValue()));
		ArrayList<SentencePoint> sortedSentencePointsDesc = new ArrayList<>(sortedSimilarities.keySet());
		Collections.reverse(sortedSentencePointsDesc);
		ArrayList<Double> sortedSimsDesc = new ArrayList<>(sortedSimilarities.values());
		Collections.reverse(sortedSimsDesc);

		System.out.println("Sorted Similarities");

		SentencePoint p = sortedSentencePointsDesc.get(0);
		System.out.println(p.getX());
		System.out.println(p.getY());
		System.out.println(sortedSimsDesc.get(0));

		String[] sentences = matrix.getSentencesAtPoint(sortedSentencePointsDesc.get(0));
		System.out.println(sentences[0]);
		System.out.println(sentences[1]);


		/**List<String> new_strings = new ArrayList<>();
		//sortedSimilarities.keySet().stream().forEach(point -> new_strings.add(String.format("Sentence %12s = %.5f", point.toString(), sortedSimilarities.get(point))));

		FileHandler.writeList(Paths.get(FilePath.SORTED_SIMILARITIES), new_strings);

		System.out.println("Wrote Sorted Similarities to File");*/

		System.out.println("All Done at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
	}
}