
/**
 * Creates a Manager Object with a toString Method
 * Extends Employee
 * 
 * @author Nick Alexander
 * @version 9/11
 */



public class Manager extends Employee
{
    private String department;
    
    /**
     * Creates a Manager Object
     * @param aSalary Manager's Salary
     * @param aName Manager's Name
     */
    public Manager(String aName, double aSalary, String aDept)
    {
        super(aName, aSalary);
        this.department = aDept;
    }
    
    /**
     * Describes Manager as a String
     * @param String Description of Manager
     */
    public String toString()
    {
        return super.toString() + " Department: " + this.department;
    }
}
