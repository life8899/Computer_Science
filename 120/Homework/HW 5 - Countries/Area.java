/**
 * Area Object for a Country
 * 
 * @author Nick Alexander
 * @version 10/3/13
 */

public abstract class Area
{
    private double area;
    
    /**
     * Instantiates an Area Object containing the Area of the Country
     * @param inArea Area of the Country
     */
    public Area (double inArea){};
    
    /**
     * Returns the Area
     * @return Area of Country
     */
    public abstract Area getArea();
}