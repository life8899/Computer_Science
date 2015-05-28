
/**
 * Implements Comparator to sort Students by Name
 * 
 * @author Nick Alexander
 * @version 10/9/13
 */

import java.util.*;

public class StudentName implements Comparator<Student>
{
    /**
     * Sorts Students by Name
     * @param a First Student
     * @param b Second Student
     * @return Equality Result
     */
    public int compare(Student a, Student b)
    {
        return a.getName().compareTo(b.getName());
    }
}
