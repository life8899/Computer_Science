/**
Static search methods
@author J. Thompson
@version 05-Jul-11
*/
public class Search
{
   public static int linearSearch(int[] a, int item)
   {
      for (int i = 0; i < a.length; i++)
         if (a[i] == item)
            return i;
      return -1;
   }
   
   public static int binarySearch(int[] a, int item)
   {
       int startIndex = 0;
       int endIndex = a.length - 1;
       int midIndex;
       while (startIndex <= endIndex)
       {
           midIndex = (startIndex + endIndex)/2;
           if (a[midIndex] == item)
               return midIndex;
           else if (a[midIndex] < item)
               endIndex = midIndex - 1;
           else
               startIndex = midIndex + 1;
       }
       return -1;
   }
}
