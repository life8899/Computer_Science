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
    /* Number of Rows on the Board */
    int ROW_SIZE;

    /* Number of Columns on the Board */
    int COLUMN_SIZE;

    /* Vector representing the board */
    std::vector< std::vector<std::string> > board;

    /* Bool representing if the game has been won */
    bool winState;

    /* Creates a string board separator for nice output formatting */
    std::string horizontalSeparator(int);

    /* Checks for a winning sequence on the board */
    bool checkForWin();

    /* Checks the horizontals for a winning sequence */
    std::string horizontalCheck();

    /* Checks the verticals for a winning sequence */
    std::string verticalCheck();

    /* Checks the right diagonal for a winning sequence */
    std::string rightDiagonalCheck();

    /* Checks the left diagonal for a winning sequence */
    std::string leftDiagonalCheck();

    /* Returns true if the pair is a valid board location */
    bool validateBoardIndex(int, int);

public:
    /* Default Constructor */
    GameBoard();

    /* Sets the number of rows and columns to the given size */
    GameBoard(int);

    /* Returns a string representation of the game board */
    std::string toString();

    /* Places a token on the board */
    MoveState place(std::string, int, int, bool=false);

    /* Returns the winning state of the board */
    bool getWinState();
};