package textsum;

import util.MathHelper;
import util.SentencePoint;

import java.util.ArrayList;
import java.util.HashMap;

public class CosineMatrix {

    private ArrayList<String> wordsList;
    private ArrayList<String> sentencesList;
    private ArrayList<ArrayList<Integer>> cosineMatrix;

    public CosineMatrix()
    {
        this.wordsList = new ArrayList<>();
        this.sentencesList = new ArrayList<>();
        this.cosineMatrix = new ArrayList<>();
    }

    public void addWord(String word)
    {
        if (!this.wordsList.contains(word)) {
            this.wordsList.add(word);
        }
    }

    public void addSentence(String sentence)
    {
        if (!this.sentencesList.contains(sentence)) {
            this.sentencesList.add(sentence);
        }
    }

    public void buildMatrix()
    {
	    for (int i = 0; i < this.sentencesList.size(); i++) {
		    this.cosineMatrix.add(new ArrayList<>());
	    }
	    for (int sentenceIndex = 0; sentenceIndex < this.sentencesList.size(); sentenceIndex++) {
		    String sentence = this.sentencesList.get(sentenceIndex);
		    for (String word : this.wordsList) {
			    if (sentence.contains(word))
				    this.cosineMatrix.get(sentenceIndex).add(1);
			    else
				    this.cosineMatrix.get(sentenceIndex).add(0);
		    }
	    }
    }

	public HashMap<SentencePoint, Double> calculateCosineSimilarities()
	{
		HashMap<SentencePoint, Double> similarities = new HashMap<>();
		for (int firstSentenceIndex = 0; firstSentenceIndex < this.cosineMatrix.size(); firstSentenceIndex++) {
			if (firstSentenceIndex % 500 == 0)
				System.out.println("First Sentence Index: " + firstSentenceIndex + " / " + this.cosineMatrix.size());
			ArrayList<Integer> firstSentenceValues = new ArrayList<>();
			this.cosineMatrix.get(firstSentenceIndex).stream().forEach(firstSentenceValues::add);
			for (int secondSentenceIndex = firstSentenceIndex+1; secondSentenceIndex < this.cosineMatrix.size(); secondSentenceIndex++) {
				if (similarities.containsKey(new SentencePoint(firstSentenceIndex, secondSentenceIndex)) || similarities.containsKey(new SentencePoint(secondSentenceIndex, firstSentenceIndex))) {
					System.out.println("Skipping");
					continue;
				}
				ArrayList<Integer> secondSentenceValues = new ArrayList<>();
				this.cosineMatrix.get(secondSentenceIndex).stream().forEach(secondSentenceValues::add);
				double sim = MathHelper.dotProduct(firstSentenceValues, secondSentenceValues) / (MathHelper.magnitude(firstSentenceValues) * MathHelper.magnitude(secondSentenceValues));
				SentencePoint sentences = new SentencePoint(firstSentenceIndex, secondSentenceIndex);
				similarities.put(sentences, sim);
			}
		}
		return similarities;
	}

	public String[] getSentencesAtPoint(SentencePoint point)
	{
		String[] sentences = new String[2];
		sentences[0] = this.sentencesList.get(point.getX());
		sentences[1] = this.sentencesList.get(point.getY());
		return sentences;
	}

    public String toString()
    {
		String asString = "";
	    for (int wordIndex = 0; wordIndex < wordsList.size(); wordIndex++) {
			if (wordIndex % 500 == 0 || wordIndex >= wordsList.size() * 0.95) {
				System.out.println("Word Index: " + wordIndex + " / " + wordsList.size());
			}
			asString += "Word " + (wordIndex + 1) + ") " + this.wordsList.get(wordIndex) + "\n";
		    for (int sentenceIndex = 0; sentenceIndex < this.sentencesList.size(); sentenceIndex++) {
			    if (this.cosineMatrix.get(wordIndex).get(sentenceIndex) == 1) {
					asString += "\t" + this.cosineMatrix.get(wordIndex).get(sentenceIndex) + " - Sentence " + (sentenceIndex + 1) + ") " + this.sentencesList.get(sentenceIndex);
			    }
		    }
	    }
		return asString;
    }
}