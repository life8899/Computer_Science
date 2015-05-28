
/**
 * Computes Fibonacci Number of a given integer n using recursive algorithm
 * 
 * @author Nick Alexander
 * @version 10/2/13
 */

public class Fibonacci
{
    public static int fib(int n)
    {
        if (n <= 2)
            return 1;
        else
            return fib(n-1) + fib(n-2);
    }
    
    public static void main(String[] args)
    {
        int myInt = Integer.parseInt(args[0]);
        System.out.println("Fibonacci (" + myInt + ") = " + fib(myInt));
    }
}
