import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Nick Alexander
 * @version 4-20-2015
 *
 * Tests the RandNumGen class
 */
public class RandNumGenTest
{
	public static final String smallSampleFile = "/Users/Nick/Developer/Computer_Science/340/Assignments/RandNumGen/smallSample.txt";
	public static final String largeSampleFile = "/Users/Nick/Developer/Computer_Science/340/Assignments/RandNumGen/largeSample.txt";

	public static final int smallSample = 512;
	public static final int largeSample = 1024 * 100;

	public static void main(String[] args) throws IOException
	{
		System.out.println("Generating Random Numbers");

		initFile(smallSampleFile);
        writeToFile(generateBitString(new RandNumGen(), smallSample, 0), smallSampleFile);

		initFile(largeSampleFile);
		writeToFile(generateBitString(new RandNumGen(), largeSample, 0), largeSampleFile);

		System.out.println("Random Number Generation Complete");
	}

    private static String generateBitString (RandNumGen generator, int numOfBits, int upperBound)
    {
        int bitCount = 0;
        String randomBitString = "";
        while (bitCount < numOfBits) {
            int random = (upperBound == 0) ? generator.randomInt() : generator.randomIntWithRange(upperBound);
            String bitString = Integer.toBinaryString(random);
            if (bitCount + bitString.length() > numOfBits) {
                int takeBits = numOfBits - bitCount;
                bitString = bitString.substring(0, takeBits);
                bitCount += takeBits;
            } else {
                bitCount += bitString.length();
            }
            randomBitString += bitString;
        }
        return randomBitString;
    }

	private static boolean initFile(String filePath) throws IOException
	{
		File outFile = new File(filePath);
		if (outFile.exists()) {
			if (!outFile.delete()) {
				throw new IOException("Error: File " + outFile.getAbsolutePath() + " could not be deleted.");
			}
		}
		return outFile.createNewFile();
	}

	private static void writeToFile(String str, String filePath) throws IOException
	{
		FileWriter fileWriter = new FileWriter(filePath, true);
		fileWriter.write(str);
		fileWriter.close();
	}
}