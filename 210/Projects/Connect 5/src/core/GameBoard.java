package core;

/**
 * Handles the Connect 5 Game Board
 *
 * @author Nick Alexander
 * @version 5-11-14
 */
public class GameBoard
{
    /**
     * Square Size of the Game Board
     */
    private int size;
    /**
     * Two Dimensional Array of Tokens to Represent Game Board State
     */
    private Token[][] board;
    /**
     * Last Token Placed onto Game Boards
     */
    private Token lastToken;
    /**
     * Array of Tokens in a Winning Chain
     */
    private Token[] winningTokens;

    /**
     * Creates a Square Game Board with a Given Size
     * @param size Length and Width of the Square Game Board
     */
    public GameBoard(int size)
    {
            this.size = size;
            this.board = new Token[size][size];
            this.winningTokens = new Token[5];
            this.lastToken = null;
    }

    /**
     * Places a Token on the Board
     * @param col Column in which Token Resides
     * @param currentPlayer Player to whom the Token Belongs
     * @return true if Token Placed Successfully
     */
    public boolean placeToken(int col, Player currentPlayer)
    {
            for (int row = this.size - 1; row > -1; row--)
            {
                    if (this.board[row][col] == null)
                    {
                            Token token = new Token(row, col, currentPlayer);
                            this.board[row][col] = token;
                            this.lastToken = token;
                            return true;
                    }
            }
            return false;
    }

    /**
     * Checks the Given Location for a Token
     * @param row Row Index
     * @param col Column Index
     * @return Token if Present; null otherwise
     */
    public Token checkBoard(int row, int col)
    {
            return this.board[row][col];
    }

