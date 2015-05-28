
/**
 * Using For Loops
 * 
 * @author Nick Alexander
 * @version 3/11/13
 */
public class ForLoop
{
    public static void main(String[] args)
    {
        double p = 1000;
        System.out.println("Principal = 1000");
        for (int y = 0; y <= 20; y++)
        {
            p = p*1.03;
        }
        System.out.printf("After 20 years: %.2f", p);
        System.out.println();
        int rows = 6;
        int cols = 6;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
                System.out.print("+");  
            System.out.println();
        }
        
        System.out.println();
        
        int rows1 = 6;
        int cols1 = 6;
        for (int i = 0; i < rows1; i++)
        {
                
        }
    }
}
