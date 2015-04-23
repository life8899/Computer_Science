package maze;

/**
 * Module 2
 * 
 * Exception Indicating that Maze Does Not Have a Solution
 * 
 * @author Eli Cabarrus
 * 
 * @version 2/25/14
 */
public class NoSolutionException extends Exception
{
    /**
     * Exception Indicating that Maze Does Not Have a Solution
     */
    public NoSolutionException()
    {
        super("Maze has No Solution");
    }
}