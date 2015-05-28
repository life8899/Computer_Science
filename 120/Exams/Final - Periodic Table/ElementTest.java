
/**
 * Tests the Element Class
 * 
 * @author Nick Alexander
 * @version December 9, 2013
 */

import java.util.*;
import java.io.*;

public class ElementTest
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        
        try
        {
            System.out.print("Eneter the Input File Name: ");
            String fileName = scanner.nextLine();
            InputStream input = new FileInputStream(fileName);
            ArrayList<Element> elements = new ArrayList<Element>();
            
            
            boolean done = false;
            //Bad Style But Ran Out of Time and Panicked! :(
            for (int k = 0; k < 13; k++)
            {
                int nextInt;
                String abbr = "";
                for (int i = 0; i < 3; i++)
                {
                    nextInt = input.read();
                    if (nextInt != 32)
                        abbr = abbr + (char)nextInt;
                    else
                        break;
                }
                
                String name = "";
                for (int i = 0; i < 10; i++)
                {
                    nextInt = input.read();
                    if (nextInt == 32)
                    {
                        break;
                    }
                    else
                        name = name + (char)nextInt;
                }
                
                String massString = "";
                for (int i = 0; i < 10; i++)
                {
                    nextInt = input.read();
                    if (nextInt == 10 || nextInt == -1)
                    {
                        break;
                    }
                    else
                        massString = massString + (char)nextInt;
                }
                double mass = Double.parseDouble(massString);
                
                elements.add(new Element(abbr, name, mass));
                Collections.sort(elements);
            }
            
            for (int i = 0 ; i < elements.size(); i++)
            {
                System.out.println(elements.get(i).toString());
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: File Not Found!");
        }
    }
}
