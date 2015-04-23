package maze;

import java.io.*;
import java.util.Scanner;

/**
 * Module 3
 * Built Upon Module 1 and Module 2
 *
 * Reads the Maze Configuration from File
 *
 * @author Nicholas Alexander
 * @author Eli Cabarrus
 *
 * @version 3/21/14 - Updated for Module 3
 */
public class MazeReader
{
    private boolean formattedProperly;
    private final int mazeLength;
    private final int mazeWidth;
    private final int mazeFloors;
    private MazeConstruct[][][] maze;
    private String mazeString;
    private String mazeWithoutLineBreak;

    /**
     * Reads Maze from File using Scanner
     *
     * @param mazeFile Name of the Maze File
     *
     * @throws FileNotFoundException if File is Not Found by JFileChooser
     * @throws IOException for File Input/Output Errors
     * @throws MazeFormatException if Maze is Empty, Incorrectly Aligned, or has No Starting Point
     */
    public MazeReader(File mazeFile) throws FileNotFoundException, IOException, MazeFormatException
    {
        this.formattedProperly = true;
        this.mazeString = readFile(mazeFile);
        this.mazeWithoutLineBreak = removeNewLine();
        this.mazeLength = findLength();
        this.mazeWidth = findWidth();
        this.mazeFloors = findFloors();
        this.maze = new MazeConstruct[mazeLength][mazeWidth][mazeFloors];
        parseMaze();
    }

    /**
     * Scans the Maze File and Produces a String Representation
     *
     * @param mazeFile User-Selected Maze File
     *
     * @return String Representation of the Maze
     */
    private String readFile(File mazeFile) throws FileNotFoundException, IOException, MazeFormatException
    {
        FileReader reader = new FileReader(mazeFile);
        Scanner scanner = new Scanner(reader);
        String parsed = "";
        while (scanner.hasNextLine())
        {
            String nextLine = scanner.nextLine();
            parsed = parsed + nextLine + "\n";
        }

        if (parsed.isEmpty())
        {
            this.formattedProperly = false;
            throw new MazeFormatException("Format Error: Empty Maze!");
        }
        reader.close();
        scanner.close();
        return parsed;
    }

    /**
     * Parses the Maze into Individual Characters for Array of MazeConstruct
     * Allows for Quick Checking
     */
    private void parseMaze()
    {
        String localMaze = this.mazeWithoutLineBreak;
        int count = 0;
        for (int z = 0; z < this.mazeFloors; z++)
        {
            for (int y = 0; y < this.mazeWidth; y++)
            {
                for (int x = 0; x < this.mazeLength; x++)
                {
                    this.maze[x][y][z] = MazeConstruct.getConstruct(localMaze.charAt(count));
                    count++;
                }
            }

            for (int i = 0; i < this.mazeLength; i++)
            {
                localMaze = localMaze.replaceFirst("-", "");
            }
        }
    }

    /**
     * Strips All Newline Characters from Maze String for Utility
     *
     * @return Full Maze as String without Line Breaks
     */
    private String removeNewLine()
    {
        return this.mazeString.replaceAll("\n", "");
    }

    /**
     * Scans the Maze to find Length
     *
     * @return Length of the maze
     */
    private int findLength() throws MazeFormatException
    {
        boolean firstPass = true;
        int length = 0;
        int testLength = 0;
        Scanner scanner = new Scanner(this.mazeString);
        while (scanner.hasNextLine())
        {
            testLength = length;
            length = scanner.nextLine().length();
            if (!firstPass && testLength != length)
            {
                this.formattedProperly = false;
                throw new MazeFormatException("Format Error: Inconsistent Row Length!");
            }
            firstPass = false;
        }
        return length;
    }

    /**
     * Computes the Width (y) of the Maze
     *
     * @return Width (y) of Maze
     */
    private int findWidth()
    {
        String mazeArea = this.mazeWithoutLineBreak.substring(0, this.mazeWithoutLineBreak.indexOf("-"));
        return mazeArea.length() / this.mazeLength;
    }

    /**
     * Computes the Number of Floors (z) of the Maze
     *
     * @return Number of Floors (z) of Maze
     */
    private int findFloors()
    {
        String charsUntilFloorBreak = this.mazeString.substring(0, this.mazeString.indexOf("-"));
        return this.mazeWithoutLineBreak.length() / charsUntilFloorBreak.length();
    }

    /**
     * Marks Maze Space as Traversed
     *
     * @param x Length Position of Space on Floor
     * @param y Width Position of Space on Floor
     * @param z Floor on which Space Exists
     */
    public void setTraversed(int x, int y, int z)
    {
        this.maze[x][y][z] = MazeConstruct.TRAVERSED;
    }

    /**
     * Marks Maze Space as Part of Solution Path
     *
     * @param x Length Position of Space on Floor
     * @param y Width Position of Space on Floor
     * @param z Floor on which Space Exists
     */
    public void setSolutionPath(int x, int y, int z)
    {
        this.maze[x][y][z] = MazeConstruct.SOLUTION;
    }

    /**
     * Returns Character at 3D Index
     *
     * @param x Length Position of Construct on Floor
     * @param y Width Position of Construct on Floor
     * @param z Floor on which Construct Exists
     *
     * @return Construct at Given Location
     */
    public MazeConstruct check(int x, int y, int z)
    {
        return maze[x][y][z];
    }

    /**
     * Returns Length of the Maze
     *
     * @return Length of the Maze
     */
    public int getLength()
    {
        return this.mazeLength;
    }

    /**
     * Returns Width of the Maze
     *
     * @return Width of the Maze
     */
    public int getWidth()
    {
        return this.mazeWidth;
    }

    /**
     * Returns Number of Floors in Maze
     *
     * @return Number of Floors in Maze
     */
    public int getFloors()
    {
        return this.mazeFloors;
    }

    public boolean isFormattedProperly()
    {
        return this.formattedProperly;
    }

    /**
     * Locates Starting Coordinates |
     * array[0] = x coordinate | array[1] = y coordinate |
     * array[2] = floor coordinate
     *
     * @return Array of Coordinates of Starting Point
     *
     */
    public int[] getStartingPoint() throws MazeFormatException
    {
        for (int z = 0; z < this.mazeFloors; z++)
        {
            for (int y = 0; y < this.mazeWidth; y++)
            {
                for (int x = 0; x < this.mazeLength; x++)
                {
                    if (this.check(x,y,z) == MazeConstruct.START)
                    {
                        int[] array = {x, y, z};
                        return array;
                    }
                }
            }
        }
        throw new MazeFormatException("Starting Point Does Not Exist!");
    }

    /**
     * Returns String Representation of Entire Maze
     *
     * @return String Representation of the entire maze
     */
    public String toString()
    {
        return this.mazeString;
    }

    /**
     * Returns Solution as a Formatted String
     *
     * @return Solution as a Formatted String
     */
    public String solutionToString()
    {
        String solution = "";
        for (int z = 0; z < this.mazeFloors; z++)
        {
            for (int y = 0; y < this.mazeWidth; y++)
            {
                for (int x = 0; x < this.mazeLength; x++)
                {
                    solution += String.valueOf(MazeConstruct.getSymbol(this.maze[x][y][z]));
                }
                solution += "\n";
            }

            for (int i = 0; i < this.mazeLength; i++)
            {
                solution += "-";
            }
            solution += "\n";
        }
        return solution;
    }
}
