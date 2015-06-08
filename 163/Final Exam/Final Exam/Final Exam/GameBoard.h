/*
	Final Exam
	GameBoard.h
	Represents a game board for TicTacToe

	Composed of a matrix of cells, each cell is intended to hold a Player's token.
*/

#include <vector>
#include "MoveState.h"

enum MoveState;

class GameBoard
{
private:
	int ROW_SIZE;
	int COLUMN_SIZE;
	std::vector<std::vector<char>> board;
	bool winState;
	std::string horizontalSeparator(int);
	bool checkForWin();
	char horizontalCheck();
	char verticalCheck();
	char rightDiagonalCheck();
	char leftDiagonalCheck();
	bool validateBoardIndex(int, int);

public:
	GameBoard();
	GameBoard(int);
	std::string toString();
	MoveState place(char, int, int, bool=false);
	bool getWinState();
};