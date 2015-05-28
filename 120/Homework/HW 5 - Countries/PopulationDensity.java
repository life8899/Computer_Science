public abstract class PopulationDensity
{
    private double popDens;
    
    /**
     * Instantiates a PopulationDensity Object that calculates the Population Density of the Country
     * @param inPop Population Density of the Country
     */
    public PopulationDensity(double inPopDensity){};
    
    /**
     * Returns the Population Density of a Country
     * @return Population Density of the Country
     */
    public abstract PopulationDensity getPopDesnity();
}