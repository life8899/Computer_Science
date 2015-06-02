/*
	Chapter 10. Exercise 4.
	main.cpp
	Tests the RomanNumeral Class

	@author Nick Alexander
	@version 5/20/2015
*/

#include <iostream>
#include <string>
#include "RomanNumeral.h"

using namespace std;

/*
	Clears the standard input to remove rogue newline
	characters.
*/
void flushStandardInput()
{
	cin.clear();
	cin.ignore(100, '\n');
}

/*
	Waits for the user to press return before exiting the program.
*/
void exit()
{
	flushStandardInput();
	cout << endl;
	cout << "Press Enter to Exit..." << endl;
	getchar();
}

/*
	Displays the menu and accepts the user selection.
	If user enters 0, returns false to quit execution.
	Otherwise, a Roman Numeral is entered and stored in
	the reference parameter. If an invalid selection is
	entered, returns false.
	
	@param numeal
	Reference parameter to store the created RomanNumeral

	@return True if a RomanNumeral was created
*/
bool displayMenu(RomanNumeral& numeral)
{
	cout << "0 - Exit" << endl;
	cout << "1 - Enter a Roman Numeral" << endl;
	cout << "2 - Enter a Decimal Value" << endl;
	cout << "Select: ";
	int choice;
	cin >> choice;
	if (choice == 0) {
		return false;
	} else if (choice == 1) {
		flushStandardInput();
		cout << "Roman Numeral: ";
		string numeralString;
		getline(cin, numeralString);
		numeral = RomanNumeral(numeralString);
	} else if (choice == 2) {
		cout << "Decimal Value: ";
		int decimalValue;
		cin >> decimalValue;
		numeral = RomanNumeral(decimalValue);
	} else {
		cout << "Error: Invalid Selection." << endl;
		return false;
	}
	cout << endl;
	return true;
}

/*
	Loops until the user enters 0 or an invalid selection.
	After getting entering a numeral, displays the numeral
	and decimal value of the numeral.

	@return 0 if no errors occurred
*/
int main()
{
	RomanNumeral numeral;
	while (displayMenu(numeral)) {
		cout << "Roman Numeral: " << numeral.asNumeral() << endl;
		cout << "Decimal Value: " << numeral.asDecimal() << endl;
		cout << endl << endl;
	}

	exit();

	return 0;
}