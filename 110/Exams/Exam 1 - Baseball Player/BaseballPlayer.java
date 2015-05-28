
/**
 * Creates a BaseballPlayer object with name, position, and years;
 * 
 * @author Nick Alexander
 * @version 2/13/13
 */
public class BaseballPlayer
{
    private String name;
    private String position;
    private int years;
    
    /**
     * Creates a BaseballPlayer object
     * @param name of player
     * @param playing position
     */
     public BaseballPlayer(String inName, String inPosition)
    {
        name = inName;
        position = inPosition;
    }
    
    /**
     * Returns name of pl
     * @return name of player
     */
    public String getName()
    {
        return name;    
    }
    
    /**
     * Returns position of player
     * @return position of player
     */
    public String getPosition()
    {
        return position;
    }
    
    
    /**
     * Returns years the player played
     * @return number of years player played
     */
    public int getYears()
    {
        return years;
    }
    
    
    /**
     * Sets number of years the player has played
     * @param number of years
     */
    public void setYears(int newYears)
    {
        years = newYears;
    }
}
