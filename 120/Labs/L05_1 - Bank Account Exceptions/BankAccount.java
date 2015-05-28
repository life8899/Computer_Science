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
        if (amount <= this.balance)
        {
            this.balance -= amount;
        }
        else if (amount > 2000 && amount <= this.balance)
        {
            ApprovalException approval = new ApprovalException("Requires Manager Approval!");
            throw approval;
        }
        else
        {
            InsufficientFundsException funds = new InsufficientFundsException("Amount exceeds Balance!");
            throw funds;
        }
    }
    
    public double getBalance()
    {
        return this.balance;
    }
}