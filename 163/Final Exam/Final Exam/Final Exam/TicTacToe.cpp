#include "TicTacToe.h"

using namespace std;

TicTacToe::TicTacToe()
{
	this->numberOfPlayers = 2;
	this->currentTurn = 0;
	this->winner = Player("NULL", '\0');
	this->players = vector<Player>(this->numberOfPlayers);
	this->players[0] = Player("Player One", 'X');
	this->players[1] = Player("Player Two", 'O');
	this->board = GameBoard();
}

TicTacToe::TicTacToe(int numberOfPlayers, int boardSize)
{
	this->numberOfPlayers = numberOfPlayers;
	this->currentTurn = 0;
	this->winner = Player("NULL", '\0');
	this->players = vector<Player>(numberOfPlayers);
	for (int i = 0; i < numberOfPlayers; i++) {
		this->players[i] = Player("P" + i, i);
	}
	this->board = GameBoard(boardSize);
}

void TicTacToe::changeTurn()
{
	if (this->currentTurn == this->numberOfPlayers - 1) {
		this->currentTurn = 0;
	} else {
		this->currentTurn++;
	}
}

MoveState TicTacToe::place(int row, int col)
{
	MoveState placedState = this->board.place(this->players[this->currentTurn].getPlayerToken(), row, col, true);
	if (placedState == MoveState::WINNING_MOVE) {
		this->winner = this->players[this->currentTurn];
	}
	return placedState;
}


ostream& operator<<(ostream& stream, TicTacToe& ticTacToe)
{
	return stream << ticTacToe.board.toString();
}