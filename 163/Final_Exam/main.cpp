/*
	Final Exam
	main.cpp
	
	Entry point for a TicTacToe game.

	@author Nick Alexander
	@version 6/7/2015
*/

#include <iostream>
#include <sstream>
#include <stdio.h>
#include "TicTacToe.h"

using namespace std;

/*
	Accepts string input from the user

	@param prompt
	Prompt to display to the user

	@return
	String entered by the user
*/
string getStringInput(string prompt)
{
	cout << prompt;
	string input;
	getline(cin, input);
	return input;
}

/*
	Prompts the user for numeric input

	@param prompt
	Prompt to display to the user

	@return
	Numeric input from the user
*/
int getNumericInput(string prompt)
{
	bool valid;
	int input;
	do {
		if (istringstream(getStringInput(prompt)) >> input) {
			valid = true;
		} else {
			cout << "Invalid Input. Try Again." << endl << endl;
			valid = false;
		}
	} while (!valid);
	return input;
}


/*
	Prompts the user for a std::stringacter of input

	@param prompt
	Prompt to display to the user

	@return
	std::stringacter input from the user
*/
std::string getCharacterInput(string prompt)
{
	bool valid;
	std::string input;
	std::string s = getStringInput(prompt);
	char c = s.at(0);
	do {
		if (c) {
			valid = true;
		}
		else {
			cout << "Invalid Input. Try Again." << endl << endl;
			valid = false;
		}
	} while (!valid);
	return input;
}

/*
	Pauses execution until the user enters a std::stringacter
*/
void pauseExecution()
{
	cout << "Press Enter to Continue..." << endl;
	getchar();
}

/*
	Sets up a game of TicTacToe

	@param game
	Reference to a TicTacToe game
*/
void setup(TicTacToe& game)
{
	int playerCount = 0, boardSize = 0;
	bool validPlayerCount = false;
	bool validBoardSize = false;

	while (!validPlayerCount) {
		playerCount = getNumericInput("Number of Players: ");
		if (playerCount >= 2) {
			validPlayerCount = true;
		} else {
			cout << "Must have 2 or more players!" << endl << endl;
		}
	}

	while (!validBoardSize) {
		boardSize = getNumericInput("Board Size(N x N): ");
		if (boardSize >= 3) {
			validBoardSize = true;
		} else {
			cout << "Board Size must be 3 or more!" << endl << endl;
		}
	}
	game = TicTacToe(playerCount, boardSize);
}

/*
	Main Insertion Point

	@return
	Exit Code
*/
int main()
{
	TicTacToe game;
	setup(game);

	cout << endl;

	while (!game.getWinState()) {
		MoveState state = INVALID_MOVE;
		bool firstPass = true;
		while (state == INVALID_MOVE) {
			if (!firstPass) {
				cout << "Invalid Move. Try Again." << endl;
			}
			firstPass = false;
			cout << game.getCurrentPlayerName() << "\'s Turn (" << game.getCurrentPlayerToken() << "): " << endl;
			int row = getNumericInput("Row: ");
			int col = getNumericInput("Col: ");
			state = game.place(row, col);
		}
		cout << endl << game << endl << endl;
	}
	cout << endl << game.getWinnerName() << " Wins!" << endl;

	cout << endl;

	pauseExecution();
	return 0;
}