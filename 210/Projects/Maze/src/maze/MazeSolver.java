package maze;

import java.util.ArrayList;

/**
 * Module 3
 * Built Upon Module 2
 *
 * Traverses the Maze to Find a Solution
 *
 * @author Eli Cabarrus
 * @author Nicholas Alexander
 *
 * @version 3/21/14 - Updated for Module 3
 */
public class MazeSolver
{
    private MazeReader maze;
    private MazeStack<Point3D> stack;
    private ArrayList<Point3D> path;
    private Point3D solution;
    private boolean solvable;
    private int stepsTaken;
    private int stepsToSolution;

    /**
     * Creates a MazeSolver to Solve the Maze
     *
     * @param maze MazeReader to Read the Maze File
     *
     * @throws MazeFormatException if Maze is Empty, is not Aligned, has No Start, has No Finish, or has Unpaired Elevators
     */
    public MazeSolver(MazeReader maze) throws MazeFormatException
    {
        this.maze = maze;
        this.stack = new MazeStack<>();
        this.path = new ArrayList<>();
        this.solution = null;
        this.stepsTaken = 0;
        this.stepsToSolution = 0;
    }

    /**
     * Initiates the Solving Process
     *
     * @throws NoSolutionException if Maze has No Solution
     * @throws MazeFormatException
     */
    public void solveMaze() throws NoSolutionException, MazeFormatException
    {
        int[] coords = maze.getStartingPoint();
        Point3D start = new Point3D(coords[0], coords[1], coords[2]);
        this.stack.push(start);
        this.solvable = this.findPath(start);
        this.stepsToSolution += this.stepsTaken;
        this.setSolutionPath();
    }

    /**
     * Recursively Finds the Solution Path
     *
     * @param currentLocation Current Space
     *
     * @return True if Solution Found
     *
     * @throws NoSolutionException if Maze has No Solution
     * @throws MazeFormatException
     */
    public boolean findPath(Point3D currentLocation) throws NoSolutionException, MazeFormatException
    {
        int x = (int) currentLocation.x;
        int y = (int) currentLocation.y;
        int z = (int) currentLocation.z;
        if(maze.check(x,y,z) == MazeConstruct.FINISH)
        {
            this.solution = this.stack.peek();
            return true;
        }
        else
        {
            if (maze.check(x,y,z) != MazeConstruct.START && maze.check(x,y,z) != MazeConstruct.ELEVATOR)
                this.maze.setTraversed(x, y, z);
            return this.findPath(this.move(currentLocation));
        }
    }

    /**
     * Determines Next Open Space and Moves the Solver
     *
     * @param currentLocation Current Space
     *
     * @return Next Space
     *
     * @throws NoSolutionException if No Open Spaces Exist Before Finish
     * @throws MazeFormatException if Elevators are Unpaired or is Unaligned
     */
    private Point3D move(Point3D currentLocation) throws NoSolutionException, MazeFormatException
    {
        int x = (int) currentLocation.x;
        int y = (int) currentLocation.y;
        int z = (int) currentLocation.z;
        Point3D newLocation = currentLocation;

        if (this.maze.check(x, y, z) == MazeConstruct.FINISH)
            return newLocation;
        else if (this.maze.check(x+1, y, z) != MazeConstruct.WALL && this.maze.check(x+1, y, z) != MazeConstruct.TRAVERSED
                    && this.maze.check(x+1, y, z) != MazeConstruct.ELEVATOR)
        {
            newLocation = new Point3D(x+1, y, z);
        }
        else if (this.maze.check(x, y+1, z) != MazeConstruct.WALL && this.maze.check(x, y+1, z) != MazeConstruct.TRAVERSED
                    && this.maze.check(x, y+1, z) != MazeConstruct.ELEVATOR)
        {
            newLocation = new Point3D(x, y+1, z);
        }

        else if (this.maze.check(x, y-1, z) != MazeConstruct.WALL && this.maze.check(x, y-1, z) != MazeConstruct.TRAVERSED
                    && this.maze.check(x, y-1, z) != MazeConstruct.ELEVATOR)
        {
            newLocation = new Point3D(x, y-1, z);
        }
        else if (this.maze.check(x-1, y, z) != MazeConstruct.WALL && this.maze.check(x-1, y, z) != MazeConstruct.TRAVERSED
                    && this.maze.check(x-1, y, z) != MazeConstruct.ELEVATOR)
        {
            newLocation = new Point3D(x-1, y, z);
        }
        else if (this.maze.check(x+1, y, z) == MazeConstruct.ELEVATOR)
        {
            Point3D tmp = this.useElevator(x+1, y, z);
            if (this.canExitElevator(tmp))
                newLocation = tmp;
            else
                newLocation = currentLocation;
        }
        else if (this.maze.check(x, y+1, z) == MazeConstruct.ELEVATOR)
        {
            Point3D tmp = this.useElevator(x, y+1, z);
            if (this.canExitElevator(tmp))
                newLocation = tmp;
            else
                newLocation = currentLocation;
        }
        else if (this.maze.check(x-1, y, z) == MazeConstruct.ELEVATOR)
        {
            Point3D tmp = this.useElevator(x-1, y, z);
            if (this.canExitElevator(tmp))
                newLocation = tmp;
            else
                newLocation = currentLocation;
        }
        else if (this.maze.check(x, y-1, z) == MazeConstruct.ELEVATOR)
        {
            Point3D tmp = this.useElevator(x, y-1, z);
            if (this.canExitElevator(tmp))
                newLocation = tmp;
            else
                newLocation = currentLocation;
        }

        if (newLocation.equals(currentLocation))
        {
            return this.backTrack();
        }

        this.stack.push(newLocation);
        this.path.add(newLocation);
        this.stepsTaken++;
        return newLocation;
    }

