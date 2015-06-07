/*
	Final Exam
	GameBoard.h
	Represents a game board for TicTacToe

	Composed of a matrix of cells, each cell is intended to hold a Player's token.
*/

#include <vector>

class GameBoard
{
private:
	int ROW_SIZE;
	int COLUMN_SIZE;
	std::vector<std::vector<char>> board;
	bool winState;
	std::string horizontalSeparator();
	char horizontalCheck();
	char verticalCheck();
	char standardDiagonalCheck();
	char reverseDiagonalCheck();
	bool validateBoardIndex(int, int);

public:
	GameBoard();
	GameBoard(int);
	std::string toString();
	char checkForWin();
	bool place(char, int, int, bool=false);
};