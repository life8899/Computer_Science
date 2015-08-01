/*
	Final Exam
	GameBoard.cpp
	Represents a game board for TicTacToe

	Composed of a matrix of cells, each cell is intended to hold a Player's token.
*/

#include <sstream>
#include <iostream>
#include "GameBoard.h"

using namespace std;

/*
	Default Constructor sets the row and column sizes to 3
*/
GameBoard::GameBoard()
{
    this->winState = false;
    this->ROW_SIZE = 3;
    this->COLUMN_SIZE = 3;
    this->board = vector<vector<std::string> >(3);
    for (int row = 0; row < this->ROW_SIZE; row++) {
        this->board[row] = vector<std::string>(3);
        for (int col = 0; col < this->COLUMN_SIZE; col++) {
            this->board[row][col] = " ";
        }
    }
}

/*
	Constructor sets the game board to have equal rows and columns specified by squareSize

	@param squareSize
	Number of rows and columns that the board should have
*/
GameBoard::GameBoard(int squareSize)
{
    this->winState = false;
    this->ROW_SIZE = squareSize;
    this->COLUMN_SIZE = squareSize;
    this->board = vector<vector<std::string> >(squareSize);
    for (int row = 0; row < this->ROW_SIZE; row++) {
        this->board[row] = vector<std::string>(squareSize);
        for (int col = 0; col < this->COLUMN_SIZE; col++) {
            this->board[row][col] = " ";
        }
    }
}

/*
	Determines if the row, column index is a valid board location

	@param row
	Row index

	@param col
	Column index

	@return
	True if the row, column pair is a valid board location
*/
bool GameBoard::validateBoardIndex(int row, int col)
{
    if (row >= 0 && row < this->ROW_SIZE) {
        if (col >= 0 && col < this->COLUMN_SIZE) {
            if (this->board[row][col] == " ")
            return true;
        }
    }
    return false;
}

/*
	Places a token on the board

	@param token
	Token to be placed on the game board in the location

	@param row
	Row index

	@param col
	Column index

	@param adjust
	True if the row and column need to be converted to 0-based from 1-based

	@return
	MoveState representing if the move was valid, invalid, or winning
*/
MoveState GameBoard::place(std::string token, int row, int col, bool adjust)
{
    if (adjust) {
        row--;
        col--;
    }
    if (validateBoardIndex(row, col)) {
        if (token != "" && this->board[row][col] == " ") {
            this->board[row][col] = token;
            if (checkForWin()) {
                this->winState = true;
                return WINNING_MOVE;
            } else {
                return VALID_MOVE;
            }
        }
    }
    return INVALID_MOVE;
}

/*
	Checks the horizontal locations for a winning sequence

	@return
	Player token of a winning sequence; null std::stringacter otherwise
*/
std::string GameBoard::horizontalCheck()
{
    std::string lastToken = " ";
    for (int row = 0; row < this->ROW_SIZE; row++) {
        lastToken = " ";
        for (int col = 0; col < this->COLUMN_SIZE; col++) {
            if (col == 0) {
                lastToken = this->board[row][col];
            } else {
                if (lastToken != " " && lastToken == this->board[row][col]) {
                    lastToken = this->board[row][col];
                } else {
                    lastToken = " ";
                    break;
                }
            }
        }
        if (lastToken != " ") {
            return lastToken;
        }
    }
    return " ";
}

/*
	Checks the vertical locations for a winning sequence

	@return
	Player token of a winning sequence; null std::stringacter otherwise
*/
std::string GameBoard::verticalCheck()
{
    std::string lastToken = " ";
    for (int col = 0; col < this->COLUMN_SIZE; col++) {
        lastToken = " ";
        for (int row = 0; row < this->ROW_SIZE; row++) {
            if (row == 0) {
                lastToken = this->board[row][col];
            } else {
                if (lastToken != " " && lastToken == this->board[row][col]) {
                    lastToken = this->board[row][col];
                } else {
                    lastToken = " ";
                    break;
                }
            }
        }
        if (lastToken != " ") {
            return lastToken;
        }
    }
    return " ";
}