    /**
     * Determines if the Elevator has Already Been Used
     *
     * @param elevator Elevator to Test
     *
     * @return True if Elevator has Not Been Used
     */
    private boolean canExitElevator(Point3D elevator)
    {
        int x = (int) elevator.x;
        int y = (int) elevator.y;
        int z = (int) elevator.z;
        if (this.maze.check(x+1,y,z) != MazeConstruct.TRAVERSED && this.maze.check(x+1,y,z) != MazeConstruct.WALL)
            return true;
        else if (this.maze.check(x,y+1,z) != MazeConstruct.TRAVERSED && this.maze.check(x,y+1,z) != MazeConstruct.WALL)
            return true;
        else if (this.maze.check(x,y-1,z) != MazeConstruct.TRAVERSED && this.maze.check(x,y-1,z) != MazeConstruct.WALL)
            return true;
        else if (this.maze.check(x-1,y,z) != MazeConstruct.TRAVERSED && this.maze.check(x-1,y,z) != MazeConstruct.WALL)
            return true;
        return false;
    }

    /**
     * Moves Solver to a New Floor via Elevator
     *
     * @param x X-Coordinate of Elevator
     * @param y Y-Coordinate of Elevator
     * @param z Z-Coordinate of Elevator
     *
     * @return New Space
     *
     * @throws MazeFormatException if Elevators are Unpaired
     */
    private Point3D useElevator(int x, int y, int z) throws MazeFormatException
    {
        for (int newFloor = 0; newFloor < this.maze.getFloors(); newFloor++)
        {
            if (newFloor != z && this.maze.check(x, y, newFloor) == MazeConstruct.ELEVATOR)
            {
                return new Point3D(x, y, newFloor);
            }
        }
        throw new MazeFormatException("Format Error: Unmatched Elevators!");
    }

    /**
     * Moves Backwards Until a New Path is Found or No Path is Found
     *
     * @return New Space
     *
     * @throws NoSolutionException if No Paths are Found
     * @throws MazeFormatException if Maze is Unaligned
     */
    private Point3D backTrack() throws NoSolutionException, MazeFormatException
    {
        Point3D popped = this.stack.pop();
        this.maze.setTraversed((int)popped.x, (int)popped.y, (int)popped.z);
        if (this.stack.peek() == null)
            throw new NoSolutionException();
        this.stepsToSolution--;
        return this.stack.peek();
    }

    /**
     * Sets All Solution Spaces to MazeConstruct.SOLUTION
     */
    private void setSolutionPath()
    {
        while (!this.stack.empty())
        {
            Point3D tmp = this.stack.pop();
            int x = (int) tmp.x;
            int y = (int) tmp.y;
            int z = (int) tmp.z;
            if (this.maze.check(x, y, z) == MazeConstruct.TRAVERSED)
                this.maze.setSolutionPath(x, y, z);

        }
    }

    /**
     * Returns Solution Point
     * @return Solution Point
     */
    public Point3D getSolutionPoint()
    {
        return this.solution;
    }

    /**
     * Total Steps Taken Inside the Maze
     * @return Total Steps Taken
     */
    public int getStepsTaken()
    {
        return this.stepsTaken;
    }

    /**
     * Returns Total Steps to Solution From Start
     *
     * @return Steps to Solution from Start
     */
    public int getStepsToSolution()
    {
        return this.stepsToSolution;
    }

    /**
     * Returns Path Through the Maze
     *
     * @return Path Through the Maze
     */
    public ArrayList<Point3D> getPath()
    {
        return this.path;
    }

    /**
     * True if Point at Index is In Solution Path
     *
     * @param row Row
     * @param col Column
     * @param floor Floor
     *
     * @return True if Index is In Solution Path
     */
    public boolean isInSolutionPath(int row, int col, int floor)
    {
        return this.path.contains(new Point3D(row, col, floor));
    }

    /**
     * True if Maze is Solvable
     * @return True if Maze is Solvable
     */
    public boolean solvable()
    {
        return this.solvable;
    }
}