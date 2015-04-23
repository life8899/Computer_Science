package Nim;

import javax.swing.JButton;

/**
 * Creates the Artificial Intelligence to Play
 * In a Player Versus Computer Game
 *
 * @author Nick Alexander
 * @version Nov 30, 2013
 */
public class AI {
    private GameInterface gameInterface;
    private JButton[][] aiButtons;
    
    /**
     * Returns a New AI Object
     */
    public AI()
    {
        this.gameInterface = OpeningInterface.gameInterface;
        this.aiButtons = GameInterface.buttons;
    }
    
    /**
     * Scans the Board for the Best Available Move 
     */
    public void findBestMove()
    {
        int numberInRow = 0;
        int[] individualNims = new int[10];
        int currentIndividualSumCounter = 0;
        for (int i = 0; i < GameInterface.stacks; i++)
        {
            numberInRow = 0;
            for (int j = 0; j < 10; j++)
            {
                if (aiButtons[i][j] != null)
                {
                    if (aiButtons[i][j].isVisible())
                    {
                        numberInRow++;
                    }
                }
            }
            individualNims[currentIndividualSumCounter] = numberInRow ^ GameInterface.nimSum;
            if (individualNims[currentIndividualSumCounter] < numberInRow
                    || individualNims[currentIndividualSumCounter] == numberInRow)
            {
                moveAI(i, numberInRow - individualNims[i]);
                return;
            }
            currentIndividualSumCounter++;
        }
    }
    
    /**
     * AI Programmatically Selects each Token and Presses Confirm
     * 
     * @param row Row in which the Tokens to Take Reside
     * @param tokensToTake Number of Tokens for the AI to Take
     */
    public void moveAI(int row, int tokensToTake)
    {
        int i = 0;
        while (tokensToTake != 0)
        {
            if (aiButtons[row][i].isVisible())
            {
                aiButtons[row][i].doClick();
                tokensToTake--;
            }
            i++;
        }
        GameInterface.game.setPlayerTurn(2);
        GameInterface.pressConfirm();
    }
}