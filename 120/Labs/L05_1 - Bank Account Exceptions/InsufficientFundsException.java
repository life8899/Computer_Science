
/**
 * Declares a New Exception Type Relating to Insufficient Funds in a Bank Account
 * 
 * @author Nick Alexander
 * @version 9/30/13
 */



public class InsufficientFundsException extends RuntimeException
{   
    public InsufficientFundsException()
    {}
    
    public InsufficientFundsException(String msg)
    {
        super(msg);
    }
}
