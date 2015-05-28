 

import java.util.Random;

/**
 * AI Class
 * Handles All Computer Moves for TicTacToe
 * (e.g. Block Row, Block Diagonals, Random Move)
 * 
 * @author Nicholas Alexander
 * @version 10/21/13
 */
public class AI {
	
	private static boolean moved = false;
	
	/**
	 * AI Clicks Button to Block Column Win
	 * @param col Column
	 */
	public static void blockColumnClick(int col)
	{
		for (int row = 0; row < 5; row++)
		{
			if (Game.getBoardValue(row, col) == 0)
			{
				GUI.getButtonIndex(row, col).doClick();
				moved = true;
				return;
			}
		}
	}
	
	/**
	 * AI Clicks Button to Block Left Diagonal Win
	 * (Bottom Left to Top Right Diagonal)
	 */
	public static void blockLeftDiagClick()
	{
		for (int row = 4; row >= 0; row--)
		{
			int col = -row + 4;
			if (Game.getBoardValue(row, col) == 0)
			{
				GUI.getButtonIndex(row, col).doClick();
				moved = true;
				return;
			}
		}
	}
	
	/**
	 * AI Clicks Button to Block Right Diagonal Win
	 * (Top Left to Bottom Right)
	 */
	public static void blockRightDiagClick()
	{
		int row = 0;
		int col = 0;
		for (row = 0; row < 5; row++)
		{
			col = row;
			if (Game.getBoardValue(row, col) == 0)
			{
				GUI.getButtonIndex(row, col).doClick();
				moved = true;
				return;
			}
		}
	}
	
	/**
	 * AI Clicks Button to Block Row Win
	 * @param row Row
	 */
	public static void blockRowClick(int row)
	{
		for (int col = 0; col < 5; col++)
		{
			if (Game.getBoardValue(row, col) == 0)
			{
				GUI.getButtonIndex(row, col).doClick();
				moved = true;
				return;
			}
		}
	}
	
	/**
	 * AI Tests for 4 Human Pieces in Column Alignment
	 */
	public static void humanHasWinningCol()
	{
		int humanSpaces = 0;
		boolean blocked = false;
		int col = 0;
		for (col = 0; col < 5; col ++)
		{
			blocked = false;
			humanSpaces = 0;
			for (int row = 0; row < 5; row++)
			{
				if (Game.getBoardValue(row, col) == 1)
					humanSpaces++;
				if (Game.getBoardValue(row, col) == 2)
					blocked = true;
			}
			if (humanSpaces == 4 && !blocked)
				blockColumnClick(col);
		}
	}

	/**
	 * AI Tests for 4 Human Pieces in Left Diagonal Alignment
	 * (Bottom Left to Top Right)
	 */
	public static void humanHasWinningLeftDiag()
	{
		int humanSpaces = 0;
		int row = 0;
		boolean blocked = false;
		for (row = 0; row < 5; row++)
		{
			int col = -row + 4;
			if (Game.getBoardValue(row, col) == 1)
				humanSpaces++;
			if (Game.getBoardValue(row, col) == 2)
				blocked = true;
		}
		if (humanSpaces == 4 && !blocked)
			blockLeftDiagClick();
	}
	
	/**
	 * AI Tests for 4 Human Pieces in Right Diagonal Alignment
	 * (Top Left to Bottom Right)
	 */
	public static void humanHasWinningRightDiag()
	{
		int humanSpaces = 0;
		int row = 0;
		boolean blocked = false;
		for (row = 0; row < 5; row++)
		{
			int col = row;
			if (Game.getBoardValue(row, col) == 1)
				humanSpaces++;
			if (Game.getBoardValue(row, col) == 2)
				blocked = true;
		}
		if (humanSpaces == 4 && !blocked)
			blockRightDiagClick();
	}
	
	/**
	 * AI Tests for 4 Human Pieces in Row Alignment
	 */
	public static void humanHasWinningRow()
	{
		int humanSpaces = 0;
		int row = 0;
		boolean blocked = false;
		for (row = 0; row < 5; row++)
		{
			blocked = false;
			humanSpaces = 0;
			for (int col = 0; col < 5; col++)
			{
				if (Game.getBoardValue(row, col) == 1)
					humanSpaces++;
				if (Game.getBoardValue(row, col) == 2)
					blocked = true;
			}
			if (humanSpaces == 4 && !blocked)
				blockRowClick(row);
		}
	}
	
	/**
	 * Calls Each AI Test Method and Moves Accordingly
	 * (e.g. Row Block, Column Block, Random Move)
	 */
	public static void move()
	{
		moved = false;
		humanHasWinningRow();
		humanHasWinningCol();
		humanHasWinningLeftDiag();
		humanHasWinningRightDiag();
		if (!moved)
			randomMove();
	}
	
	/**
	 * AI Uses the Random Class to Select a Random Move if No Blocks are Required
	 */
	public static void randomMove()
	{
		Random random = new Random();
		boolean valid  = false;
		do
		{
			int row = random.nextInt(5);
			int col = random.nextInt(5);
			if (Game.getBoardValue(row, col) == 0)
			{
				GUI.getButtonIndex(row, col).doClick();
				valid = true;
			}
		} while (!valid);	
	}
}