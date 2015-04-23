package TaxTracker;

import java.util.Comparator;

/**
 * Compares Student Workers Alphabetically Based on Name
 *
 * @author Nick Alexander
 * @author Brandon Houser
 * @version December 3, 2013
 */
public class CompareStudentName implements Comparator<Student>
{
    /**
     * Compares Students by Name
     */
    @Override
    public int compare(Student a, Student b)
    {
        return a.getName().compareTo(b.getName());
    }
}
