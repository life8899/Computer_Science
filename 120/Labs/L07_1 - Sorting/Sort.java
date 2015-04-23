/** 
 * Creates a Selection Sort Algorithm
 * 
 * @author Nick Alexander
 * @version 10/7/13
 */

public class Sort
{
    /**
     * Selection Sort
     * @param a Array to be Sorted
     */
    public static void selectionSort(int[] a)
    {
        for (int i = 0; i < a.length-1; i++)
        {
            int minIndex = i;
            for (int k = i+1; k < a.length; k++)
            {
                if (a[k] < a[minIndex])
                {
                    minIndex = a[k];
                }
                
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }
}