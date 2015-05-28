
/**
 * Describes 3 methods in which employees are paid
 * 
 * @author Nick Alexander
 * @version 08/28/13
 */

/**
 * Describes Abstract Methods for Payday
 */
public interface Payday
{
    /**
     * Calculuates the Earnings
     * @return Total Earnings
     */
    public double findEarnings();
    
    /**
     * Returns the payStub
     * @return String of PayStub
     */
    public String payStub();
}
