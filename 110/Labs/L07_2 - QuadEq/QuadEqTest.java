
/**
 * Tests the QuadEq class using user Quadratics
 * 
 * @author Nick Alexander
 * @version 3/1/13
 */

import java.util.Scanner;

public class QuadEqTest
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Root-o-Matic");
        System.out.print("Input a: ");
        String answer = scanner.nextLine();
        double a = Double.parseDouble(answer);
        System.out.print("Input b: ");
        answer = scanner.nextLine();
        double b = Double.parseDouble(answer);
        System.out.print("Input c: ");
        answer = scanner.nextLine();
        double c = Double.parseDouble(answer);
        
        QuadEq myEq = new QuadEq(a, b, c);
        if (a==0)
        {
            System.out.println("Undefined");
        }
        else if (myEq.findDiscrim() < 0)
        {
            System.out.println("Roots are complex.");
        }
        else
        {
            System.out.println("Root 1 is " + myEq.findRoot1());
            System.out.println("Root 2 is " + myEq.findRoot2());
        }
    }
}