/*
	Checks the right diagonal locations for a winning sequence

	@return
	Player token of a winning sequence; null std::stringacter otherwise
*/
std::string GameBoard::rightDiagonalCheck()
{
    std::string lastToken = " ";
    for (int row = 0; row < this->ROW_SIZE; row++) {
        int col = row;
        if (row == 0 && col == 0) {
            lastToken = this->board[row][col];
        } else {
            if (lastToken != " " && lastToken == this->board[row][col]) {
                lastToken = this->board[row][col];
            } else {
                lastToken = " ";
                break;
            }
        }
    }
    if (lastToken != " ") {
        return lastToken;
    }
    return " ";
}

/*
	Checks the left diagonal locations for a winning sequence

	@return
	Player token of a winning sequence; null std::stringacter otherwise
*/
std::string GameBoard::leftDiagonalCheck()
{
    std::string lastToken = " ";
    for (int row = 0; row < this->ROW_SIZE; row++) {
        int col = this->COLUMN_SIZE - row - 1;
        if (row == 0) {
            lastToken = this->board[row][col];
        } else {
            if (lastToken != " " && lastToken == this->board[row][col]) {
                lastToken = this->board[row][col];
            } else {
                lastToken = " ";
                break;
            }
        }
    }
    if (lastToken != " ") {
        return lastToken;
    }
    return " ";
}

/*
	Checks the entire game board for a winning sequence

	@return
	True if a winning sequence was found
*/
bool GameBoard::checkForWin()
{
    std::string hCheck = horizontalCheck();
    std::string vCheck = verticalCheck();
    std::string rCheck = rightDiagonalCheck();
    std::string lCheck = leftDiagonalCheck();
    if (hCheck != " " || vCheck != " " || rCheck != " " || lCheck != " ") {
        return true;
    }
    return false;
}


/*
	Calculates and creates a string board separator for nice output formatting

	@return
	String of a horizontal separator for the game board
*/
string GameBoard::horizontalSeparator(int pad)
{
    int numDash = this->COLUMN_SIZE;
    int numPlus = numDash - 1;

    string asString = "";

    for (int i = 0; i < pad; i++) {
        asString += " ";
    }

    for (int i = 0; i < numDash; i++) {
        asString += '-';
        if (i < numPlus) {
            asString += '+';
        }
    }
    return asString += '\n';
}

/*
	Returns the winning state of the game board

	@return
	Winning state of the gameboard
*/
bool GameBoard::getWinState()
{
    return this->winState;
}

/*
	Returns a string representation of the game board

	@return
	String representation of the game board
*/
string GameBoard::toString()
{
    ostringstream rowIntToStringConverter("");
    ostringstream colIntToStringConverter("");
    string asString = "";
    for (int row = -1; row < this->ROW_SIZE; row++) {
        rowIntToStringConverter << (row + 1) << " ";

        if (row + 1 < 10 && this->ROW_SIZE >= 10) {
            rowIntToStringConverter.str(" " + rowIntToStringConverter.str());
        }

        if (row >= 0) {
            asString += rowIntToStringConverter.str();
        }

        for (int col = 0; col < this->COLUMN_SIZE; col++) {
            if (row >= 0) {
                asString += this->board[row][col];

                if (col != this->COLUMN_SIZE - 1) {
                    asString += "|";
                }
            } else {
                if (col == 0) {
                    for (int i = 0; i < rowIntToStringConverter.str().size(); i++) {
                        asString += " ";
                    }
                }
                colIntToStringConverter << (col + 1) << " ";
                asString += colIntToStringConverter.str();
                colIntToStringConverter.str("");
                colIntToStringConverter.clear();
            }
        }

        if (row < 0) {
            asString += "\n";
            asString += "\n";
        }

        if (row >= 0 && row != this->ROW_SIZE-1) {
            asString += "\n";
            asString += horizontalSeparator(rowIntToStringConverter.str().size());
        }

        rowIntToStringConverter.str("");
        rowIntToStringConverter.clear();
    }

    return asString;
}