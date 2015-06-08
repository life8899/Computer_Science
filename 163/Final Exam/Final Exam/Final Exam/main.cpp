/*
	Final Exam
	main.cpp
	Entry point for a TicTacToe game.

	@author Nick Alexander
	@version 6/7/2015
*/

#include <iostream>
#include <sstream>
#include "TicTacToe.h"

using namespace std;

string getStringInput(string prompt)
{
	cout << prompt;
	string input;
	getline(cin, input);
	return input;
}

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

char getCharInput(string prompt)
{
	bool valid;
	char input;
	do {
		if (istringstream(getStringInput(prompt)) >> input) {
			valid = true;
		}
		else {
			cout << "Invalid Input. Try Again." << endl << endl;
			valid = false;
		}
	} while (!valid);
	return input;
}

void pauseExecution()
{
	cout << "Press Enter to Continue..." << endl;
	getchar();
}

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

int main()
{
	TicTacToe game;
	setup(game);

	cout << endl;

	cout << game << endl;

	pauseExecution();
	return 0;
}