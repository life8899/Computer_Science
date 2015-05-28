 

/**
 * Initializes two employee objects to test Employee Class
 * @author Nick Alexander
 * @version 1/25/13
 */
public class EmployeeTest
{
    /*
     * Initializes employees a and b with name and salary;
     * Increases salaries by given percentage
     */
    public static void main(String[] args)
    {
        //Initialize employee a
        Employee a = new Employee("Hacker, Harry", 50000);
        System.out.println("Employee name is: " + a.getName());
        System.out.println("Employee salary is:" + a.getSalary());

        //Raise a's salary
        a.raiseSalary(10);
        System.out.println("Employee's new salary after 10% raise is: " + a.getSalary());

        //Initialize employee b
        Employee b = new Employee("Accountant, Jim", 100000);
        System.out.println("Employee name is: " + b.getName());
        System.out.println("Employee salary is:" + b.getSalary());

        //Raise b's salary
        b.raiseSalary(10);
        System.out.println("Employee's new salary after 10% raise is: " + b.getSalary());
    }
}
