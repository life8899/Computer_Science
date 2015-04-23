/*
Exercise various sort methods
@author J. Thompson
@version 08-Jul-2010
*/
import java.util.*;
public class SortTest
{
  public static void main(String[] args)
  {
    Random myRandom = new Random();
    long startTime = 0, endTime = 0, elapsedTime = 0;
    for (int n = 20000; n <= 140000; n=n+20000)
    {
      int[] myArray = new int[n];
      for (int i = 0; i < myArray.length; i++)
        myArray[i] = i;
      for (int k = 0; k < 1000000; k++)
      {
        int i = myRandom.nextInt(myArray.length);
        int j = myRandom.nextInt(myArray.length);
        int temp = myArray[i];
        myArray[i] = myArray[j];
        myArray[j] = temp;
      }
      System.out.println("Processing " + n + " elements");
      int[] myArray2 = new int[n];
      System.arraycopy(myArray, 0, myArray2, 0,  myArray.length);
      startTime = System.currentTimeMillis();
      Sort.selectionSort(myArray2);
      endTime = System.currentTimeMillis();
      elapsedTime = endTime - startTime;
         System.out.print("Selection sort: "+elapsedTime+" milliseconds");
      int[] myArray3 = new int[n];
      System.arraycopy(myArray, 0, myArray3, 0,  myArray.length);
      startTime = System.currentTimeMillis();
      MergeSort sort = new MergeSort(myArray3);
      sort.sort();
      //Arrays.sort(myArray3);      
      endTime = System.currentTimeMillis();
      elapsedTime = endTime - startTime;
         System.out.println("\tMerge Sort: "+elapsedTime + " milliseconds");
    }
    System.out.println("Good bye");
    System.exit(0);
  }
}
