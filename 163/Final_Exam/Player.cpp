/*
	Final Exam
	Player.cpp

	Class representing a TicTacToe player

	@author Nick Alexander
	@version 6/30/2015
*/

#include "Player.h"

/*
	Default Constructor sets name to empty string and token to space std::stringacter
*/
Player::Player()
{
    this->playerName = "NULL";
    this->token = " ";
}


/*
	Constructor to set the name and token of a player

	@param playerName
	Name for the player

	@param token
	Token to display on the game board for the player
*/
Player::Player(std::string playerName, std::string token)
{
    this->playerName = playerName;
    this->token = token;
}

/*
	Returns the name of the player

	@return
	Name of the player
*/
std::string Player::getPlayerName()
{
    return this->playerName;
}


/*
	Returns the token for the player

	@return
	Token for the player	
*/
std::string Player::getPlayerToken()
{
    return this->token;
}


/*
	Overloads the equals operator to determine if two players are equal.

	Two players are equal if their name and their token match.

	@param player
	Reference to another player object

	@return
	True if the players are equal	
*/
bool Player::operator==(const Player& player)
{
    if (this->playerName == player.playerName) {
        if (this->token == player.token) {
            return true;
        }
    }
    return false;
}


/*
	Overload of the stream operator to display a representation of the Player

	@param ostream
	Reference to an out stream

	@param player
	Reference to a player to display

	@return
	New out stream	
*/
std::ostream& operator<<(std::ostream& stream, const Player& player)
{
    stream << player.playerName;
    stream << (std::string)player.token;
    return stream;
    //return stream << playerName << " (" << playerToken << ")";
    //return stream << player.playerName << " (" << player.token << ")";
}