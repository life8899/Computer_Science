import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Implementation of the Jean-Taillefer & Pierre-Wacozyk Cryptosystem
 *
 * @author Nick Alexander
 * @version 1-19-2015
 */
public class Cryptomatic
{
	private static final byte SPACE = (byte)' ';
	private static final int BYTE_MAX = 127;
	private static final int FULL_TEXT_THRESHOLD = 100;
	private static final double POSSIBILITY_THRESHOLD = 0.50;
	private static long bruteForceStart = 0;
	private static long bruteForceEnd = 0;

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n1)Encrypt File\n2)Decrypt File\n3)Brute Force Decryption");
		System.out.print("Select: ");
		int select = Integer.parseInt(scanner.nextLine());
		try {
			if (select == 1 || select == 2 || select == 3) {
				System.out.print("Input File: ");
				String inPath = scanner.nextLine();
				System.out.print("Output File: ");
				String outPath = scanner.nextLine();
				byte[] messageBytes = Files.readAllBytes(getPathFromString(inPath));
				byte[] resultBytes;
				if (select == 1 || select == 2) {
					System.out.print("Key: ");
					String key = scanner.nextLine();
					resultBytes = (select == 1) ? encrypt(messageBytes, key.getBytes()) : decrypt(messageBytes, key.getBytes());
				} else {
					System.out.print("Dictionary File: ");
					String dictPath = scanner.nextLine();
					byte[] key = bruteForce(messageBytes, buildDictionary(dictPath));
					resultBytes = decrypt(messageBytes, key);
					System.out.println(new String(resultBytes));
					System.out.println("\nEncryption Key: <" + key[0] + ", " + key[1] + "> : " + new String(key));
					bruteForceEnd = System.currentTimeMillis();
					System.out.println("Brute Force Decryption took " + (bruteForceEnd - bruteForceStart) + " milliseconds!");
				}
				Files.write(getPathFromString(outPath), resultBytes, StandardOpenOption.CREATE);
				System.out.println(((select == 1) ? "Encryption" : "Decryption") + " Complete and File Saved");
			} else {
				System.out.println("Error: Invalid Selection");
				System.exit(-1);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error: File Not Found!");
			System.exit(-2);
		} catch (IOException ioe) {
			System.out.println("Error: IO Error!");
			System.exit(-3);
		} catch (NullPointerException npe) {
			System.out.println("No Encryption Key Found!");
			System.exit(1);
		}
	}

	/**
	 * Encrypt a message using the Jean-Taillefer & Pierre-Wacozyk Cryptosystem
	 *
	 * @param message ASCII Message to encrypt
	 * @param key 16 bit ASCII Encryption key
	 * @return byte[] containing ciphertext bytes
	 */
	public static byte[] encrypt(byte[] message, byte[] key)
	{
		if (message.length % 2 != 0) { // If message is odd length, pad with whitespace for even length
			byte[] tmp = message.clone();
			message = new byte[tmp.length + 1];
			System.arraycopy(tmp, 0, message, 0, tmp.length);
			message[message.length - 1] = SPACE;
		}
		byte[] keyBytes = new byte[message.length];
		for (int i = 0; i < keyBytes.length; i++) { // Repeat key for length of message
			keyBytes[i] = key[i % 2];
		}
		byte[] cipherBytes = new byte[message.length];
		for (int i = 0; i < message.length; i++) { // Perform xor
			int xor = message[i] ^ keyBytes[i];
			cipherBytes[i] = (byte)xor;
		}
		if (cipherBytes[cipherBytes.length - 1] == SPACE) { //Remove trailing whitespace
			byte[] tmp = cipherBytes.clone();
			cipherBytes = new byte[cipherBytes.length - 1];
			System.arraycopy(tmp, 0, cipherBytes, 0, tmp.length - 1);
		}
		return cipherBytes;
	}

	/**
	 * Decrypt a message using the Jean-Taillefer & Pierre-Wacozyk Cryptosystem
	 *
	 * @param message ASCII Message to decrypt
	 * @param key 16 bit ASCII Encryption key
	 * @return byte[] containing plaintext bytes
	 */
	public static byte[] decrypt(byte[] message, byte[] key)
	{
		return encrypt(message, key);
	}

	/**
	 * Systematically tests every possible encryption starting with <127, 127> and running until <0,0> or
	 * a key produces strings of which at least 50% are valid words.
	 *
	 * @param message ASCII Message to decrypt using brute force
	 * @param dictionary HashMap containing English dictionary
	 * @return byte[] containing encryption key
	 */
	public static byte[] bruteForce(byte[] message, HashMap<String, byte[]> dictionary)
	{
		bruteForceStart = System.currentTimeMillis();
		int chunkLength;
		if ((message.length / 2) < FULL_TEXT_THRESHOLD) { // If message is under threshold, use entire message to ensure correctness
			chunkLength = message.length;
		} else {
			chunkLength = FULL_TEXT_THRESHOLD;
		}
		byte[] chunk = new byte[chunkLength];
		System.arraycopy(message, message.length/2, chunk, 0, chunkLength);
			for (byte first = BYTE_MAX; first > 0; first--) {
				for (byte second = BYTE_MAX; second > 0; second--) { // Start at <127,127> and run until <0,0>
					byte[] key = new byte[] {first, second};
					String decrypted = new String(decrypt(chunk, key));
					String[] strings = decrypted.split(" ");
					if (strings.length == 1) { // Logically text must contain at least one space
						continue;
					} else {
						int isValid = 0;
						for (String str : strings) {
							if (dictionary.containsKey(str.toLowerCase())) { // Check if string is a word
								isValid++;
							}
						}
						if (isValid > (int)(strings.length * POSSIBILITY_THRESHOLD)) { // If % of strings are words, probable key
							return new byte[] {first, second};
						}
					}
				}
			}
		return null;
	}

	/**
	 * Stores all words in supplied dictionary file into a HashMap for operations in constant time
	 * @param dictPath File path to dictionary text file
	 * @return HashMap of word as string and corresponding byte code
	 * @throws FileNotFoundException If dictPath does not exist
	 */
	private static HashMap<String, byte[]> buildDictionary(String dictPath) throws FileNotFoundException
	{

		HashMap<String, byte[]> dictionary = new HashMap<String, byte[]>();
		Scanner fileScanner = new Scanner(new File(dictPath));
		while (fileScanner.hasNextLine()) {
			String word = fileScanner.nextLine();
			dictionary.put(word, word.getBytes());
		}
		return dictionary;
	}

	/**
	 * Generates a Path object from a string file path
	 * @param path String path to file
	 * @return Path object of the file
	 */
	private static Path getPathFromString(String path)
	{
		return FileSystems.getDefault().getPath(path);
	}
}