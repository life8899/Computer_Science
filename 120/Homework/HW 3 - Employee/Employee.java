
/**
 * Creates an Employee Object wtih a toString Method
 * 
 * @author Nick Alexander
 * @version 9/11
 */

public class Employee
{
    private String name;
    private double salary;
    
    /**
     * Creates an Employee Object
     * @param aName Employee's Name
     * @param aSalary Employee's Salary
     */
    public Employee(String aName, double aSalary)
    {
        this.name = aName;
        this.salary = aSalary;
    }
    
    /**
     * Describes Employee as a String
     * @return String Description of Employee
     */
    public String toString()
    {
        return name + "; Salary: $" + salary +";";
    }
}
