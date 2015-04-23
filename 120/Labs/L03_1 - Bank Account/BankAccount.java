/**
 * Creates a SuperClass BankAccount
 * 
 * @author Nick Alexander
 * @version 9/9/13
 */

public class BankAccount
{
    private double balance;
    
    public BankAccount()
    {
        this.balance = 0;
    }
    
    public void deposit(double amount)
    {
        this.balance += amount;
    }
    
    public void withdraw(double amount)
    {
        if (amount < this.balance)
        {
            this.balance -= amount;
        }
        else
        {
            System.out.println("Error: Insufficient Funds!");
        }
    }
    
    public double getBalance()
    {
        return this.balance;
    }
}