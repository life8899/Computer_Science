
#include <string>

class Player
{
private:
	std::string playerName;
	char token;

public:
	Player();
	Player(std::string, char);
	std::string getPlayerName();
	char getPlayerToken();
	bool operator==(const Player&);
	friend std::ostream& operator<<(std::ostream&, const Player&);
};