
/**
 * Accepts user input for Month number and returns 3 letter abbreviation
 * 
 * @author Nick Alexander 
 * @version 2/20/13
 */

import java.util.Scanner;

public class Months
{
    public static void main(String[] args)
    {
        String monthString = "JanFebMarAprMayJunJulAugSepOctNovDec";
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter a month: ");
        String answer = myScanner.nextLine();
        int mon = Integer.parseInt(answer);
        int end = mon*3;
        int start = end - 3;
        System.out.println("The month is: "+ monthString.substring(start, end));
        System.exit(0);
    }
}
