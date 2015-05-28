/**
 * Creates a CheckingAccount object, subclassing BankAccount
 * 
 * @author Nick Alexander
 * @version 9/9/13
 */

public class CheckingAccount extends BankAccount
{
    private int transactionCount;
    
    public CheckingAccount()
    {
        transactionCount = 0;
    }
    
    public void deposit(double amount)
    {
        super.deposit(amount);
        transactionCount++;
    }
    
    public void withdraw(double amount)
    {
        super.withdraw(amount);
        transactionCount++;
    }
    
    public void deductFees()
    {
        if (transactionCount > 3)
        {
            double fee = 2.0 * (transactionCount - 3);
            this.withdraw(fee);
        }
    }
}