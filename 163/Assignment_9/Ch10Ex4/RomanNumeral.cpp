/*
	Chapter 10. Exercise 4.
	RomanNumeral.cpp
	Convert numbers between RomanNumeral and Decimal form

	@author Nick Alexander
	@version 5/20/2015
*/

#include "RomanNumeral.h"

/*
	Default Constructor. Sets the numeral to empty string
	and the decimal to 0.
*/
RomanNumeral::RomanNumeral()
{
	this->numeral = "";
	this->decimal = 0;
}

/*
	Creates a RomanNumeral from a string of numerals.
*/
RomanNumeral::RomanNumeral(std::string numeralString)
{
	this->numeral = numeralString;
	this->decimal = 0;
}

/*
	Creates a RomanNumeral from a decimal value.
*/
RomanNumeral::RomanNumeral(int decimalValue)
{
	this->decimal = decimalValue;
	this->numeral = "";
}

/*
	Given a single numeral, returns the decimal value.

	For example, valueOf('V') returns 5.

	@param numeral
	Single numeral to convert to a decimal value.
	Note: Only M, D, C, L, X, V, and I are valid values.

	@return Decimal representation of the singular numeral if valid;
	-1 if invalid value is supplied.
*/
int RomanNumeral::valueOf(char numeral)
{
	if (numeral == 'M') {
		return 1000;
	} else if (numeral == 'D') {
		return 500;
	} else if (numeral == 'C') {
		return 100;
	} else if (numeral == 'L') {
		return 50;
	} else if (numeral == 'X') {
		return 10;
	} else if (numeral == 'V') {
		return 5;
	} else if (numeral == 'I') {
		return 1;
	} else {
		return -1; // ERROR
	}
}

/*
	Given a decimal value, returns the numeral representation.

	For example, valueOf(5) returns 'V".

	@param decimalValue
	Decimal to convert to a singular numeral.
	Note: Only 1000, 500, 100, 50, 10, 5, and 1 are valid values.

	@return Numeral representation of the decimal value if valid;
	' ' if invalid value is supplied.
*/
char RomanNumeral::valueOf(int decimalValue)
{
	if (decimalValue == 1000) {
		return 'M';
	} else if (decimalValue == 500) {
		return 'D';
	} else if (decimalValue == 100) {
		return 'C';
	} else if (decimalValue == 50) {
		return 'L';
	} else if (decimalValue == 10) {
		return 'X';
	} else if (decimalValue == 5) {
		return 'V';
	} else if (decimalValue == 1) {
		return 'I';
	} else {
		return ' '; // ERROR
	}
}

/*
	Converts the decimal value to a string of numerals
	and reutrns it.

	@return String of numerals representing the decimal value.
*/
std::string RomanNumeral::convertToNumeral()
{
	int decimalValue = this->decimal; // Shadow the value so we don't alter the member value
	std::string numeralString = "";

	while (decimalValue >= 1000) { // M
		numeralString += valueOf(1000);
		decimalValue -= 1000;
	}

	while (decimalValue >= 900) { // CM
		numeralString += valueOf(100);
		numeralString += valueOf(1000);
		decimalValue -= 900;
	}

	while (decimalValue >= 500) { // D
		numeralString += valueOf(500);
		decimalValue -= 500;
	}

	while (decimalValue >= 400) { // CD
		numeralString += valueOf(100);
		numeralString += valueOf(500);
		decimalValue -= 400;
	}

	while (decimalValue >= 100) { // C
		numeralString += valueOf(100);
		decimalValue -= 100;
	}

	while (decimalValue >= 90) { // XC
		numeralString += valueOf(10);
		numeralString += valueOf(100);
		decimalValue -= 90;
	}

	while (decimalValue >= 50) { // L
		numeralString += valueOf(50);
		decimalValue -= 50;
	}

	while (decimalValue >= 40) { // XL
		numeralString += valueOf(10);
		numeralString += valueOf(50);
		decimalValue -= 40;
	}

	while (decimalValue >= 10) { // X
		numeralString += valueOf(10);
		decimalValue -= 10;
	}

	while (decimalValue >= 9) { // IX
		numeralString += valueOf(1);
		numeralString += valueOf(10);
		decimalValue -= 9;
	}

	while (decimalValue >= 5) { // V
		numeralString += valueOf(5);
		decimalValue -= 5;
	}

	while (decimalValue >= 4) { // IV
		numeralString += valueOf(1);
		numeralString += valueOf(5);
		decimalValue -= 4;
	}

	while (decimalValue > 0) { // I
		numeralString += valueOf(1);
		decimalValue -= 1;
	}
	this->numeral = numeralString; // Update the member
	return numeralString; // return the value
}

/*
	Converts the string of numerals to a decimale value and returns it.

	@return Decimal value representing the string of numerals.
*/
int RomanNumeral::convertToDecimal()
{
	int decimalValue = 0;
	int lastValue = 0;
	int currentValue = 0;
	for (char numeral : this->numeral) {
		currentValue = valueOf(numeral);
		if (lastValue != 0 && lastValue < currentValue) {
			decimalValue -= 2 * lastValue;
		}
		lastValue = currentValue;
		decimalValue += currentValue;
	}
	this->decimal = decimalValue;
	return decimalValue;
}

/*
	Returns the RomanNumeral as a decimal value.

	@return Decimal representation
*/
int RomanNumeral::asDecimal()
{
	if (this->decimal == 0) {
		this->convertToDecimal();
	}
	return this->decimal;
}

/*
	Returns the RomanNumeral as a string of numerals.

	@return Roman Numeral representation
*/
std::string RomanNumeral::asNumeral()
{
	if (this->numeral == "") {
		this->convertToNumeral();
	}
	return this->numeral;
}