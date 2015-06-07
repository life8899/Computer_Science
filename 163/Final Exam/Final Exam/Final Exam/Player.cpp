#include "Player.h"

Player::Player()
{
	this->playerName = "";
	this->token = ' ';
}

Player::Player(std::string playerName, char token)
{
	this->playerName = playerName;
	this->token = token;
}

std::string Player::getPlayerName()
{
	return this->playerName;
}

char Player::getPlayerToken()
{
	return this->token;
}

bool Player::operator==(const Player& player)
{
	if (this->playerName == player.playerName) {
		if (this->token == player.token) {
			return true;
		}
	}
	return false;
}

std::ostream& operator<<(std::ostream& stream, const Player& player)
{
	return stream << player.playerName << " (" << player.token << ")";
}