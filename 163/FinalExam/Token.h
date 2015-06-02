/*
	Final Exam
	Token.h
	Represents a Player Token

	@author Nick Alexander
	@version 6/1/2015
*/

#include <string>

class Token {

private:
	/* Symbol to be displayed on the TicTacToe board */
	char symbol;

	/* Name of the Owning Player */
	std::string owner;

public:
	/*
		Default Constructor. Sets the symbol to a space character (' ')
		and the owner to "NULL".
	*/
	Token();

	/*
		Constructs a Token with a symbol and owner.
	*/
	Token(char, std::string);

	/*
		Returns the token symbol.
	*/
	char getSymbol();

	/*
		Returns the token owner.
	*/
	std::string getOwner();

	/*
		Compares two Tokens to determine if they are equal. Equality
		is based on the symbol and owner. If both the symbol and owner match,
		tokens are equal.

		@return True if the symbol and owner are the same.
	*/
	bool operator== (const Token&) const;
};