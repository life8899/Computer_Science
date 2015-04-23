
/**
 * Computes a raised to the n with a recursive algorithm
 * 
 * @author Nick Alexander
 * @version 10/2/13
 */

import java.util.Scanner;

public class aPowerN
{
    public static int aToN(int a, int n)
    {
        if (n == 0)
            return 1;
        else
            return a * aToN(a, n-1);    
    }
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("a = ");
        int a = scanner.nextInt();
        System.out.print("n = ");
        int n = scanner.nextInt();
        System.out.println("Result = " + aToN(a, n));
    }
}
