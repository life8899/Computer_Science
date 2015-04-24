package textsum;

import com.aliasi.util.CollectionUtils;
import util.FileHandler;
import util.FileMode;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.StopTokenizerFactory;



public class TextSummarizer
{
	private static final int MAX_FREQUENCY_THRESHOLD = 1000;
	private static final String sentencesPath = "/Users/Nick/Developer/Computer_Science/Research/TextSum/sup/sentences.txt";
	private static final String stopListPath = "/Users/Nick/Developer/Computer_Science/Research/TextSum/sup/stopList.txt";
	private static final String dataPath = "/Users/Nick/Developer/Computer_Science/Research/TextSum/data/train/train_1/doc";

	public static void main(String[] args) throws IOException
	{
		System.out.println("Starting at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
		FileHandler documentsDirectoryFileHandler = new FileHandler(dataPath, FileMode.READ);
		List<Path> documentPaths = documentsDirectoryFileHandler.readPathsFromDirectory();
		FileHandler stopListFileHandler = new FileHandler(stopListPath, FileMode.READ);
		Set<String> stopSet = CollectionUtils.asSet("");
		stopSet.addAll(stopListFileHandler.readFileAsList().stream().collect(Collectors.toList()));
		FrequencyAnalyzer[] analyzers = new FrequencyAnalyzer[documentPaths.size()];
		HashMap<String, Integer> aggregateFrequencyMap = new HashMap<>();
		for (int i = 0; i < documentPaths.size(); i++) {
			FileHandler documentFileHandler = new FileHandler(documentPaths.get(i), FileMode.READ);
			String docString = documentFileHandler.readFileAsString();
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

		ArrayList<String> interestingSentences = new ArrayList<>();
		for (Path documentPath : documentPaths) {
			String docString = new FileHandler(documentPath.toFile(), FileMode.READ).readFileAsString();
			SentenceExtractor extractor = new SentenceExtractor(docString);
			ArrayList<String> sentences = extractor.extract();
			for (String frequentTerm : frequentTerms) {
				interestingSentences.addAll(sentences.stream().filter(sentence -> sentence.contains(frequentTerm)).collect(Collectors.toList()));
			}
		}


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


		CosineMatrix matrix = new CosineMatrix();
		for (String sentence : moreInterestingSentences) {
			matrix.addSentence(sentence.toLowerCase());
			String[] words = new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopSet).tokenizer(sentence.toCharArray(), 0, sentence.length()).tokenize();
			for (String word : words) {
				matrix.addWord(word.toLowerCase());
			}
		}

		matrix.buildMatrix();
		matrix.writeToFile();


		System.out.println("All Done at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
		FileHandler sentencesFile = new FileHandler(sentencesPath, FileMode.WRITE);
		sentencesFile.writeListToFile(moreInterestingSentences, true);
	}
}