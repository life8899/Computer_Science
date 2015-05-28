/**
 * Population Object of a Country
 * 
 * @author Nick Alexander
 * @version 10/3/13
 */

public abstract class Population
{
    private int population;
    
    /**
     * Instantiates a Population Object containing the Population of the Country
     * @param inPop Population of the Country
     */
    public Population (int inPop){};
    
    /**
     * Returns the Population of Country
     * @return Population of Country
     */
    public abstract Population getPop();
}