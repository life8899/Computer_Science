/**
 * Creates an Employee object with a part time job; implements Payday
 * 
 * @author Nick Alexander
 * @version 8/28/13
 */

public class PartTimeEmployee implements Payday
{
    private String employeeID;
    private double hoursWorked;
    private double pay;
    
    /**
     * Creates a PartTimeEmployee Object
     * @param inID Employee's ID
     * @param inHours Hours Worked by Employee
     */
    public PartTimeEmployee(String inID, double inHours)
    {
        employeeID = inID;
        hoursWorked = inHours;
    }
    
    public String payStub()
    {
        return ("Part-Time; " + "ID: " + employeeID + "; Pay: " + pay);
    }
    
    public double findEarnings()
    {
        pay = (hoursWorked * 8);
        return pay;
    }
}