/**
Test the Student class
@author Nick Alexander
@version 1/25/13
*/
public class StudentTest
{
  public static void main (String[] args)
  {
    Student aStudent;
    aStudent = new Student("Jim", 2013, "Poca");
    System.out.println("My name is " + aStudent.getName());
    System.out.println("My class year is " + aStudent.getGradYear());
    System.out.println("I am from the state of " + aStudent.getState());
    aStudent.setGradYear(2014);
    aStudent.setState("WV");
    System.out.println("My class year is now " + aStudent.getGradYear());
    System.out.println("I am from the state of " + aStudent.getState());
    Student bStudent;
    bStudent = new Student("Mary", 2011, "Nitro");
    System.out.println("My name is " + bStudent.getName());
    System.out.println("My class year is " + bStudent.getGradYear());
    System.exit(0);
  }
}
