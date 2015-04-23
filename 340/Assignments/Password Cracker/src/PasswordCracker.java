import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Cracks a UNIX system passwd file
 *
 * @author Nick Alexander
 * @version 2-7-2015
 */
public class PasswordCracker
{
    private static HashMap<String, String> accounts = new HashMap<String, String>();
    private static HashMap<String, String> dictionary = new HashMap<String, String>();
    private static HashMap<String, String> crackedPasswords = new HashMap<String, String>();

    /**
     * Opens a JFileChooser to Open a Password and Dictionary File. Proceeds to Crack Passwords.
     * @param args None
     * @throws FileNotFoundException if File Not Found
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        createAccounts(openFile("Passwd File"));
        dictionary = createDictionary(openFile("Dictionary File"));
        long start = System.currentTimeMillis();
        for (String account : accounts.keySet()) {
            if(singleMangle(account)) {
                System.out.println(crackedPasswords.keySet().size() + ") Cracked " + account + " -> " + crackedPasswords.get(account));
            } else if (doubleMangle(account)) {
                System.out.println(crackedPasswords.keySet().size() + ") Cracked " + account + " -> " + crackedPasswords.get(account));
            }
        }
        System.out.println("Cracked " + crackedPasswords.size() + "/" + accounts.size() + " Passwords");
        System.out.println("Run Time: " + String.format("%,d", System.currentTimeMillis() - start) + " Milliseconds");
    }

    /**
     * Tests Single Mangles
     * @param account Current Account
     * @return True if password cracked
     */
    private static boolean singleMangle(String account)
    {
        String password = accounts.get(account);
        String salt = password.substring(0, 2);
        for (String word : dictionary.keySet()) {
            for (Mangle m : Mangle.values()) {
                String attempt = Mangler.testMangles(m, word);
                if (JCrypt.crypt(salt, attempt).equals(password)) {
                    crackedPasswords.put(account, attempt);
                    return true;
                }
            }
        }

        // Mangle.PREPEND
        for (String word : dictionary.keySet()) {
            for (byte b = 32; b < 127; b++) {
                String attempt = (char)b + word;
                if (JCrypt.crypt(salt, attempt).equals(password)) {
                    crackedPasswords.put(account, attempt);
                    return true;
                }
            }
        }

        // Mangle.APPEND
        for (String word : dictionary.keySet()) {
            for (byte b = 32; b < 127; b++) {
                String attempt = word + (char)b;
                if (JCrypt.crypt(salt, attempt).equals(password)) {
                    crackedPasswords.put(account, attempt);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tests Double Mangles
     * @param account Current account
     * @return True if password cracked
     */
    private static boolean doubleMangle(String account)
    {
        String password = accounts.get(account);
        String salt = password.substring(0, 2);
        for (String word : dictionary.keySet()) {
            for (Mangle firstMangle : Mangle.values()) {
                if (firstMangle.equals(Mangle.PREPEND) || firstMangle.equals(Mangle.APPEND)) continue; // Skip APPEND and PREPEND
                String mangledOnce = Mangler.testMangles(firstMangle, word); // Apply first Mangle
                for (Mangle secondMangle : Mangle.values()) {
                    if (secondMangle.equals(Mangle.PREPEND)) { continue; // Skip PREPEND
                    } else if (secondMangle.equals(Mangle.APPEND)) {
                        for (byte secondMangleAppend = 32; secondMangleAppend < 127; secondMangleAppend++) { // Test every APPEND against password
                            String mangledTwice = mangledOnce + (char)secondMangleAppend;
                            if (JCrypt.crypt(salt, mangledTwice).equals(password)) {
                                crackedPasswords.put(account, mangledTwice);
                                return true;
                            }
                        }
                    }
                    String mangledTwice = Mangler.testMangles(secondMangle, mangledOnce);
                    if (JCrypt.crypt(salt, mangledTwice).equals(password)) {
                        crackedPasswords.put(account, mangledTwice);
                        return true;
                    } else if (JCrypt.crypt(salt, mangledOnce + mangledTwice).equals(password)) { // Concatenate to pseudo apply a 3rd duplicate mangle
                        crackedPasswords.put(account, (mangledOnce + mangledTwice));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Creates accounts hashmap from passwd file
     * @param passwdFile File containing account information
     * @throws FileNotFoundException if File Not Found
     */
    private static void createAccounts(File passwdFile) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(passwdFile);
        while (fileScanner.hasNextLine()) {
            String[] lineComps = fileScanner.nextLine().split(":");
            String name = lineComps[4].toLowerCase();
            String firstName = name.split(" ")[0];
            String lastName = name.split(" ")[1];
            dictionary.put(firstName, firstName);
            dictionary.put(lastName, lastName);
            accounts.put(lineComps[0], lineComps[1]);
        }
    }

    /**
     * Creates Dictionary from Words File
     * @param dictionaryFile File containing dictionary words
     * @return  dictionary hashmap
     * @throws FileNotFoundException if File Not Found
     */
    private static HashMap<String, String> createDictionary(File dictionaryFile) throws FileNotFoundException
    {
        Scanner fileScanner = new Scanner(dictionaryFile);
        while (fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            dictionary.put(word, word);
        }
        return dictionary;
    }

    /**
     * Constructs a JFileChooser to Select Files
     * @param title Title for JFileChooser window
     * @return Selected file
     */
    private static File openFile(String title)
    {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        fileChooser.setDialogTitle(title);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}