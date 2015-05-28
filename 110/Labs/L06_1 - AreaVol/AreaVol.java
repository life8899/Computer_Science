
/**
 * Constructor and Methods for finding Area and Volume of a cylinder and sphere
 * 
 * @author Nick Alexander 
 * @version 2/22/13
 */
public class AreaVol
{
    public static double findCylinderSurfaceArea(double r, double h)
    {
        return 2*Math.PI*r*h + 2*Math.PI*Math.pow(r, 2);
    }
    
    public static double findCylinderVolume(double r, double h)
    {
        return Math.PI*Math.pow(r, 2)*h;
    }
    
    public static double findSphereSurfaceArea(double rS)
    {
        return 4*Math.PI*(rS*rS);
    }
    
    public static double findSphereVolume(double rS)
    {
        return (4.0/3.0)*Math.PI*(Math.pow(rS, 3));
    }
        
}
