/*
	Final Exam
	TicTacToe.cpp
	Represents a TicTacToe Game

	@author Nick Alexander
	@version 6/1/2015
*/  

#include "TicTacToe.h"

/* Sets turn to player 1, sets both players and winner to default Tokens */
TicTacToe::TicTacToe()
{
	turn = 1;
	this->player1 = Token();
	this->player2 = Token();
	this->winner = Token();
}

/* Converts row and column to a board index */
int TicTacToe::convertToIndex(int row, int column, bool adjust=false)
{
	if (adjust) {
		row--;
		column--;
	}
	return 3 * row + column;
}

/* Returns a string representation of the playing board */
std::string TicTacToe::boardToString()
{
	std::string boardString = "";
	for (int row = 0; row < 3; row++) {
		for (int col = 0; col < 3; col++) {
			int index = convertToIndex(row, col);
			boardString += this->board[index].getSymbol();
			if (col != 2) {
				boardString += "|";
			}
		}
		if (row != 2) {
			boardString += "\n";
			for (int i = 0; i < 5; i++) {
				if (i == 1 || i == 3) {
					boardString += "+";
				} else {
					boardString += "-";
				}
			}
			boardString += "\n";
		}
	}
	return boardString;
}

/* Initializes player 1 */
void TicTacToe::setPlayer1(char tokenSymbol, std::string playerName)
{
	Token token(tokenSymbol, playerName);
	this->player1 = token;
}

/* Initializes player 2 */
void TicTacToe::setPlayer2(char tokenSymbol, std::string playerName)
{
	Token token(tokenSymbol, playerName);
	this->player2 = token;
}

/* Alternates turn */
void TicTacToe::changeTurn()
{
	if (this->turn == 1) {
		this->turn = 2;
	} else {
		this->turn = 1;
	}
}

/* Determines if a row, column pair is a valid move */
MoveState TicTacToe::validateMove(int row, int column)
{
	int moveIndex = convertToIndex(row, column, true);
	if (moveIndex >= 0 && moveIndex <= BOARD_SIZE - 1) {
		if (this->board[moveIndex].getOwner() == "NULL") {
			return MoveState::VALID_MOVE;
		}
	}
	return MoveState::INVALID_MOVE;
}

/* Place a token at a row, column pair */
MoveState TicTacToe::move(int row, int column)
{
	if (validateMove(row, column) == MoveState::INVALID_MOVE) {
		return MoveState::INVALID_MOVE;
	}
	Token token = (this->turn == 1) ? this->player1 : this->player2;
	int index = convertToIndex(row, column, true);
	this->board[index] = token;
	this->changeTurn();
	if (checkForWin()) {
		return MoveState::WINNING_MOVE;
	}
	return MoveState::VALID_MOVE;
}

/* Checks all rows for a winning series */
bool TicTacToe::horizontalCheck(Token& testWinner)
{
	for (int row = 0; row < 3; row++) {
		int col = 0;
		int base = convertToIndex(row, col);
		if (this->board[base].getOwner() != "NULL") {
			if (this->board[base] == this->board[convertToIndex(row, col + 1)]) {
				if (this->board[base] == this->board[convertToIndex(row, col + 2)]) {
					testWinner = this->board[base];
					return true;
				}
			}
		}
	}
	return false;
}

/* Checks all columns for a winning series */
bool TicTacToe::verticalCheck(Token& testWinner)
{
	for (int col = 0; col < 3; col++) {
		int row = 0;
		int base = convertToIndex(row, col);
		if (this->board[base].getOwner() != "NULL") {
			if (this->board[base] == this->board[convertToIndex(row + 1, col)]) {
				if (this->board[base] == this->board[convertToIndex(row + 2, col)]) {
					testWinner = this->board[base];
					return true;
				}
			}
		}
	}

	return false;
}

/* Checks both diagonals for a winning series */
bool TicTacToe::diagonalCheck(Token& testWinner)
{
	int base = convertToIndex(0, 0);
	if (this->board[base].getOwner() != "NULL") {
		if (this->board[base] == this->board[convertToIndex(1, 1)]) {
			if (this->board[base] == this->board[convertToIndex(2, 2)]) {
				testWinner = this->board[base];
				return true;
			}
		}
	}

	base = convertToIndex(0, 2);
	if (this->board[base].getOwner() != "NULL") {
		if (this->board[base] == this->board[convertToIndex(1, 1)]) {
			if (this->board[base] == this->board[convertToIndex(2, 0)]) {
				testWinner = this->board[base];
				return true;
			}
		}
	}

	return false;
}

/* Wrapper to check for a winning series*/
bool TicTacToe::checkForWin()
{
	Token winningToken = Token();
	if (horizontalCheck(winningToken)) {
		this->winner = winningToken;
		return true;
	}
	else if (verticalCheck(winningToken)) {
		this->winner = winningToken;
		return true;
	} else if (diagonalCheck(winningToken)) {
		this->winner = winningToken;
		return true;
	}
	return false;
}

/* Returns the token of the winner */
Token TicTacToe::getWinnerToken()
{
	return this->winner;
}

/* Returns the token of the player currently taking a turn */
Token TicTacToe::getCurrentTurnToken()
{
	if (this->turn == 1) {
		return this->player1;
	}
	else {
		return this->player2;
	}
}

/* Resets the game to a default, playable stae */
void TicTacToe::reset()
{
	this->turn = 1;
	this->winner = Token();
	for (int i = 0; i < 9; i++) {
		this->board[i] = Token();
	}
}