/*
	Chapter 10. Exercise 4.
	RomanNumeral.h
	Convert numbers between RomanNumeral and Decimal form

	@author Nick Alexander
	@version 5/20/2015
*/

#include <string>

class RomanNumeral {
private:
/* String of numerals */
	std::string numeral;

/* Decimal Value */
	int decimal;

/*
	Given a single numeral, returns the decimal value.

	For example, valueOf('V') returns 5.

	@param numeral
	Single numeral to convert to a decimal value.
	Note: Only M, D, C, L, X, V, and I are valid values.

	@return Decimal representation of the singular numeral if valid;
			-1 if invalid value is supplied.
*/
	int valueOf(char numeral);

/*
	Given a decimal value, returns the numeral representation.

	For example, valueOf(5) returns 'V".

	@param decimalValue
	Decimal to convert to a singular numeral.
	Note: Only 1000, 500, 100, 50, 10, 5, and 1 are valid values.

	@return Numeral representation of the decimal value if valid;
			' ' if invalid value is supplied.
*/
	char valueOf(int decimalValue);

public:
/*
	Default Constructor. Sets the numeral to empty string
	and the decimal to 0.
*/
	RomanNumeral();

/*
	Creates a RomanNumeral from a string of numerals.
*/
	RomanNumeral(std::string numeralString);

/*
	Creates a RomanNumeral from a decimal value.
*/
	RomanNumeral(int decimalValue);

/*
	Converts the decimal value to a string of numerals
	and reutrns it.

	@return String of numerals representing the decimal value.
*/
	std::string convertToNumeral();

/*
	Converts the string of numerals to a decimale value and returns it.

	@return Decimal value representing the string of numerals.
*/
	int convertToDecimal();

/*
	Returns the RomanNumeral as a string of numerals.

	@return Roman Numeral representation
*/
	std::string asNumeral();

/*
	Returns the RomanNumeral as a decimal value.

	@return Decimal representation
*/
	int asDecimal();
};