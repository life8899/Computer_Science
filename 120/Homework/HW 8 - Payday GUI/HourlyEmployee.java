/**
 * Creates an Employee object with an hourly wage; implements Payday
 * 
 * @author Nick Alexander
 * @version 8/28/13
 */

public class HourlyEmployee implements Payday
{
    private String employeeID;
    private double hoursWorked;
    private double pay;
    
    /**
     * Creates an HourlyEmployee Object
     * @param inID Employee's ID
     * @param inSalary Hours worked by Employee
     */
    public HourlyEmployee(String inID, double inHours)
    {
        employeeID = inID;
        hoursWorked = inHours;
    }
    
    public String payStub()
    {
        pay = findEarnings();
        return ("ID: " + employeeID + "     " + "Pay: " + pay);
    }
    
    public double findEarnings()
    {
        if (hoursWorked > 40)
        {
            pay = ((hoursWorked - 40) * 15) + (40 * 10);
        }
        else
        {
            pay = hoursWorked * 10;
        }
        
        return pay;
    }
}
