/**
 * Country Object containing a name, Area, Population, and Population Density
 * 
 * @author Nick Alexander
 * @version 10/3/13
 */

public abstract class Country
{
    private String name;
    private Area area;
    private Population population;
    private PopulationDensity popDensity;
    
    /**
     * Instantiates a Country Object with a Name, Area, Population, and Population Density
     * @param inName Name of the Country
     * @param inArea Area of the Country
     * @param inPopulation Population of the Country
     * @param inPopDen Population Density of the Country
     */
    public Country(String inName, Area inArea, Population inPopulation, PopulationDensity inPopDen){};
    
    /**
     * Returns Country in String Format
     * @return Country in String Format
     */
    public abstract String toString();
    
    /**
     * Finds the Country with the Largest Area
     * @return Country with the Largest Area
     */
    public abstract Country findLargestArea();
    
    /**
     * Finds the Country with the Largest Population
     * @return Country with the Largest Population
     */
    public abstract Country findLargestPop();
    
    /**
     * Finds the Country with the Largest Population Density
     * @return Country with the Lagest Population Density
     */
    public abstract Country findLargestPopDensity();
}