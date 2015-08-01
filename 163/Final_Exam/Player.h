/*
	Final Exam
	Player.h

	Class representing a TicTacToe player

	@author Nick Alexander
	@version 6/30/2015
*/
#include <string>

/*
	Represents a TicTacToe Player
*/
class Player
{
private:
    /* Name of the player */
    std::string playerName;

    /* Token displayed on the board */
    std::string token;

public:
    /* Default Constructor */
    Player();

    /* Constructor that sets the name and the token for the player */
    Player(std::string, std::string);

    /* Returns the player's name */
    std::string getPlayerName();

    /* Returns the player's token */
    std::string getPlayerToken();

    /* Overlod the equals operator */
    bool operator==(const Player&);

    /* Overload the less-than relational operator */
    friend std::ostream& operator<<(std::ostream&, const Player&);
};