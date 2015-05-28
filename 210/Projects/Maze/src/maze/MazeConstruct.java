package maze; 

/**
 * Module 2
 * Built upon Module 1
 * 
 * Enumerated Type for Each Maze Construct
 * 
 * @author Nicholas Alexander
 * @author Eli Cabarrus
 *
 * @version 2/25/14 - Updated for Module 2
 */
public enum MazeConstruct
{
    WALL        ('#'),
    OPEN        ('.'),
    ELEVATOR    ('+'),
    TRAVERSED   (','),
    START       ('@'),
    FINISH      ('*'),
    SOLUTION    ('!');
    
    private final char SYMBOL;
    /**
    * Creates a Representative Enumeration Constant for a char Value
    *
    * @param symbol Character the Enumeration Constant Represents
    */
    private MazeConstruct (char symbol)
    {
        this.SYMBOL = symbol;
    }
    
    /**
    * Static Method to Return the Representative Enumeration Constant for given char
    *
    * @return Representative Enumeration Constant with Enumeration Type
    */
    public static MazeConstruct getConstruct(char symbol)
    {
        for (MazeConstruct c : MazeConstruct.values())
        {
            if (symbol == c.SYMBOL)
                return c;
        }
        return null;
    }
    
    /**
     * Returns the char Associated with the Supplied Construct
     * 
     * @return char Represented by Construct
     */
    public static char getSymbol(MazeConstruct construct)
    {
        return construct.SYMBOL;
    }
}
