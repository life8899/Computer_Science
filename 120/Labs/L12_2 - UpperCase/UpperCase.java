import java.util.Scanner;
import java.io.*;

public class UpperCase 
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input filename: ");
        String inFile = scanner.nextLine();
        InputStream inputStream = new FileInputStream(inFile);
        System.out.print("Enter output filename: ");
        String outFile = scanner.nextLine();
        OutputStream outputStream = new FileOutputStream(outFile);
        
        while (!done)
        {
            int nextChar = inputStream.read();
            if (nextChar == -1)
            {
                done = true;
            }
            else
            {
                byte c = (byte)nextChar;
                if (c > 96 && c < 123)
                    c = (byte)(c-32);
                outputStream.write(c);
            }
        }
        
        inputStream.close();
        outputStream.close();
        System.out.println("Goodbye!");
    }
}