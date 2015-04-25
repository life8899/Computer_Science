package textsum;

import util.FileHandler;
import util.FileMode;
import util.FilePath;
import util.MathHelper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CosineMatrix {

    private ArrayList<String> words;
    private ArrayList<String> sentences;
    private ArrayList<ArrayList<Integer>> cosineMatrix;

    public CosineMatrix()
    {
        this.words = new ArrayList<>();
        this.sentences = new ArrayList<>();
        this.cosineMatrix = new ArrayList<>();
    }

    public void addWord(String word)
    {
        if (!this.words.contains(word)) {
            this.words.add(word);
        }
    }

    public void addSentence(String sentence)
    {
        if (!this.sentences.contains(sentence)) {
            this.sentences.add(sentence);
        }
    }

    public void buildMatrix()
    {
	    for (int i = 0; i < this.sentences.size(); i++) {
		    this.cosineMatrix.add(new ArrayList<>());
	    }
	    for (int sentenceIndex = 0; sentenceIndex < this.sentences.size(); sentenceIndex++) {
		    String sentence = this.sentences.get(sentenceIndex);
		    for (String word : this.words) {
			    if (sentence.contains(word))
				    this.cosineMatrix.get(sentenceIndex).add(1);
			    else
				    this.cosineMatrix.get(sentenceIndex).add(0);
		    }
	    }
    }

	public double calculateCosineSimilarity()
	{

		//ArrayList<Double> sims = new ArrayList<>((int)Math.pow(this.cosineMatrix.size(), 2));
		for (int firstSentenceIndex = 0; firstSentenceIndex < this.cosineMatrix.size(); firstSentenceIndex++) {
			for (int secondSentenceIndex = 1; secondSentenceIndex < this.cosineMatrix.size(); secondSentenceIndex++) {
				ArrayList<Integer> firstSentenceValues = new ArrayList<>();
				this.cosineMatrix.get(firstSentenceIndex).stream().forEach(i -> firstSentenceValues.add(i));
				ArrayList<Integer> secondSentenceValues = new ArrayList<>();
				this.cosineMatrix.get(secondSentenceIndex).stream().forEach(i -> secondSentenceValues.add(i));
				//System.out.println(firstSentenceValues);
				//System.out.println(secondSentenceValues);
				double sim = MathHelper.dotProduct(firstSentenceValues, secondSentenceValues) / (MathHelper.magnitude(firstSentenceValues) * MathHelper.magnitude(secondSentenceValues));
				//sims.add();
			}
		}
		return 0;
	}

    public String toString()
    {
		String asString = "";

        //FileHandler matrixFileHandler = new FileHandler(FilePath.MATRIX, FileMode.DELETE_BEFORE_APPEND);
	    //matrixFileHandler.initFileForWriting();
	    for (int wordIndex = 0; wordIndex < words.size(); wordIndex++) {
		    //matrixFileHandler.writeStringToFile("Word " + (wordIndex + 1) + ") " + this.words.get(wordIndex), true);
			asString += "Word " + (wordIndex + 1) + ") " + this.words.get(wordIndex) + "\n";
		    for (int sentenceIndex = 0; sentenceIndex < this.sentences.size(); sentenceIndex++) {
			    if (this.cosineMatrix.get(wordIndex).get(sentenceIndex) == 1) {
				    //matrixFileHandler.writeStringToFile("\t" + this.cosineMatrix.get(wordIndex).get(sentenceIndex) + " - Sentence " + (sentenceIndex + 1) + ") " + this.sentences.get(sentenceIndex), true);
					asString += "\t" + this.cosineMatrix.get(wordIndex).get(sentenceIndex) + " - Sentence " + (sentenceIndex + 1) + ") " + this.sentences.get(sentenceIndex);
			    }
		    }
		    //matrixFileHandler.writeStringToFile("\n", false);
	    }
		return asString;
    }
}