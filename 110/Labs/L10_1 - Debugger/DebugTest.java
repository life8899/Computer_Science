/**
 * Using the BlueJ Debugger!
 * 
 * @author Nick Alexander
 * @version 3/29/13
 */

import java.util.*;

public class DebugTest
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an ending value: ");
        int endNum = Integer.parseInt(scanner.nextLine());
        int count = 0;
        String numString = "";
        while (count < endNum)
        {
            numString = numString + " " + count;
            //FIX -- Must increment the count variable
            count++;
        }
        System.out.println("The numbers are: " + numString);
    }
}
