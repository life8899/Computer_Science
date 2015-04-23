package Nim;

/**
 * Creates a New Game of Nim
 *
 * @author Nick Alexander
 * @version Nov 27, 2013
 */
public class Game {
    private AI ai;
    private int playerTurn;
    private boolean twoPlayer;
    private boolean hasWon;
    
    /**
     * Create a Game of Nim Object
     * 
     * @param playerTurn Starting Player
     * @param isTwoPlayer Boolean if the Game is Two Player
     */
    public Game(int playerTurn, boolean isTwoPlayer)
    {
        this.playerTurn = playerTurn;
        this.twoPlayer = isTwoPlayer;
    }
    
    /**
     * Returns the Current Player
     * 
     * @return Current Player
     */
    public int getPlayer()
    {
        return playerTurn;
    }
    
    /**
     * Returns a Boolean Value for Game is Won
     * 
     * @return Boolean Value for Game is Won
     */
    public boolean hasWon()
    {
        return this.hasWon;
    }
    
    /**
     * Cycles to the Next Player
     * Calls for the AI to Move when Necessary
     */
    public void nextPlayer()
    {
        if (this.playerTurn == 1)
        {
            if (!this.twoPlayer)
            {
                if (ai == null)
                    ai = new AI();
                playerTurn++;
                ai.findBestMove(); 
            }
            else
            {
                playerTurn++;
            }
        }
        else
            playerTurn--;
    }
    
    /**
     * Sets the Player Turn for the AI
     * 
     * @param player New Player Turn Value
     */
    public void setPlayerTurn(int player)
    {
        this.playerTurn = player;
    }
    
    /**
     * Sets the Value for hasWon
     * 
     * @param hasWon Boolean Value of the Game has been Won
     */
    public void setHasWon(boolean hasWon)
    {
        this.hasWon = hasWon;
    }
    
    /**
     * Returns a New AI Object for Lazy Instantiation
     * 
     * @return Lazily Instantiated AI Object
     */
    public AI newAI()
    {
        if (this.twoPlayer)
            return null;
        return new AI();
    }
    
    /**
     * Forces the AI to Move if It has Starting Move
     */
    public void forceAIMove()
    {
        if (!this.twoPlayer && playerTurn == 2)
        {
            playerTurn = 1;
            nextPlayer();
        }
    }
}