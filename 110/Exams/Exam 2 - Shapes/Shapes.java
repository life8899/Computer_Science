
/**
 * Draws Shapes
 * 
 * @author Nick Alexander
 * @version 3/27/13
 */

import java.util.Scanner;

public class Shapes
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do\n" + "1 - Draw a rectangle\n" + "2 - Draw a triangle\n" + "3 - Exit");
        int answer = Integer.parseInt(scanner.nextLine());
        
        if (answer == 1)
        {
            System.out.println("Width?");
            int width = Integer.parseInt(scanner.nextLine());
            System.out.println("Height?");
            int height = Integer.parseInt(scanner.nextLine());
            
            for(int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    System.out.print("x");
                }
                System.out.println();
            }
        }
        
        else if ( answer == 2)
        {
            System.out.println("Sides?");
            int sides = Integer.parseInt(scanner.nextLine());
            
            for (int i = 0; i < sides; i++)
            {
                for (int j = i; j < sides; j++)
                {
                    System.out.print("i");
                }
                System.out.println();
            }
        }
        
        else if (answer == 3)
        {
            System.out.println("Goodbye :)");
            System.exit(0);
        }
        
        else
        {
            System.out.println("Invalid Input");
            System.exit(0);
        }
    }
}
