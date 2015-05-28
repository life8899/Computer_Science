
/**
 * Reads text files and writes to a text file
 * 
 * @author Nick Alexander
 * @version 9/18/2013
 */

import java.util.*;
import java.io.*;
import javax.swing.*;

public class CatFiles
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        for (int i = 1; i < args.length; i++)
        {
            //Opens ith File in Command Line Argumens
            FileReader reader = new FileReader(args[i]);
            //Applies File to Scanner
            Scanner scanner = new Scanner(reader);
            //Creates a New PrintWriter with append = true; Correct way to do it?
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("Book.txt"), true));
            while (scanner.hasNextLine())
            {
                String textLine = scanner.nextLine();
                writer.println(textLine);
            }
            //Closes Writer & Reader
            writer.close();
            reader.close();
        }
        System.out.println("Done!");
    }
}