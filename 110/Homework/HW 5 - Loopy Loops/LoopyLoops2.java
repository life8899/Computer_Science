
/**
 * Using For loops
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class LoopyLoops2
{
    public static void main(String[] args)
    {
        //1
        for (int x = 1; x < 11; x++)
        {
            System.out.println("" + x);
        }
        System.out.println("");
        
        //2
        for (int x=10; x>0; x--)
        {
            System.out.println(""+x);
        }
        System.out.println("");
        
        //3
        for (int x = 0; x<20; x++)
        {
            if (x%2 == 0)
            System.out.println(""+x);
        }
        System.out.println("");
        
        //4
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start value: ");
        int start = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the end value: ");
        int y = Integer.parseInt(scanner.nextLine());
        System.out.println("");
        for (int x = start; x < y; x++)
        {
            System.out.println("" + x);  
        }
    }
}
