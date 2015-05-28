 

/**
 * Creates an Employee object storing name and salary
 * @author Nick Alexander
 * @version 1/25/13
*/

public class Employee
{
	private String name;
	private double salary;

	/*Constructor method: Creates a new employee object
      @param: String inName - set name of employee
      @param: double Salary - set salary of employee
	*/
	public Employee(String inName, double inSalary)
	{
		name = inName;
		salary = inSalary;
	}

	/*Displays employee's name
	@return: String, Employee's name
	 */
	public String getName()
	{
		return name;
	}

	/*Displays employee's salary
	@return: double, Employee's salary
	 */
	public double getSalary()
	{
		return salary;
	}

	/*Increases employee's salary by given percentage
	@param: double byPercent - percentage by which salary increases.
	 */
	public void raiseSalary(double byPercent)
	{
		salary = (salary * byPercent/100) + salary;
	}

}
