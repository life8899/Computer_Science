package core;

import java.awt.Color;

/**
 * Player Object to Store Player Name, Turn, and Other Useful Information
 *
 * @author Nick Alexander
 * @version 5-11-14
 */
public class Player
{
    /**
     * Name of the Player
     */
    private String name;
    /**
     * Which Turn the Player Takes
     */
    private int turn;
    /**
     * Color Associated with the Player on the Game Board
     */
    private Color color;

    /**
     * Creates a Player with a Default Name
     * @param turn When this Player should Take his/her Turn
     */
    public Player(int turn)
    {
        this.name = "Player " + turn;
        this.turn = turn;
        this.color = findColor(turn);
    }

    /**
     * Creates a Player with a Given Name
     * @param name Player's Name
     * @param turn When this Player should Take his/her Turn
     */
    public Player(String name, int turn)
    {
        this.name = name;
        this.turn = turn;
        this.color = findColor(turn);
    }

    /**
     * Finds the Player's Color on the Game Board
     * @param turn Player's Turn Order
     * @return Color Associated with Player on the Game Board
     */
    private static Color findColor(int turn)
    {
        switch (turn)
        {
            case 1: return Color.RED;
            case 2: return Color.BLUE;
            case 3: return Color.GREEN;
            case 4: return Color.CYAN;
            case 5: return Color.MAGENTA;
        }
        return Color.WHITE;
    }

    /**
     * Return Player's Name
     * @return Player's Name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns Player's Turn
     * @return Player's Turn
     */
    public int getTurn()
    {
        return this.turn;
    }

    /**
     * Returns Color Associated with the Player on the Game Board
     * @return Color Associated with the Player on the Game Board
     */
    public Color getColor()
    {
        return this.color;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
                return false;
        else if (other == this)
                return true;
        else if (!(other instanceof Player))
                return false;
        else
        {
                Player tmp = (Player)other;
                if (tmp.getName().equals(this.getName()) && tmp.getTurn() == this.getTurn())
                        return true;
        }
        return false;
    }
}