package textsum;


import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SummaryCompare
{
	private String summary;
	private String modelSummary;
	private int summaryWordCount;
	private int modelSummaryWordCount;
	private int similarWordCount;

	public SummaryCompare(String summary, String modelSummary)
	{
		this.summary = summary;
		this.modelSummary = modelSummary;
		this.similarWordCount = 0;
	}

	public int compare()
	{

		StringTokenizer summaryATokenizer = new StringTokenizer(this.summary, " \t\n\r\f,.:;?![]'");
		StringTokenizer summaryBTokenizer = new StringTokenizer(this.modelSummary, " \t\n\r\f,.:;?![]'");
		this.summaryWordCount = summaryATokenizer.countTokens();
		this.modelSummaryWordCount = summaryBTokenizer.countTokens();
		ArrayList<String> summaryATokens = new ArrayList<>(summaryATokenizer.countTokens());
		ArrayList<String> summaryBTokens = new ArrayList<>(summaryBTokenizer.countTokens());
		while (summaryATokenizer.hasMoreTokens()) {
			summaryATokens.add(summaryATokenizer.nextToken());
		}
		while (summaryBTokenizer.hasMoreTokens()) {
			summaryBTokens.add(summaryBTokenizer.nextToken());
		}
		if (summaryBTokens.size() < summaryATokens.size()) {
			this.similarWordCount = summaryBTokens.stream().filter(token -> summaryATokens.contains(token)).collect(Collectors.toList()).size();
		} else {
			this.similarWordCount = summaryATokens.stream().filter(token -> summaryATokens.contains(token)).collect(Collectors.toList()).size();
		}
		return this.similarWordCount;
	}

	public int getSummaryWordCount()
	{
		return this.summaryWordCount;
	}

	public int getModelSummaryWordCount()
	{
		return this.modelSummaryWordCount;
	}
}