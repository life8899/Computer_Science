#include <iostream>
#include <string>
#include "RomanNumeral.h"

using namespace std;

void flushStandardInput()
{
	cin.clear();
	cin.ignore(100, '\n');
}

void exit()
{
	flushStandardInput();
	cout << endl;
	cout << "Press Enter to Exit..." << endl;
	getchar();
}

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