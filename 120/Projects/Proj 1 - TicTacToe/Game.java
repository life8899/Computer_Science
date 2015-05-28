 

/**
 * Game Class
 * Handles All Game Related Functions for TicTacToe
 * (e.g. Switch Players, Checking for a Win, etc.)
 * 
 * @author Nicholas Alexander
 * @version 10/21/13
 */
public class Game {
    public static final int ROWS = 5;
    public static final int COLS = 5;
    
    private static int col;
    private static int row;
    private static int player;
    private static int starter;
    private static int[][] board = new int[ROWS][COLS];
    
    /**
     * Instantiates a Game Object to Initialize the Game
     * Calls the Constructor for the GUI
     */
    public Game()
    {
        new GUI();        
    }
    
    /**
     * Sets the Starting Player
     * @param starter Player Who Moves First
     */
    public static void setPlayer(int thePlayer)
    {
        player = thePlayer;
        if (thePlayer == 1)
            starter = 2;
        else
            starter = 1;
    }
    
    /**
     * Returns Associated Player Value at Index[row][column]
     * @param row Row Index
     * @param col Column Index
     * @return Player Value
     */
    public static int getBoardValue(int row, int col)
    {
        return board[row][col];
    }
    
    /**
     * Returns Column Index of Most Recent Move
     * @return Column Index of Most Recent Move
     */
    public static int getLastCol()
    {
        return col;
    }
    
    /**
     * Returns Row Index of Most Recent Move
     * @return Row Index of Most Recent Move
     */
    public static int getLastRow()
    {
        return row;
    }
    
    /**
     * Returns the Current Player
     * @return Current Player
     */
    public static int getPlayer()
    {
        return player;
    }
    
    /**
     * Switches Player
     * Calls AI Move when Appropriate
     */
    public static void nextPlayer()
    {
        if (player == 1)
        {
            player++;
            AI.move();
        }
        else
            player--;
    }
    
    /**
     * Reinitializes the Game to Play Again
     */
    public static void playAgain()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                setBoardValue(row, col, 0);
            }
        }
        if (starter == 1)
            player = starter++;
        else
            player = starter--;
        GUI.resetLabel();
        GUI.resetButtons();
        GUI.updateGUI();
        if (player == 2)
            AI.move();
    }
    
    /**
     * Returns Current Player's Piece as a String
     * @return Current Player's Piece
     */
    public static String playerToString()
    {
        if(player == 1)
            return "X";
        else
            return "O";
    }
    
    /**
     * Sets the Board Value to Current Player
     * @param row Row Index
     * @param col Column Index
     * @param value Player Value
     */
    public static void setBoardValue(int row, int col, int player)
    {
        board[row][col] = player;
    }
    
    /**
     * Updates col to Most Recent Move
     * @param newCol Latest Column Choice
     */
    public static void setCol(int newCol)
    {
        col = newCol;
    }
    
    /**
     * Updates row to Most Recent Move
     * @param newRow Latest Row Choice
     */
    public static void setRow(int newRow)
    {
        row = newRow;
    }

    /**
     * Tests the Game Board for a Draw
     * @return Draw State
     */
    public static boolean testDraw()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    
    /**
     * Tests the Game Board for a Winner Using Latest Move as a Reference Point
     * @param row Row Index of Latest Move
     * @param col Column Index of Latest Move
     * @param player Player Value Associated with Latest Move
     * @return Win State
     */
    public static boolean testWinner(int row, int col, int player)

    {
        if (Game.getBoardValue(row, 0) == player && Game.getBoardValue(row, 1) == player &&
                Game.getBoardValue(row, 2) == player && Game.getBoardValue(row, 3) == player &&
                Game.getBoardValue(row, 4) == player || Game.getBoardValue(0, col) == player && 
                    Game.getBoardValue(1, col) == player && Game.getBoardValue(2, col) == player &&
                        Game.getBoardValue(3, col) == player && Game.getBoardValue(4, col) == player ||
                        Game.getBoardValue(0, 0) == player && Game.getBoardValue(1, 1) == player &&
                        Game.getBoardValue(2, 2) == player && Game.getBoardValue(3, 3) == player &&
                        Game.getBoardValue(4, 4) == player || Game.getBoardValue(4, 0) == player &&
                            Game.getBoardValue(3, 1) == player && Game.getBoardValue(2, 2) == player
                            && Game.getBoardValue(1, 3) == player && Game.getBoardValue(0, 4) == player )
        {
            return true;
        }
        return false;
    }
}