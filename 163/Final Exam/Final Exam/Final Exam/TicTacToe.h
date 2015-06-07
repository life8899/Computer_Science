#include <vector>
#include "GameBoard.h"
#include "Player.h"

class TicTacToe
{
private:
	GameBoard board;
	std::vector<Player> players;

public:
	TicTacToe();
};