    /**
     * Checks the Game Board for a Winning Chain
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    public boolean checkWinner(Player player)
    {
            boolean isWon = checkHorizontal(player) || checkVertical(player)
                            || checkLRDiag(player) || checkLRCornerDiag(player) || checkRLDiag(player)
                            || checkRLCornerDiag(player);
            if (isWon)
                    this.setWinningTokens();
            return isWon;
    }

    /**
     * Horizontally Checks the Game Board for a Winning Chain
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    private boolean checkHorizontal(Player currentPlayer)
    {
            for (int row = this.size - 1; row > -1; row--)
            {
                    for (int col = 0; col < this.size; col++)
                    {
                            for (int connectCount = 0; connectCount < 5; connectCount++)
                            {
                                    int tmpCol = col + connectCount;
                                    if (tmpCol >= this.size)
                                            tmpCol -= this.size;
                                    if (this.board[row][tmpCol] != null && this.board[row][tmpCol].getPlayer().equals(currentPlayer))
                                    {
                                            this.winningTokens[connectCount] = this.board[row][tmpCol];

                                            if (connectCount == 4)
                                                    return true;
                                    }
                                    else
                                    {
                                            this.clearWinningTokens();
                                            break;
                                    }
                            }
                    }
            }
            return false;
    }

    /**
     * Checks the Game Board for a Winning Chain Along Top-Left-to-Bottom-Right Diagonal
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    private boolean checkVertical(Player currentPlayer)
    {
            for (int col = this.size - 1; col > -1; col--)
            {
                    for (int row = 0; row < this.size; row++)
                    {
                            for (int connectCount = 0; connectCount < 5; connectCount++)
                            {
                                    int tmpRow = row + connectCount;
                                    if (tmpRow >= this.size)
                                            tmpRow -= this.size;
                                    if (this.board[tmpRow][col] != null && this.board[tmpRow][col].getPlayer().equals(currentPlayer))
                                    {
                                            this.winningTokens[connectCount] = this.board[tmpRow][col];

                                            if (connectCount == 4)
                                                    return true;
                                    }
                                    else
                                    {
                                            this.clearWinningTokens();
                                            break;
                                    }
                            }
                    }
            }
            return false;
    }

    /**
     * Checks the Game Board for a Winning Chain Along Top-Left-to-Bottom-Right Diagonal
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    private boolean checkLRDiag(Player currentPlayer)
    {
            int row = this.lastToken.getRow();
            int col = this.lastToken.getColumn();
            for (int connectCount = 0; connectCount < 5; connectCount++)
            {
                    int tmpRow = row + connectCount;
                    int tmpCol = col + connectCount;

                    if (tmpRow >= this.size)
                            tmpRow -= this.size;
                    if (tmpCol >= this.size)
                            tmpCol -= this.size;

                    if (this.board[tmpRow][tmpCol] != null && this.board[tmpRow][tmpCol].getPlayer().equals(currentPlayer))
                    {
                            this.winningTokens[connectCount] = this.board[tmpRow][tmpCol];

                            if (connectCount == 4)
                                    return true;
                    }
                    else
                    {
                            this.clearWinningTokens();
                            break;
                    }
            }
            return false;
    }

    /**
     * Checks the Game Board for a Winning Chain Along
     * Top-Left-to-Bottom-Right Diagonal with Corner Wrap-Around
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    private boolean checkLRCornerDiag(Player currentPlayer)
    {
            int connectCount;
            for (int row = 0; row < this.size; row++)
            {
                    for (connectCount = 0; connectCount < 5; connectCount++)
                    {
                            int tmpRow = row + connectCount;
                            if (tmpRow >= this.size)
                                    tmpRow -= this.size;
                            int col = tmpRow;
                            if (this.board[tmpRow][col] != null && this.board[tmpRow][col].getPlayer().equals(currentPlayer))
                            {
                                    this.winningTokens[connectCount] = this.board[tmpRow][col];

                                    if (connectCount == 4)
                                            return true;
                            }
                            else
                            {
                                    this.clearWinningTokens();
                                    break;
                            }
                    }
            }
            return false;
    }

    /**
     * Checks the Game Board for a Winning Chain Along Bottom-Left-to-Top-Right Diagonal
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    private boolean checkRLDiag(Player currentPlayer)
    {
            int row = this.lastToken.getRow();
            int col = this.lastToken.getColumn();

            for (int connectCount = 0; connectCount < 5; connectCount++)
            {
                    int tmpRow = row + connectCount;
                    int tmpCol = col - connectCount;

                    if (tmpRow >= this.size)
                            tmpRow -= this.size;

                    if (tmpCol >= this.size)
                            tmpCol -= this.size;
                    else if (tmpCol < 0)
                            tmpCol += this.size;

                    if (this.board[tmpRow][tmpCol] != null && this.board[tmpRow][tmpCol].getPlayer().equals(currentPlayer))
                    {
                            this.winningTokens[connectCount] = this.board[tmpRow][tmpCol];

                            if (connectCount == 4)
                                    return true;
                    }
                    else
                    {
                            this.clearWinningTokens();
                            break;
                    }
            }
            return false;

    }

    /**
     * Checks the Game Board for a Winning Chain Along
     * Bottom-Left-to-Top-Right Diagonal with Corner Wrap-Around
     * @param player Player who Placed the Latest Token
     * @return true if Winning Chain is Found
     */
    private boolean checkRLCornerDiag(Player currentPlayer)
    {
            for (int row = this.size; row > -1; row--)
            {
                    for (int connectCount = 0; connectCount < 5; connectCount++)
                    {
                            int tmpRow = row + connectCount;
                            if (tmpRow >= this.size)
                                    tmpRow -= this.size;
                            int tmpCol = -tmpRow + (this.size - 1);

                            if (this.board[tmpRow][tmpCol] != null && this.board[tmpRow][tmpCol].getPlayer().equals(currentPlayer))
                            {
                                    this.winningTokens[connectCount] = this.board[tmpRow][tmpCol];

                                    if (connectCount == 4)
                                            return true;
                            }
                            else
                            {
                                    this.clearWinningTokens();
                                    break;
                            }
                    }
            }
            return false;
    }

    /**
     * Removes Winning Tokens from the Board
     */
    private void clearWinningTokens()
    {
            for (int i = 0; i < this.winningTokens.length; i++)
                    this.winningTokens[i] = null;
    }

    /**
     * Sets Winning Chain as Winning Tokens
     */
    private void setWinningTokens()
    {
            for (Token t : this.winningTokens)
                    t.setAsAWinningToken();
    }

    /**
     * Returns Size of the Square Game Board
     * @return Size of the Square Game Board
     */
    public int getSize()
    {
            return this.size;
    }

    /**
     * Returns Last Token Placed
     * @return Last Token Placed
     */
    public Token getLastToken()
    {
            return this.lastToken;
    }

}