#include <vector>
#include "GameBoard.h"
#include "Player.h"

class TicTacToe
{
private:
	int numberOfPlayers;
	int currentTurn;
	GameBoard board;
	Player winner;
	std::vector<Player> players;
	void changeTurn();

public:
	TicTacToe();
	TicTacToe(int, int);
	MoveState place(int, int);
	friend std::ostream& operator<<(std::ostream&, TicTacToe&);
};