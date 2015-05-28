import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Allows a User to Hide a Text File or a JPEG Image File Inside a WAV Audio File
 *
 * @author Nick Alexander
 * @version 2/20/2015
 */
public class Steg
{

    private static final String WAV = "wav", TXT = "txt", JPG = "jpg";
    private static final String DELIM = "|--- ";

    /**
     * Runs the program
     *
     * @param args N/A
     * @throws IOException if IO Error Occurs
     * @throws IllegalArgumentException if Invalid Selection is Provided
     */
    public static void main(String[] args) throws IOException, IllegalArgumentException
    {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        do {
            try {
                System.out.println("1) Insert into WAV\n" + "2) Extract from WAV\n" + "3) Exit");
                System.out.print("Select: ");
                int select = Integer.parseInt(scanner.nextLine());
                if (select == 1) { // Insert
                    System.out.println(DELIM + "1) Insert Text\n" + DELIM + "2) Insert Image");
                    System.out.print(DELIM + "Select: ");
                    select = Integer.parseInt(scanner.nextLine());
                    if (select == 1 || select == 2) { // Insert TXT into WAV
                        String extension = (select == 1) ? TXT : JPG;

                        System.out.print(DELIM + DELIM + "Input WAV File: ");
                        String wavFilePath = chooseFile(WAV);
                        System.out.println(wavFilePath);

                        System.out.print(DELIM + DELIM + "Input " + extension + " File: ");
                        String toHideFilePath = chooseFile(extension);
                        System.out.println(toHideFilePath);

                        System.out.println(DELIM + DELIM + "Processing...");
                        byte[] newWav = combineArrays(readFile(wavFilePath), readFile(toHideFilePath));

                        System.out.print(DELIM + DELIM + "Output WAV File: ");
                        String outPath = writeFilePath(WAV);
                        System.out.println(outPath);

                        writeFile(outPath, newWav);
                        System.out.println(DELIM + DELIM + "Saved\n");
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else if (select == 2) { // Extract
                    System.out.println(DELIM + "1) Extract Text\n" + DELIM + "2) Extract Image");
                    System.out.print(DELIM + "Select: ");
                    select = Integer.parseInt(scanner.nextLine());
                    if (select == 1 || select == 2) {
                        String extension = (select == 1) ? TXT : JPG;

                        System.out.print(DELIM + DELIM + "Input WAV File: ");
                        String wavFilePath = chooseFile(WAV);
                        System.out.println(wavFilePath);

                        System.out.println(DELIM + DELIM + "Processing");
                        byte[] extractedFileBytes = extract(readFile(wavFilePath));

                        System.out.print(DELIM + DELIM + "Output " + extension.toUpperCase() + " File: ");
                        String outPath = writeFilePath(extension);
                        System.out.println(outPath);

                        writeFile(outPath, extractedFileBytes);
                        System.out.println(DELIM + DELIM + "Saved\n");
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else if (select == 3) {
                    done = true;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException illArgE) {
                System.out.println("Invalid Selection!");
            } catch (IOException ioE) {
                System.out.println("Ouch! IO Operations Returned An Error!");
                ioE.printStackTrace();
            }
        } while (!done);
        System.out.println("Stay Frosty");
    }

    /**
     * Extracts the hidden file from the WAV byte array
     *
     * @param wavBytes WAV File in Bytes
     * @return Extracted File in Bytes
     */
    private static byte[] extract(byte[] wavBytes)
    {
        int wavLength = getWavFileSize(wavBytes);
        byte[] extractedBytes = new byte[wavBytes.length - wavLength];
        for (int exBytePos = 0, wavBytePos = wavLength; wavBytePos < wavBytes.length; exBytePos++, wavBytePos++) {
            extractedBytes[exBytePos] = wavBytes[wavBytePos];
        }
        return extractedBytes;
    }


    /**
     * Appends the Second Array to the End of the First Array and Returns the Combined Array
     *
     * @param first First Array
     * @param second Second Array
     * @return Combined Array
     */
    private static byte[] combineArrays(byte[] first, byte[] second)
    {
        byte[] newBytes = new byte[first.length + second.length];
        System.arraycopy(first, 0, newBytes, 0, first.length);
        System.arraycopy(second, 0, newBytes, first.length, second.length);
        return newBytes;
    }

    /**
     * Returns the File Size of a WAV Audio File as Specified by the Header Value
     *
     * @param wavBytes WAV File in Bytes
     * @return File Size
     */
    private static int getWavFileSize(byte[] wavBytes)
    {
        byte[] fileSizeBytes = Arrays.copyOfRange(wavBytes, 4, 8); // Extract File Size Header Information
        ByteBuffer buffer = ByteBuffer.wrap(fileSizeBytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt() + 8; // Add 8 for the header
    }

    /**
     * Prompts the User to Choose a File of a Given Extension and Returns the Path to that File
     *
     * @param extension Allowed Extension
     * @return Path to Chosen File as a String
     * @throws IOException if IO Error Occurs
     */
    private static String chooseFile(String extension) throws IOException
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filterFromExtension(extension));
        fileChooser.setDialogTitle("Choose " + extension + " File");
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    /**
     * Reads the Bytes from a File Specified by the Path
     *
     * @param pathToFile Path to File
     * @return File Bytes
     * @throws IOException if IO Error Occurs
     */
    private static byte[] readFile(String pathToFile) throws IOException
    {
        return Files.readAllBytes(Paths.get(pathToFile));
    }

    /**
     * Prompts the User to Save a File of a Given Extension and Returns the Path to that File
     *
     * @param extension Allowed Extension
     * @return Path to the File as String
     * @throws IOException if IO Error Occurs
     * @throws IllegalArgumentException if Extension is not a Valid Value
     */
    private static String writeFilePath(String extension) throws IOException, IllegalArgumentException
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(filterFromExtension(extension));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Save As");
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String pathString = fileChooser.getSelectedFile().getAbsolutePath();
            if (extension.equals(WAV) && !pathString.endsWith("." + WAV)) {
                pathString += "." + WAV;
            } else if (extension.equals(TXT) && !pathString.endsWith("." + TXT)) {
                pathString += "." + TXT;
            } else if (extension.equals(JPG) && !pathString.endsWith("." + JPG)) {
                pathString += "." + JPG;
            }
            return pathString;
        }
        return null;
    }

    /**
     * Writes the Bytes to File on Disk
     *
     * @param path Path to the File
     * @param fileBytes Bytes of the New File
     * @throws IOException if IO Error Occurs
     */
    private static void writeFile(String path, byte[] fileBytes) throws IOException
    {
        Files.write(Paths.get(path), fileBytes, StandardOpenOption.CREATE);
    }

    /**
     * Returns Proper FileNameExtensionFilter Object given an Extension String
     *
     * @param extension Extension
     * @return FileNameExtensionFilter for the Extension
     * @throws IllegalArgumentException if Extension is not a Valid Value
     */
    private static FileNameExtensionFilter filterFromExtension(String extension) throws IllegalArgumentException
    {
        if (extension.equals(WAV)) {
            return new FileNameExtensionFilter("WAV Audio File", WAV);
        } else if (extension.equals(TXT)) {
            return new FileNameExtensionFilter("TXT Text File", TXT);
        } else if (extension.equals(JPG)) {
            return new FileNameExtensionFilter("JPG Image File", JPG);
        } else {
            throw new IllegalArgumentException(extension + " is not a valid extension");
        }
    }
}