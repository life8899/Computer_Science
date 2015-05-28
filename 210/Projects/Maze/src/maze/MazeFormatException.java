package maze; 

/**
 * Module 2
 * 
 * Exception Indicating that Maze is not Formatted Correctly
 * 
 * @author Eli Cabarrus
 * 
 * @version 2/28/14
 */
public class MazeFormatException extends Exception
{
    /**
     * Exception Indicating that Maze is not Formatted Correctly
     */
    public MazeFormatException(String message)
    {
        super(message);
    }
}