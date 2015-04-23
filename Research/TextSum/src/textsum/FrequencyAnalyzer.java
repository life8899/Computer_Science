package textsum;

import java.util.HashMap;
import java.util.Set;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.StopTokenizerFactory;



public class FrequencyAnalyzer
{
	private String fullText;
	private String[] wordsAfterStemming;
	private String[] wordsAfterStopRemoval;
	private HashMap<String, Integer> frequencyMap;
	private HashMap<String, Integer> frequentTokens;
	private int averageFrequency;

	public FrequencyAnalyzer(String fullText, Set<String> stopWordSet)
	{
		this.fullText = fullText;
		this.wordsAfterStopRemoval = new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopWordSet).tokenizer(fullText.toCharArray(), 0, fullText.length()).tokenize();
		this.wordsAfterStemming = new PorterStemmerTokenizerFactory(new StopTokenizerFactory(new LowerCaseTokenizerFactory(IndoEuropeanTokenizerFactory.INSTANCE), stopWordSet)).tokenizer(fullText.toCharArray(), 0, fullText.length()).tokenize();
		this.frequencyMap = buildFrequencyMap();
		this.averageFrequency = calculateAverageFrequency();
		this.frequentTokens = findFrequentTokens();
	}

	public String[] getWordsAfterStemming()
	{
		return this.wordsAfterStemming;
	}

	public String[] getWordsAfterStopRemoval()
	{
		return this.wordsAfterStopRemoval;
	}

	public int getAverageFrequency()
	{
		return this.averageFrequency;
	}

	public HashMap<String, Integer> getFrequentTokens()
	{
		return this.frequentTokens;
	}

	private HashMap<String, Integer> buildFrequencyMap()
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < this.wordsAfterStemming.length; i++) {
			try {
				Double.parseDouble(this.wordsAfterStemming[i]);
			} catch (NumberFormatException numFormEx) {
				if (map.containsKey(this.wordsAfterStemming[i])) {
					map.put(this.wordsAfterStemming[i], map.get(this.wordsAfterStemming[i]) + 1);
				} else {
					map.put(this.wordsAfterStemming[i], 1);
				}
			}
		}
		return map;
	}

	private HashMap<String, Integer> findFrequentTokens()
	{

		HashMap<String, Integer> frequentTokens = new HashMap<String, Integer>();
		for (String key : this.frequencyMap.keySet()) {
			if (this.frequencyMap.get(key) > this.averageFrequency) {
				frequentTokens.put(key, this.frequencyMap.get(key));
			}
		}
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
	
	public String getFullText()
	{
		return this.fullText;
	}
}