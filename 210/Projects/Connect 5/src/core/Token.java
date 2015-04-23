package core;

/**
 * Game Pieces for Connect 5
 *
 * @author Nick Alexander
 * @version  5-11-14
 */
public class Token
{
    /**
     * Player to whom the Token Owns
     */
    private Player player;
    /**
     * Row in Which the Token Resides
     */
    private int row;
    /**
     * Column in Which the Token Resides
     */
    private int column;
    /**
     * true if the Token is in a Winning Chain
     */
    private boolean isAWinningToken;

    /**
     * Creates a New Game Token
     * @param row Row where Token will Reside
     * @param column Column where Token will Reside
     * @param player Player to whom the Token Belongs
     */
    public Token(int row, int column, Player player)
    {
            this.player = player;
            this.isAWinningToken = false;
            this.row = row;
            this.column = column;
    }

    /**
     * Returns Row where Token Resides
     * @return Row where Token Resides
     */
    public int getRow()
    {
            return this.row;
    }

    /**
     * Returns Column where Token Resides
     * @return Column where Token Resides
     */
    public int getColumn()
    {
            return this.column;
    }

    /**
     * Returns the Player to whom the Token Belongs
     * @return Player to whom the Token Belongs
     */
    public Player getPlayer()
    {
            return this.player;
    }

    /**
     * Returns true if Token Formed a Winning Chain
     * @return true if Token Formed a Winning Chain
     */
    public boolean isAWinningToken()
    {
            return this.isAWinningToken;
    }

    /**
     * Sets Token as the Token which Forms a Winning Chain
     */
    public void setAsAWinningToken()
    {
            this.isAWinningToken = true;
    }
}
