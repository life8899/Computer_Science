/**
 * Creates a SavingsAccount object subclassing BankAccount
 * 
 * @author Nick Alexander
 * @version 9/11/13
 */

public class SavingsAccount extends BankAccount
{
    private double interestRate;
    
    public SavingsAccount(double rate)
    {
        this.interestRate = rate;
    }
    
    public void addInterest()
    {
        this.deposit(this.getBalance() * this.interestRate/100);
    }
}