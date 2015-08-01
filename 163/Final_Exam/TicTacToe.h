/*
	Final Exam
	TicTacToe.h

	Represents a TicTacToe game

	@author Nick Alexander
	@version 6/30/2015
*/

#include <vector>
#include "GameBoard.h"
#include "Player.h"

/*
	Class representing a TicTacToe game
*/
class TicTacToe
{
private:
    /* Number of players playing the game */
    int numberOfPlayers;

    /* Index of the player who is taking their turn currently */
    int currentTurn;

    /* Game Board */
    GameBoard board;

    /* Winner */
    Player winner;

    /* Vector containing the players */
    std::vector<Player> players;

public:
    /* Default Constructor */
    TicTacToe();

    /* Constructor that sets the the board size and number of players */
    TicTacToe(int, int);

    /* Places a token on the board */
    MoveState place(int, int);

    /* Returns the name of the current player */
    std::string getCurrentPlayerName();

    /* Returns the token of the current player */
    std::string getCurrentPlayerToken();

    /* Returns the winner's name */
    std::string getWinnerName();

    /* Returns the winner's token */
    std::string getWinnerToken();

    /* Changes the turn to the next player */
    void changeTurn();

    /* Returns the winning state of the game board */
    bool getWinState();

    /* Overload stream operator display the board */
    friend std::ostream& operator<<(std::ostream&, TicTacToe&);
};