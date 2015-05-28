package textsum;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.StopTokenizerFactory;
import util.StringHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class FrequencyAnalyzer
{
	private List<String> wordsAfterStemming;
	private HashMap<String, Integer> frequencyMap;
	private HashMap<String, Integer> frequentTokens;
	private int averageFrequency;

	public FrequencyAnalyzer(String fullText, Set<String> stopWordSet)
	{
		this.wordsAfterStemming = Arrays.asList(new PorterStemmerTokenizerFactory(new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopWordSet)).tokenizer(fullText.toCharArray(), 0, fullText.length()).tokenize());
		this.frequencyMap = buildFrequencyMap();
		this.averageFrequency = calculateAverageFrequency();
		this.frequentTokens = findFrequentTokens();
	}

	public HashMap<String, Integer> getFrequentTokens()
	{
		return this.frequentTokens;
	}

	private HashMap<String, Integer> buildFrequencyMap()
	{
		HashMap<String, Integer> map = new HashMap<>();
		this.wordsAfterStemming.stream().filter(StringHandler::isAlphabetic).forEach(word -> {
			if (map.containsKey(word))
				map.put(word, map.get(word) + 1);
			else
				map.put(word, 1);
		});
		return map;
	}

	private HashMap<String, Integer> findFrequentTokens()
	{
		HashMap<String, Integer> frequentTokens = new HashMap<>();
		this.frequencyMap.keySet().stream().forEach(key -> {
			int value = this.frequencyMap.get(key);
			if (value > this.averageFrequency)
				frequentTokens.put(key, value+1);
		});
		return frequentTokens;
	}

	private int calculateAverageFrequency()
	{
		int average = 0;
		int wordCount = 0;
		for (String key : this.frequencyMap.keySet()) {
			if (this.frequencyMap.get(key) > 10) {
				average += this.frequencyMap.get(key);
				wordCount++;
			}
		}
		return Math.round(average / wordCount);
	}
}