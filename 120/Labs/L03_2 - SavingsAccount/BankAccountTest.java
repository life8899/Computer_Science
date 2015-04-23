/**
 * BankAccountTest class for BankAccount lab.
 *
 * @author ??
 * @version 29-Jan-10
 */
import java.util.Scanner;
public class BankAccountTest
{
    public static void main(String[] args)
    {
        boolean done = false;
        Scanner myScanner = new Scanner(System.in);
        //instantiate a checking account with an initial balance of $1000
        CheckingAccount myAccount = new CheckingAccount();
        myAccount.deposit(1000);
        SavingsAccount savings = new SavingsAccount(3.6);
        do
        {
            System.out.println("\nEnter menu choice");
            System.out.println("1. Deposit into checking");
            System.out.println("2. Withdraw from checking");
            System.out.println("3. End-of-month processing");
            System.out.println("4. Current Account balances");
            System.out.println("5. Transfer funds from Checking to Savings");
            System.out.println("6. Transfer funds from Savings to Checking");
            System.out.println("7. Exit the program");
            int menuChoice = Integer.parseInt(myScanner.nextLine());
            if (menuChoice == 1)
            {
                System.out.print("\nEnter amount to deposit into checking: ");
                double amount = Double.parseDouble(myScanner.nextLine());
                myAccount.deposit(amount);
            }
            else if (menuChoice == 2)
            {
                System.out.print("\nEnter amount to withdraw from checking: ");
                double amount = Double.parseDouble(myScanner.nextLine());
                myAccount.withdraw(amount);
            }
            else if (menuChoice == 3)
            {
                System.out.println("\nEnd of month processing");
                myAccount.deductFees();
                savings.addInterest();
            }
            else if (menuChoice == 4)
            {
                System.out.println("\nChecking Balance: $" + myAccount.getBalance());
                System.out.println("Savigns Balance: $" + savings.getBalance());
            }
            else if (menuChoice == 5)
            {
                System.out.println("\nChecking to Savings");
                System.out.print("Amount to transfer: ");
                double amount = Double.parseDouble(myScanner.nextLine());
                if (amount < myAccount.getBalance())
                {
                    myAccount.transfer(amount, savings);
                }
                else
                {
                    System.out.println("Insufficient Funds!");
                }
            }
            else if (menuChoice == 6)
            {
                System.out.println("\nSavings to Checking");
                System.out.print("Amount to transfer: ");
                double amount = Double.parseDouble(myScanner.nextLine());
                if (amount < myAccount.getBalance())
                {
                    savings.transfer(amount, myAccount);
                }
                else
                {
                    System.out.println("Insufficient Funds!");
                }
            }
            else if (menuChoice == 7)
            {
                done = true;
                System.out.println("\nGoodbye");
            }
            else
                System.out.println("Invalid choice! Try again, Bozo!");
        } while (!done);
        System.exit(0);
   }
}
