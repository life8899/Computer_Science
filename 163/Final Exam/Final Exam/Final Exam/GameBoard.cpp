#include <iostream>

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

bool GameBoard::place(char token, int row, int col, bool adjust)
{
	if (adjust) {
		row--;
		col--;
	}
	if (validateBoardIndex(row, col)) {
		if (token != '\0' && this->board[row][col] == '\0') {
			this->board[row][col] = token;
			return true;
		}
	}
	return false;
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

char GameBoard::standardDiagonalCheck()
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
		if (lastToken != '\0') {
			return lastToken;
		}
	}
	return '\0';
}

char GameBoard::reverseDiagonalCheck()
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


char GameBoard::checkForWin()
{
	char hCheck = horizontalCheck();
	cout << "hCheck: " << hCheck << endl;
	char vCheck = verticalCheck();
	cout << "vCheck: " << vCheck << endl;
	char sCheck = standardDiagonalCheck();
	cout << "sCheck: " << sCheck << endl;
	char rCheck = reverseDiagonalCheck();
	cout << "rCheck: " << rCheck << endl;
	if (hCheck != '\0') {
		return hCheck;
	} else if (vCheck != '\0') {
		return vCheck;
	} else if (sCheck != '\0') {
		return sCheck;
	} else if (rCheck != '\0') {
		return rCheck;
	}  else {
		return '\0';
	}
}

string GameBoard::horizontalSeparator()
{
	int numDash = this->COLUMN_SIZE;
	int numPlus = numDash - 1;

	string s = "";

	for (int i = 0; i < numDash; i++) {
		s += '-';
		if (i < numPlus) {
			s += '+';
		}
	}

	return s += '\n';
}

string GameBoard::toString()
{
	string asString = "";
	for (int row = 0; row < this->ROW_SIZE; row++) {
		for (int col = 0; col < this->COLUMN_SIZE; col++) {
			asString += this->board[row][col];
			if (col != this->COLUMN_SIZE-1) {
				asString += "|";
			}
		}
		if (row != this->ROW_SIZE-1) {
			asString += "\n";
			asString += horizontalSeparator();
		}
	}
	return asString;
}