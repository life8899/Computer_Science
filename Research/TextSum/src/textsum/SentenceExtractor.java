package textsum;

import java.util.ArrayList;
import java.util.Set;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.sentences.MedlineSentenceModel;
import com.aliasi.sentences.SentenceChunker;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;


public class SentenceExtractor
{
	private static final TokenizerFactory tokenFact = IndoEuropeanTokenizerFactory.INSTANCE;
	private static final SentenceModel sentenceModel = new MedlineSentenceModel();
	private static final SentenceChunker chunker = new SentenceChunker(tokenFact, sentenceModel);

	private String text;
	private ArrayList<String> sentenceList;

	public SentenceExtractor(String text)
	{
		this.text = text;
		this.sentenceList = new ArrayList<>();
	}

	public ArrayList<String> extract()
	{
		Chunking chunking = chunker.chunk(text.toCharArray(), 0, text.length());
		Set<Chunk> sentences = chunking.chunkSet();
		String slice = chunking.charSequence().toString();
		for (Chunk sentence : sentences) {
			int start = sentence.start();
			int end = sentence.end();
			sentenceList.add(slice.substring(start, end));
		}
		return this.sentenceList;
	}
}