
/**
 * Calculates the roots and discriminate of a Quadratic
 * 
 * @author Nick Alexander 
 * @version 3/1/12
 */
public class QuadEq
{
    private double a, b, c;
    
    public QuadEq(double inA, double inB, double inC)
    {
        a = inA;
        b = inB;
        c = inC;
    }
    
    public double findDiscrim()
    {
        return b*b-4*a*c;
    }
    
    public double findRoot1()
    {
        return (-b+Math.sqrt(b*b-4*a*c))/(2*a);
    }
    
    public double findRoot2()
    {
        return (-b-Math.sqrt(b*b-4*a*c))/(2*a);
    }
}
