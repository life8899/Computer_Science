#include <sstream>
#include "GameBoard.h"

using namespace std;

GameBoard::GameBoard()
{
	this->winState = false;
	this->ROW_SIZE = 3;
	this->COLUMN_SIZE = 3;
	this->board = vector<vector<char>>(3);
	for (int row = 0; row < this->ROW_SIZE; row++) {
		this->board[row] = vector<char>(3);
		for (int col = 0; col < this->COLUMN_SIZE; col++) {
			this->board[row][col] = '\0';
		}
	}
}

GameBoard::GameBoard(int squareSize)
{
	this->winState = false;
	this->ROW_SIZE = squareSize;
	this->COLUMN_SIZE = squareSize;
	this->board = vector<vector<char>>(squareSize);
	for (int row = 0; row < this->ROW_SIZE; row++) {
		this->board[row] = vector<char>(squareSize);
		for (int col = 0; col < this->COLUMN_SIZE; col++) {
			this->board[row][col] = '\0';
		}
	}
}

bool GameBoard::validateBoardIndex(int row, int col)
{
	if (row >= 0 && row < this->ROW_SIZE) {
		if (col >= 0 && col < this->COLUMN_SIZE) {
			return true;
		}
	}
	return false;
}

MoveState GameBoard::place(char token, int row, int col, bool adjust)
{
	if (adjust) {
		row--;
		col--;
	}
	if (validateBoardIndex(row, col)) {
		if (token != '\0' && this->board[row][col] == '\0') {
			this->board[row][col] = token;
			if (checkForWin()) {
				return MoveState::WINNING_MOVE;
			} else {
				return MoveState::VALID_MOVE;
			}
		}
	}
	return MoveState::INVALID_MOVE;
}


char GameBoard::horizontalCheck()
{
	char lastToken = '\0';
	for (int row = 0; row < this->ROW_SIZE; row++) {
		lastToken = '\0';
		for (int col = 0; col < this->COLUMN_SIZE; col++) {
			if (col == 0) {
				lastToken = this->board[row][col];
			} else {
				if (lastToken != '\0' && lastToken == this->board[row][col]) {
					lastToken = this->board[row][col];
				} else {
					lastToken = '\0';
					break;
				}
			}
		}
		if (lastToken != '\0') {
			return lastToken;
		}
	}
	return '\0';
}

char GameBoard::verticalCheck()
{
	char lastToken = '\0';
	for (int col = 0; col < this->COLUMN_SIZE; col++) {
		lastToken = '\0';
		for (int row = 0; row < this->ROW_SIZE; row++) {
			if (row == 0) {
				lastToken = this->board[row][col];
			} else {
				if (lastToken != '\0' && lastToken == this->board[row][col]) {
					lastToken = this->board[row][col];
				} else {
					lastToken = '\0';
					break;
				}
			}
		}
		if (lastToken != '\0') {
			return lastToken;
		}
	}
	return '\0';
}

char GameBoard::rightDiagonalCheck()
{
	char lastToken = '\0';
	for (int row = 0; row < this->ROW_SIZE; row++) {
		int col = row;
		if (row == 0 && col == 0) {
			lastToken = this->board[row][col];
		} else {
			if (lastToken != '\0' && lastToken == this->board[row][col]) {
				lastToken = this->board[row][col];
			} else {
				lastToken = '\0';
				break;
			}
		}
	}
	if (lastToken != '\0') {
		return lastToken;
	}
	return '\0';
}

char GameBoard::leftDiagonalCheck()
{
	char lastToken = '\0';
	for (int row = 0; row < this->ROW_SIZE; row++) {
		int col = this->COLUMN_SIZE - row - 1;
		if (row == 0) {
			lastToken = this->board[row][col];
		} else {
			if (lastToken != '\0' && lastToken == this->board[row][col]) {
				lastToken = this->board[row][col];
			} else {
				lastToken = '\0';
				break;
			}
		}
		if (lastToken != '\0') {
			return lastToken;
		}
	}
	return '\0';
}


bool GameBoard::checkForWin()
{
	char hCheck = horizontalCheck();
	char vCheck = verticalCheck();
	char rCheck = rightDiagonalCheck();
	char lCheck = leftDiagonalCheck();
	if (hCheck != '\0' || vCheck != '\0' || rCheck != '\0' || lCheck != '\0') {
		return true;
	}
	return false;
}

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

bool GameBoard::getWinState()
{
	return this->winState;
}

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