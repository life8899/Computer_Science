package textsum;

import util.FileHandler;
import util.FileMode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CosineMatrix {

    private final String matrixPath = "/Users/Nick/Developer/Computer_Science/Research/TextSum/sup/matrix.txt";

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

    public void writeToFile() throws IOException
    {
        FileHandler matrixFileHandler = new FileHandler(matrixPath, FileMode.WRITE);
    	File outFile = new File(this.matrixPath);
    	FileWriter writer = new FileWriter(outFile);
        for (int wordIndex = 0; wordIndex < this.words.size(); wordIndex++) {
        	writer.write("Word " + (wordIndex + 1) + " - " + this.words.get(wordIndex) + "\n");
            for (int sentenceIndex = 0; sentenceIndex < this.sentences.size(); sentenceIndex++) {
            	if (this.cosineMatrix.get(wordIndex).get(sentenceIndex) == 1) {
            		writer.write("\t" + this.cosineMatrix.get(wordIndex).get(sentenceIndex) + " - Sentence " + (sentenceIndex + 1) + ": " + this.sentences.get(sentenceIndex) + "\n");
            	}
            }
            writer.write("\n");
        }
        writer.close();
    }
}