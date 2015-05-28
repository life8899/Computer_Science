/**
Executes different searches
@author J. Thompson
@version 05-Jul-11
*/
import java.util.*;
public class SearchTest
{
  public static void main(String[] args)
  {
    Random myRandom = new Random();
    for (int n = 100000; n < 1100000; n=n+100000)
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
      long totalTime = 0;
      for (int i = 0; i < 1000; i++)
      {
        int findThis = myRandom.nextInt(myArray.length);
        long startTime = System.currentTimeMillis();
        //int result = Search.linearSearch(myArray, findThis);
        int result = Search.binarySearch(myArray, findThis);
        long endTime = System.currentTimeMillis();
        totalTime = totalTime + endTime - startTime;
      }
      System.out.println("n: "+myArray.length+"\tTotal time: "+totalTime + " ms");
    }
    System.exit(0);
  }
}
