package maze;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Module 2;
 * Built Upon Module 1;
 *
 * Tests the MazeReader and MazeSolver
 *
 * @author Nicholas Alexander
 * @author Eli Cabarrus
 *
 * @version 2/28/14 - Updated for Module 2
 */
public class MazeTest
{
    private static Scanner scanner = new Scanner(System.in);
    private static boolean mazeLoaded = true;
    private static boolean formattedCorrectly = true;

    /**
     * Insert Point
     *
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        int choice = 0;
        boolean done = false;
        MazeReader reader = init();
        do
        {
            System.out.print("\n1 - Load Maze from File\n" + "2 - Check Location\n" + "3 - Print Maze\n" + "4 - Solve Maze\n" + "5 - Exit\n" + "Select: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){}

            switch (choice)
            {
                case 1:
                    reader = init();
                    break;

                case 2:
                    if (isProper())
                    {
                        System.out.println("X: ");
                        int x = readInteger();
                        System.out.println("Y: ");
                        int y = readInteger();
                        System.out.println("Z: ");
                        int z = readInteger();
                        try {
                            System.out.println(reader.check(x, y, z));
                        } catch (Exception e) {
                            System.out.println("Error: Point Does Not Exist");
                        }
                    } else
                        System.out.println("Maze is Not Loaded or Not Formatted Correctly!");
                    break;

                case 3:
                    if (isProper())
                        System.out.println(reader.toString());
                    else
                        System.out.println("Maze is Not Loaded or Not Formatted Correctly!");
                    break;

                case 4:
                    if (isProper())
                    {
                        try {
                            MazeSolver solver = new MazeSolver(reader);
                            solver.solveMaze();
                            System.out.println(reader.solutionToString());
                            System.out.println("Solution Point: " + solver.getSolutionPoint());
                            System.out.println("Total Steps Taken: " + solver.getStepsTaken());
                            System.out.println("Steps to Solution: " + solver.getStepsToSolution());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else
                        System.out.println("Maze is Not Loaded or Not Formatted Correctly!");
                    break;

                case 5:
                    done = true;
                    break;

                default:
                    System.out.println("Invalid Input. Please Try Again!");
                    break;
            }
        } while (!done);
        System.exit(0);
    }

    /**
     * Utility Method to Load a File from JFileChooser
     *
     * @return Selected File
     */
    private static File loadFile() throws FileNotFoundException, IOException
    {
        JFileChooser input = new JFileChooser(new File(".").getCanonicalPath());
        input.updateUI();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
        input.setFileFilter(filter);
        int returnVal = 0;
        returnVal = input.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("Loaded Maze File: " + input.getSelectedFile().getName());
            return input.getSelectedFile();
        }
        throw new FileNotFoundException("File Not Selected");
    }

    /**
     * Utility Method to Handle Error Catching for new MazeReader
     *
     * @return newly created MazeReader
     */
    private static MazeReader init()
    {
        MazeReader reader = null;
        formattedCorrectly = true;
        mazeLoaded = true;
        try {
            reader = new MazeReader(loadFile());
        } catch (FileNotFoundException e) {
            mazeLoaded = false;
            System.out.println(e.getMessage());
        } catch (IOException e) {
            mazeLoaded = false;
            System.out.println("Create Maze Reader: IO Error!");
        } catch (MazeFormatException e) {
            formattedCorrectly = false;
            System.out.println(e.getMessage());
        }
        return reader;
    }

    /**
     * Utility Method to Quickly Check if Maze is Loaded and Formatted
     *
     * @return boolean of loaded and formatted
     */
    private static boolean isProper()
    {
        return mazeLoaded && formattedCorrectly;
    }

    /**
     * Utility Method to Handle Errors of Parsing String to an Int
     *
     * @return Parsed integer
     */
    private static int readInteger()
    {
        boolean valid = false;
        int tmp = 0;
        do
        {
            try
            {
                tmp = Integer.parseInt(scanner.nextLine());
                valid = true;
            }
            catch (NumberFormatException e)
            {
                System.out.print("Invalid Input! Please Enter a Number Only!\n" + "Please Try Again: ");
                valid = false;
            }
        } while (!valid);
        return tmp;
    }
}