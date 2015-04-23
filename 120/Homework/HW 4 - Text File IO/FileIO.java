
/**
 * Reads a text file and stores the data in an ArrayList
 * Uses ArrayList data for comnputations
 * 
 * @author Nick Alexander
 * @version 9/19/13
 */

import java.util.*;
import java.io.*;
import javax.swing.*;

public class FileIO
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //Initialize ArrayList of Integer objects
        ArrayList<Integer> grades = new ArrayList<Integer>();
        
        //Read Input
        JFileChooser fileInput = new JFileChooser();
        if (fileInput.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            FileReader reader = new FileReader(fileInput.getSelectedFile());
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine())
            {
                String textLine = scanner.nextLine();
                //Parses String into Integers
                grades.add(Integer.parseInt(textLine));
            }
            reader.close();
        }

        //Number of Entries is equals to Size of List
        int entries = grades.size(), sum = 0;
        
        //Initialize small/large values to first value in List to test
        int largest = grades.get(0), smallest = grades.get(0);
        double average = 0;
        for (int i = 0; i < grades.size(); i++)
        {
            //Calculate Sum
            sum += grades.get(i);
            
            //Test Largest Entry
            if (grades.get(i) > largest) {largest = grades.get(i);}
            
            //Test Smallest Entry
            if (grades.get(i) < smallest) {smallest = grades.get(i);}
        }
        //Average = sum / entries; cast sum as double to preserve accuracy
        average = (double) sum/entries;
        
        //Print Results
        System.out.println("A) Entries = " + entries);
        System.out.println("B) Sum = " + sum);
        System.out.println("C) Average = " + average);
        System.out.println("D) Largest = " + largest);
        System.out.println("E) Smallest = " + smallest);
    }
}
