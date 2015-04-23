package textsum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CosineMatrix {

    private ArrayList<String> words;
    private ArrayList<String> sentences;
    private ArrayList<ArrayList<Integer>> cosineMatrix;

    public CosineMatrix()
    {
        this.words = new ArrayList<String>();
        this.sentences = new ArrayList<String>();
        this.cosineMatrix = new ArrayList<ArrayList<Integer>>();
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
        for (int i = 0; i < this.words.size(); i++) {
            this.cosineMatrix.add(new ArrayList<Integer>());
        }

        for (int wordIndex = 0; wordIndex < this.words.size(); wordIndex++) {
            for (int sentenceIndex = 0; sentenceIndex < this.sentences.size(); sentenceIndex++) {
                String word = this.words.get(wordIndex);
                String sentence = this.sentences.get(sentenceIndex);
                if (sentence.contains(word)) {
                    this.cosineMatrix.get(wordIndex).add(1);
                } else {
                    this.cosineMatrix.get(wordIndex).add(0);
                }
            }
        }
    }

    public void asString() throws IOException
    {
    	File outFile = new File("C:\\Users\\Nick\\Developer\\Computer_Science\\Research\\TextSum\\sup\\matrix.txt");
    	FileWriter writer = new FileWriter(outFile);
        for (int wordIndex = 0; wordIndex < this.words.size(); wordIndex++) {
        	//System.out.println("Word " + (wordIndex + 1) + " - " + this.words.get(wordIndex));
        	writer.write("Word " + (wordIndex + 1) + " - " + this.words.get(wordIndex) + "\n");
            for (int sentenceIndex = 0; sentenceIndex < this.sentences.size(); sentenceIndex++) {
            	if (this.cosineMatrix.get(wordIndex).get(sentenceIndex) == 1) {
            		//System.out.println("\t" + this.cosineMatrix.get(wordIndex).get(sentenceIndex) + " - Sentence " + (sentenceIndex + 1));
            		writer.write("\t" + this.cosineMatrix.get(wordIndex).get(sentenceIndex) + " - Sentence " + (sentenceIndex + 1) + ": " + this.sentences.get(sentenceIndex) + "\n");
            	}
            }
            //System.out.println();
            writer.write("\n");
        }
        writer.close();
    }
}