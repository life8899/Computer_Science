/*
	Final Exam
	TicTacToe.cpp

	Represents a TicTacToe game

	@author Nick Alexander
	@version 6/30/2015
*/

#include <sstream>
#include "TicTacToe.h"

using namespace std;

/*
	Default Constructor sets the number of players to 2, current turn to 0, 
	player names to "Player 1", "Player 2", and players to X and O respectively.
*/
TicTacToe::TicTacToe()
{
    this->numberOfPlayers = 2;
    this->currentTurn = 0;
    this->winner = Player("NULL", " ");
    this->players = vector<Player>(this->numberOfPlayers);
    this->players[0] = Player("Player One", "X");
    this->players[1] = Player("Player Two", "O");
    this->board = GameBoard();
}

/*
	Constructor sets the number of players and the board size
*/
TicTacToe::TicTacToe(int numberOfPlayers, int boardSize)
{
    this->numberOfPlayers = numberOfPlayers;
    this->currentTurn = 0;
    this->winner = Player("NULL", "");
    this->players = vector<Player>(numberOfPlayers);
    std::ostringstream oss;
    for (int i = 0; i < numberOfPlayers; i++) {
        oss << "Player " << (i+1);
        std::string name = oss.str();
        oss.str("");
        oss << i + 1;
        std::string token = oss.str();
        this->players[i] = Player(name, token);
    }
    this->board = GameBoard(boardSize);
}


/*
	Changes the current turn to the next player
*/
void TicTacToe::changeTurn()
{
    if (this->currentTurn == this->numberOfPlayers - 1) {
        this->currentTurn = 0;
    } else {
        this->currentTurn++;
    }
}


/*
	Places a token on the board at the specified row and column

	@param row
	Row that is 1-based, not 0-based	

	@param col
	Column that is 1-based, not 0-based

	@return
	MoveState representing if the move was valid, invalid, or winning
*/
MoveState TicTacToe::place(int row, int col)
{
    MoveState placedState = this->board.place(this->players[this->currentTurn].getPlayerToken(), row, col, true);
    if (placedState == WINNING_MOVE) {
        this->winner = this->players[this->currentTurn];
    } else if (placedState != INVALID_MOVE) {
        this->changeTurn();
    }
    return placedState;
}

/*
	Returns the winning state of the game board

	@return
	True if the game has been won
*/
bool TicTacToe::getWinState()
{
    return this->board.getWinState();
}

std::string TicTacToe::getCurrentPlayerName()
{
    return this->players[this->currentTurn].getPlayerName();
}

std::string TicTacToe::getCurrentPlayerToken()
{
    return this->players[this->currentTurn].getPlayerToken();
}

/*
    Return name of the winner
 */
std::string TicTacToe::getWinnerName()
{
    return this->winner.getPlayerName();
}

/*
    Returns token of the winner
 */
std::string TicTacToe::getWinnerToken()
{
    return this->winner.getPlayerToken();
}

/*
	Overload of the stream operator to display the board

	@param stream
	Output stream

	@param ticTactoe
	Reference to a TicTacToe object to output

	@return
	New output stream
*/
ostream& operator<<(ostream& stream, TicTacToe& ticTacToe)
{
    return stream << ticTacToe.board.toString();
}