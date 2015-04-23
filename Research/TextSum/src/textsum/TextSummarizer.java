package textsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.StopTokenizerFactory;
import com.aliasi.util.CollectionUtils;



public class TextSummarizer
{
	private static final int MAX_FREQUENCY_THRESHOLD = 1000;
	private static final String sentencesPath = "C:\\Users\\Nick\\Developer\\Computer_Science\\Research\\TextSum\\sup\\sentences.txt";
	private static final String stopListPath = "C:\\Users\\Nick\\Developer\\Computer_Science\\Research\\TextSum\\sup\\stopList.txt";
	private static final String dataPath = "C:\\Users\\Nick\\Developer\\Computer_Science\\Research\\Data\\Train\\D1401_TRAIN\\Documents_Text";

	public static void main(String[] args) throws IOException
	{
		System.out.println("Starting at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));

		ArrayList<Path> documentPaths = getDocumentPaths(dataPath);
		Set<String> stopSet = getStopSet(stopListPath);
		FrequencyAnalyzer[] analyzers = new FrequencyAnalyzer[documentPaths.size()];
		HashMap<String, Integer> aggregateFrequencyMap = new HashMap<String, Integer>();
		for (int i = 0; i < documentPaths.size(); i++) {
			String docString = readFile(documentPaths.get(i));
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


		Map<Integer, String> frequencyTree = new TreeMap<Integer, String>(Collections.reverseOrder());
		for (String key : aggregateFrequencyMap.keySet()) {
			frequencyTree.put(aggregateFrequencyMap.get(key), key);
		}

		int topToTake = 30;
		Object[] treeMapAsArray = frequencyTree.entrySet().toArray();
		ArrayList<String> frequentTerms = new ArrayList<String>(topToTake);
		ArrayList<Integer> frequencies = new ArrayList<Integer>(topToTake);
		for (int i = 0; i < topToTake; i++) {
			String entry = treeMapAsArray[i].toString();
			int frequency = Integer.parseInt(entry.split("=")[0]);
			String word = entry.split("=")[1];
			if (frequency > MAX_FREQUENCY_THRESHOLD) {
				topToTake++;
				continue;
			}
			frequentTerms.add(word);
			frequencies.add(frequency);
		}

		ArrayList<String> interestingSentences = new ArrayList<String>();
		for (int i = 0; i < documentPaths.size(); i++) {
			String docString = readFile(documentPaths.get(i));
			SentenceExtractor extractor = new SentenceExtractor(docString);
			ArrayList<String> sentences = extractor.extract();
			for (int j = 0; j < frequentTerms.size(); j++) {
				for (int k = 0; k < sentences.size(); k++) {
					if (sentences.get(k).contains(frequentTerms.get(j))) {
						interestingSentences.add(sentences.get(k));
					}
				}
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

		ArrayList<String> moreInterestingSentences = new ArrayList<String>();
		for (int i = 0; i < interestingSentences.size(); i++) {
			ArrayList<String> frequentInSentence = new ArrayList<String>();
			int containsCount = 0;
			for (int k = 0; k < frequentTerms.size(); k++) {
				if (interestingSentences.get(i).contains(frequentTerms.get(k))) {
					frequentInSentence.add(frequentTerms.get(k));
					containsCount++;
				}
			}
			if (containsCount >= avgContainsCount) {
				String str = interestingSentences.get(i);
				moreInterestingSentences.add(str);
			}
		}


		CosineMatrix matrix = new CosineMatrix();
		for (int i = 0; i < moreInterestingSentences.size(); i++) {
			matrix.addSentence(moreInterestingSentences.get(i).toLowerCase());
			String[] words = new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopSet).tokenizer(moreInterestingSentences.get(i).toCharArray(), 0, moreInterestingSentences.get(i).length()).tokenize();
			for (int j = 0; j < words.length; j++) {
				matrix.addWord(words[j].toLowerCase());
			}
		}

		matrix.buildMatrix();

		matrix.asString();


		System.out.println("All Done at " + new SimpleDateFormat("hh:mm:ss a").format(new Date()));
		if (true) return;

		File sentencesFile = initFile(sentencesPath);
		writeToFile(moreInterestingSentences, true, sentencesFile);
	}

	private static ArrayList<Path> getDocumentPaths(String pathToFile) throws IOException
	{
		ArrayList<Path> paths = new ArrayList<Path>();
		Object[] subFiles = Files.walk(Paths.get(pathToFile)).toArray();
		for (int i = 0; i < subFiles.length; i++) {
			Path p = (Path)subFiles[i];
			File f = p.toFile();
			if (f.isDirectory() || p.getFileName().toString().startsWith(".")) {
				continue;
			}
			paths.add(p);
		}
		return paths;
	}

	private static String readFile(Path path) throws IOException
	{
		try {
			String fullString = "";
			for (String line : Files.readAllLines(path, Charset.forName("UTF-8"))) {
				fullString += line + " ";
			}
			return fullString;
		} catch (Exception ex) {
			//pass -- Encoding Error
		}
		return null;
	}

	private static Set<String> getStopSet(String pathToFile) throws IOException
	{
		Set<String> stopSet = CollectionUtils.asSet("");
		List<String> stopWords = Files.readAllLines(Paths.get(pathToFile));
		for (String s : stopWords) {
			stopSet.add(s);
		}
		return stopSet;
	}

	private static File initFile(String filePath)
	{
		try {
			File sentencesFile = new File(filePath);
			if (sentencesFile.exists()) { // Check if file exists
				if (sentencesFile.delete()) { // Delete if so for a fresh file
					if (sentencesFile.createNewFile()) { // Create the fresh file
						return sentencesFile;
					} else { // Return null if the creation fails
						return null;
					}
				} else { // Return null if the deletion fails
					return null;
				}
			} else { // File does not exist
				if (sentencesFile.createNewFile()) { // Create the file
					return sentencesFile;
				} else { // Return null if creation fails
					return null;
				}
			}
		} catch (IOException ioEx) {
			System.out.println("Error: IO Error");
			System.out.println("Probable Cause: " + ioEx.getCause());
			System.out.println("Message: " + ioEx.getMessage());
		}
		return null; // Return null if something went wacky and no other cases were caught
	}

	private static void writeToFile(String str, File sentencesFile)
	{
		try {
			if (sentencesFile.exists() || sentencesFile.createNewFile()) {
				FileWriter fileWriter = new FileWriter(sentencesFile.getAbsolutePath(), true);
				fileWriter.write(str);
				fileWriter.close();
			}
		} catch (FileNotFoundException fileNotFoundEx) {
			System.out.println("Error: File Not Found");
			System.out.println(fileNotFoundEx.getMessage());
		} catch (IOException ioEx) {
			System.out.println("Error: IO Error");
			System.out.println(ioEx.getMessage());
		}
	}

	private static void writeToFile(List<String> strs, boolean separateByNewLine, File sentencesFile)
	{
		System.out.println("Strings to write: " + strs.size());
		int count = 1;
		for (String str : strs) {
			if (separateByNewLine)
				writeToFile(str + "\n\n", sentencesFile);
			else
				writeToFile(str, sentencesFile);
			if (count % 20 == 0 || (strs.size() - count < 50)) {
				System.out.println("Wrote " + count);
			}
			count++;
		}
	}
}