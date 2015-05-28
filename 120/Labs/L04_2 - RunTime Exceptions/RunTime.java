
/**
 * Uses Try/Catch to handle RunTime Exceptions
 * 
 * @author Nick Alexander
 * @version 9/20/13
 */

import java.util.Scanner;

public class RunTime
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[4];
        for (int i = 0; i < 5; i++)
        {
            try
            {
                System.out.print("Enter An Integer: ");
                numbers[i] = Integer.parseInt(scanner.nextLine());
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("No More Room!");
            }
        }

        System.out.println("Your Numbers Are:");
        for (int i = 0; i < numbers.length; i++)
        {
            System.out.print(numbers[i] + " ");
        }

    }
}
