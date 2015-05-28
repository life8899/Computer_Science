package core;

/**
 * Game Controller for Connect 5
 *
 * @author Nick Alexander
 * @version 5-11-14
 */
public class Controller
{
    /**
     * Array of Players that are Playing the Game
     */
    private Player[] players;
    /**
     * Player who is Currently Taking Their Turn
     */
    private Player currentPlayer;
    /**
     * Game Board on which the Game is being Played
     */
    private GameBoard board;
    /**
     * true if the Game is Won
     */
    private boolean isWon;

    /**
     * Creates a Game Controller
     * @param boardSize Square Size of the Game Board
     * @param totalPlayers Number of Players Playing the Game
     */
    public Controller(int boardSize, int totalPlayers)
    {
            this.players = new Player[totalPlayers];
            this.board = new GameBoard(boardSize);
            this.isWon = false;
    }

    /**
     * Adds a Player to the Game
     * @param player Player to Add
     */
    public void addPlayer(Player player)
    {
        this.players[player.getTurn() - 1] = player;
        if (player.getTurn() == 1)
            this.currentPlayer = player;
    }

    /**
     * Creates a New Player to Play the Game with a Default Name
     * @param turn Which Turn the New Player will Take
     */
    public void addPlayer(int turn)
    {
            this.players[turn - 1] = new Player(turn);
            if (turn == 1)
                this.currentPlayer = players[turn - 1];
    }

    /**
     * Creates a New Player to Play the Game
     * @param name Name of the New Player
     * @param turn Which Turn the New Player will Take
     */
    public void addPlayer(String name, int turn)
    {
            this.players[turn - 1] = new Player(name, turn);
            if (turn == 1)
                this.currentPlayer = players[turn - 1];
    }

    /**
     * Places a Token on the Board
     * @param column Column in which Token Resides
     * @return true if Token Placed Successfully
     */
    public boolean placeToken(int column)
    {
            return this.board.placeToken(column, this.currentPlayer);
    }

    /**
     * Transitions to Next Player in the Turn Order
     */
    public void nextPlayer()
    {
            int turn = currentPlayer.getTurn();
            if (turn == players.length)
                    this.currentPlayer = players[0];
            else
                    this.currentPlayer = players[turn];
    }

    /**
     * Checks Game Board for a Winner
     * @return true if a Winning Chain was Found
     */
    public boolean checkWinner()
    {
            return this.board.checkWinner(this.currentPlayer);
    }

    /**
     * Creates a New Game Controller with the Same Settings for a New Game
     * @return New Game Controller with the Same Settings for a New Game
     */
    public Controller reset()
    {
        int newBoardSize = this.getBoardSize();
        int newPlayers = this.getPlayers();
        Controller newController = new Controller(newBoardSize, newPlayers);
        for (Player player : this.players)
            newController.addPlayer(player);
        return newController;
    }

    /**
     * Sets if the Game is Won
     * @param won true if the Game is Won
     */
    public void isWon(boolean won)
    {
            isWon = won;
    }

    /**
     * Returns true if the Game is Won
     * @return true if the Game is Won
     */
    public  boolean isWon()
    {
            return isWon;
    }

    /**
     * Returns the Player who is Currently Taking Their Turn
     * @return Player who is Currently Taking Their Turn
     */
    public  Player getCurrentPlayer()
    {
            return currentPlayer;
    }

    /**
     * Returns the Number of Players Playing the Game
     * @return Number of Players Playing the Game
     */
    public int getPlayers()
    {
            return this.players.length;
    }

    /**
     * Returns Player who Takes the Given Turn in the Turn Order
     * @param turnIndex Player's Turn
     * @return Player who Takes the Given Turn
     */
    public Player getPlayer(int turnIndex)
    {
            return this.players[turnIndex];
    }

    /**
     * Returns the Square Board Size
     * @return Square Board Size
     */
    public int getBoardSize()
    {
            return this.board.getSize();
    }

    /**
     * Returns Last Token Placed
     * @return Last Token Placed
     */
    public Token getLastToken()
    {
        return this.board.getLastToken();
    }
}