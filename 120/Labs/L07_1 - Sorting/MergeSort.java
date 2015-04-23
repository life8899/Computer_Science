/**
 * Implements the Merge Sort Algorithm
 * 
 * @author J. Fuller
 * @version 22-Jun-2010
 */

public class MergeSort
{
    private int[] myArray;
    
    public MergeSort(int[] inArray)
    {
        myArray = inArray;
    }
    
    public void sort()
    {
        if (myArray.length < 2)
            return;
        int[] left = new int[myArray.length/2];
        System.arraycopy(myArray, 0, left, 0, left.length);
        MergeSort sortLeft = new MergeSort(left);
        sortLeft.sort();
        
        int[] right = new int[myArray.length - left.length];
        System.arraycopy(myArray, left.length, right, 0, right.length);
        MergeSort sortRight = new MergeSort(right);
        sortRight.sort();
        merge(left, right);
    }
    
    private void merge(int[] left, int[] right)
    {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
        {
            if (left[i] < right[j])
                myArray[k++] = left[i++];
            else
                myArray[k++] = right[j++];
        }
        while (i < left.length)
            myArray[k++] = left[i++];
        while (j < right.length)
            myArray[k++] = right[j++];
    }
}