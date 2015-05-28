#include "RomanNumeral.h"

RomanNumeral::RomanNumeral()
{
	this->numeral = "";
	this->decimal = 0;
}


RomanNumeral::RomanNumeral(std::string numeralString)
{
	this->numeral = numeralString;
	this->decimal = 0;
}

RomanNumeral::RomanNumeral(int decimalValue)
{
	this->decimal = decimalValue;
	this->numeral = "";
}

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

char RomanNumeral::valueOf(int value)
{
	if (value == 1000) {
		return 'M';
	} else if (value == 500) {
		return 'D';
	} else if (value == 100) {
		return 'C';
	} else if (value == 50) {
		return 'L';
	} else if (value == 10) {
		return 'X';
	} else if (value == 5) {
		return 'V';
	} else if (value == 1) {
		return 'I';
	} else
	{
		return ' '; // ERROR
	}
}

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

int RomanNumeral::asDecimal()
{
	if (this->decimal == 0) {
		this->convertToDecimal();
	}
	return this->decimal;
}

std::string RomanNumeral::asNumeral()
{
	if (this->numeral == "") {
		this->convertToNumeral();
	}
	return this->numeral;
}