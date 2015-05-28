
/**
 * Using arrays and their methods
 * 
 * @author Nick Alexander
 * @version 4/5/13
 */
public class ArrayTest
{
    public static void main(String[] args)
    {
        //1
        int[] array = {34, 2, 14, 34, 58, 4};
        
        //2
        for (int i = 0; i < array.length; i++)
            {
                System.out.println(array[i]);
            }
            
        //3    
        int max = array[0];
        for (int i = 0; i < array.length; i++)
            {
                if (array[i] > max)
                    {
                        max = array[i];
                    }
            }
        System.out.println("Max: " + max);    
            
        //4    
        int sum = 0;
        for (int i = 0; i < array.length; i++)
            {
                sum = sum + array[i];    
            }
        double avg = (double) sum/array.length;
        System.out.println("Average: " + avg);
        
        //5
        int[] array2 = new int[3];
        System.arraycopy(array, 1, array2, 0, 3);
        
        //6
        for (int temp : array2)
            {
                System.out.println(temp);
            }
    }    
}
