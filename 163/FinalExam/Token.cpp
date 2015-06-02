/*
	Final Exam
	Token.cpp
	Represents a Player Token

	@author Nick Alexander
	@version 6/1/2015
*/

#include "Token.h"

/* Default Constructor. Sets Symbol to ' ' and owner to "NULL" */
Token::Token()
{
	this->symbol = ' ';
	this->owner = "NULL";
}

/* Initializes symbol and owner to meaningful values */
Token::Token(char symbol, std::string owner)
{
	this->symbol = symbol;
	this->owner = owner;
}

/* Returns the token symbol */
char Token::getSymbol()
{
	return this->symbol;
}

/* Return the token owner */
std::string Token::getOwner()
{
	return this->owner;
}

/* Tests if two Token objects are equal */
bool Token::operator==(const Token& other) const
{
	if (this->symbol == other.symbol) {
		if (this->owner == other.owner) {
			return true;
		}
	}
	return false;
}