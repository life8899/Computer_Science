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
        do
        {
            System.out.println("Enter menu choice");
            System.out.println("1. Deposit into checking");
            System.out.println("2. Withdraw from checking");
            System.out.println("3. End-of-month processing");
            System.out.println("4. Current checking account balance");
            System.out.println("5. Exit the program");
            try
            {
                int menuChoice = Integer.parseInt(myScanner.nextLine());
                if (menuChoice == 1)
                {
                    System.out.print("Enter amount to deposit into checking: ");
                    try
                    {
                        double amount = Double.parseDouble(myScanner.nextLine());
                        myAccount.deposit(amount);
                    } 
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid Number");
                        System.out.println("Transaction Aborted\n");
                    }
                }
                else if (menuChoice == 2)
                {
                    System.out.print("Enter amount to withdraw from checking: ");
                    try
                    {
                        double amount = Double.parseDouble(myScanner.nextLine());
                        myAccount.withdraw(amount);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid Number");
                        System.out.println("Transaction Aborted\n");
                    }
                    catch (InsufficientFundsException e)
                    {
                        System.out.println(e.getMessage());
                        System.out.println("Transaction Aborted\n");
                    }
                    catch (ApprovalException e)
                    {
                        System.out.println(e.getMessage());
                        System.out.println("Transaction Aborted\n");
                    }
                }
                else if (menuChoice == 3)
                {
                    System.out.println("End of month processing");
                    myAccount.deductFees();
                }
                else if (menuChoice == 4)
                {
                    System.out.println("Checking balance: ");
                    System.out.println("Balance: $" + myAccount.getBalance());
                }
                else if (menuChoice == 5)
                {
                    done = true;
                    System.out.println("Goodbye");
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid Entry, Try Again\n");
            }
        } while (!done);
        System.exit(0);
   }
}
