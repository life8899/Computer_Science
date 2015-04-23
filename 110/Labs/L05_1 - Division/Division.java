import java.util.Scanner;
public class Division
{
  public static void main(String[] args)
  {
    int intA, intB;
    double dblC, dblD;
    Scanner myScanner = new Scanner(System.in);
    System.out.print("Enter an integer for the numerator: ");
    String answer = myScanner.nextLine();
    intA = Integer.parseInt(answer);
    System.out.print("Enter an integer for the denominator: ");
    answer = myScanner.nextLine();
    intB = Integer.parseInt(answer);

    System.out.print("Enter a double for the numerator: ");
    answer = myScanner.nextLine();
    dblC = Double.parseDouble(answer);
    System.out.print("Enter a double for the denominator: ");
    answer = myScanner.nextLine();
    dblD = Double.parseDouble(answer);

    System.out.println("intA/intB = " + intA/intB);
    System.out.println("dblC/dblD = " + dblC/dblD);
    System.out.println("intA/dblD = " + intA/dblD);
    System.out.println("dblC/intB = " + dblC/intB);
  }
}
