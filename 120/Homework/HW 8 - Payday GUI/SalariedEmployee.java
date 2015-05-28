/**
 * Creates an Employee object with a salary; implements Payday
 * 
 * @author Nick Alexander
 * @version 8/28/13
 */

public class SalariedEmployee implements Payday
{
    private String employeeID;
    private double salary;
    
    /**
     * Creates a SalariedEmployee Object
     * @param inID Employee's ID
     * @param inSalary Employee's Salary
     */
    public SalariedEmployee(String inID, double inSalary)
    {
        employeeID = inID;
        salary = inSalary;
    }
    
    public String payStub()
    {
        return ("ID: " + employeeID + "     " + "Salary: " + salary);
    }
    
    public double findEarnings()
    {
        return salary;
    }
}
