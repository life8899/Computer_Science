
/**
 * Write a description of class ForWhileDo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ForWhileDo
{
    public static void main(String[] args)
    {
        //sum numbers from 1 to 5
        int sum = 0;
        for(int i = 1; i<6; i++)
        {
            sum = sum+i;
        }
        System.out.println("The sum is: " + sum);
        System.out.println();
        
        //sum numbers from 1 to 10
        sum = 0;
        int i = 0;
        while (i<10)
        {
            i++;
            sum = sum + i;
        }
        System.out.println("The sum is: " + sum);
        System.out.println();
        
        //sum numbers from 1 to 15
        sum = 0;
        i = 0;
        do
        {
            i++;
            sum = sum + i;
        } while (i<15);
        System.out.println("The sum is: " + sum);
    }
}
