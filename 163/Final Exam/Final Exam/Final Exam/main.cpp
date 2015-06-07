/*
	Final Exam
	main.cpp
	Entry point for a TicTacToe game.

	@author Nick Alexander
	@version 6/7/2015
*/

#include <iostream>
#include "Player.h"
#include "GameBoard.h"

using namespace std;

void pauseExecution()
{
	cout << "Press Enter to Continue..." << endl;
	getchar();
}

int main()
{
	GameBoard board = GameBoard();
	cout << board.toString() << endl << endl;

	board.place('X', 0, 2);
	board.place('X', 1, 1);
	board.place('X', 2, 0);

	cout << board.toString() << endl << endl;

	cout << "Winner = " << board.checkForWin() << endl << endl;

	pauseExecution();
	return 0;
}