
/**
 * Declares an Exception Type relating to Transactions Greater than $2,000
 * 
 * @author Nick Alexander
 * @version 9/30/13
 */



public class ApprovalException extends RuntimeException
{
    public ApprovalException()
    {}
    
    public ApprovalException(String msg)
    {
        super(msg);
    }
}
