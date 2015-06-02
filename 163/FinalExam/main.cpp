/*
	Final Exam
	Main.cpp
	Main Game Loop

	@author Nick Alexander
	@version 6/1/2015
*/

#include <iostream>
#include <sstream>
#include "TicTacToe.h"

using namespace std;

/*
	Pauses execution until the user presses return.
*/
void pause()
{
	cout << "Press Enter to Continue..." << endl;
	getchar();
}

/*
	Displays the supplied prompt and accepts a string
	from the user via standard input.

	@param prompt
	Prompt to be shown to the user

	@return User input string
*/
string getStringInput(string prompt)
{
	cout << prompt;
	string input = "";
	getline(cin, input);
	return input;
}

/*
	Displays the supplied prompt and accepts an integer
	from the user via standard input. If an integer is not entered,
	an error message is displayed and loops until a valid integer
	is found. The user input integer is then returned.

	@param prompt
	Prompt to be shown to the user

	@return User input integer
*/
int getNumberInput(string prompt)
{
	int number = 0;
	bool valid = false;
	while (!valid) {
		stringstream stream(getStringInput(prompt));
		if (stream >> number) { // Type-safe extraction. Fails if stream does not contain integer
			valid = true;
		} else {
			cout << "Error: Invalid Input" << endl;
			cout << endl;
		}
	}
	return number;
}

/*
	Displays the supplied prompt and accepts an character
	from the user via standard input. If a character is not entered,
	an error message is displayed and loops until a valid character
	is found. The user input character is then returned.

	@param prompt
	Prompt to be shown to the user

	@return User input character
*/
char getCharInput(string prompt)
{
	char c = 0;
	bool valid = false;
	while (!valid) {
		stringstream stream(getStringInput(prompt));
		if (stream >> c) { // Type-safe extraction. Fails if stream does not contain character
			valid = true;
		} else {
			cout << "Error: Invalid Input" << endl;
			cout << endl;
		}
	}
	return c;
}

/*
	Prompts the user for 2 player names and corresponding
	player symbol to initialize the TicTacToe game.

	@param game
	Reference to a TicTacToe game
*/
void setupMenu(TicTacToe& game)
{
	char player1Symbol, player2Symbol;
	string player1Name, player2Name;

	player1Name = getStringInput("Player 1 Name: ");
	player1Symbol = getCharInput("Player 1 Symbol: ");
	game.setPlayer1(player1Symbol, player1Name);

	cout << endl;

	player2Name = getStringInput("Player 2 Name: ");
	player2Symbol = getCharInput("Player 2 Symbol: ");
	game.setPlayer2(player2Symbol, player2Name);

	cout << endl;
}

/*
	Prompts the current player for a row and column
	to place a token. If the token placement is invalid,
	such as already filled or out of bounds, an error message
	is displayed and prompts again. If the placement is valid,
	the new TicTacToe board is displayed. If the placement
	was a winning move, returns true.

	@param game
	Reference to a TicTacToe game

	@return True if the move was a winning move
*/
bool playMenu(TicTacToe& game)
{
	MoveState currentState = MoveState::INVALID_MOVE;
	while (currentState == MoveState::INVALID_MOVE) {
		cout << endl;
		cout << game.getCurrentTurnToken().getOwner() << "'s Turn!" << endl;

		int row, column;
		row = getNumberInput("Row: ");
		column = getNumberInput("Col: ");
		currentState = game.move(row, column);

		if (currentState == MoveState::INVALID_MOVE) {
			cout << "Invalid Move!" << endl;
		}

		cout << endl;
	}

	cout << game.boardToString() << endl;
	cout << endl;

	if (currentState == MoveState::WINNING_MOVE) {
		return true;
	}
	return false;
}

/*
	Main game loop. Creates a TicTacToe game and calls
	the setupMenu. Once setup is complete, displays the empty
	board. Loops until the game is won, displays the winner,
	and asks to play again. If play again is chosen, the board
	is reset and the loop restarts.

	@return 0 if no errors
*/
int main()
{
	TicTacToe game = TicTacToe();
	setupMenu(game);
	
	cout << game.boardToString() << endl;

	bool done = false;
	while (!done) {
		if (playMenu(game)) {
			cout << game.getWinnerToken().getOwner() << "(" << game.getWinnerToken().getSymbol() << ") Wins!" << endl;
			cout << endl;
			if (getCharInput("Play Again (y/n): ") != 'y') {
				done = true;
			} else {
				game.reset();
				cout << endl;
				cout << game.boardToString() << endl;
			}
		}
	}

	pause();

	return 0;
}