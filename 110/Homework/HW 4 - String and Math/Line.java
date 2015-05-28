
/**
 * Calculates slope and length of a line
 * 
 * @author Nick Alexander
 * @version 2/23/13
 */

import java.util.Scanner;

public class Line
{
    public static double Slope(int x1, int y1, int x2, int y2)
    {
        double dx2 = (double)x2;
        double lineSlope = (y2 - y1)/(x2 - x1);
        return lineSlope;
    }
    
    public static double Length(int x1, int y1, int x2, int y2)
    {
        double length = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
        return length;
    }    

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x1: ");
        String stringX1 = scanner.nextLine();
        int x1 = Integer.parseInt(stringX1);
        
        System.out.println("Enter y1: ");
        String stringY1 = scanner.nextLine();
        int y1 = Integer.parseInt(stringY1);
        
        System.out.println("Enter x2 (must not = x1: ");
        String stringX2 = scanner.nextLine();
        int x2 = Integer.parseInt(stringX2);
        
        System.out.println("Enter y2: ");
        String stringY2 = scanner.nextLine();
        int y2 = Integer.parseInt(stringY2);
        
        System.out.println("The slope of the line is: " + Line.Slope(x1, y1, x2, y2));
        System.out.println("The length of the line is: " + Line.Length(x1, y1, x2, y2));   
    }
}
