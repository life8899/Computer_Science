#include <iostream>

using namespace std;

int menu()
{
	cout << "0 - Exit" << endl;
	cout << "1 - Add two fractions" << endl;
	cout << "2 - Subtract two fractions" << endl;
	cout << "3 - Multiply two fractions" << endl;
	cout << "4 - Divide two fractions" << endl;
	cout << "Selection: ";
	int choice;
	cin >> choice;
	return choice;
}

void getFraction(int& numerator, int& denominator)
{
	cout << "Numerator: ";
	cin >> numerator;
	cout << "Denominator: ";
	cin >> denominator;
}

void addFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	int commonDenominator = denominator1 * denominator2;
	int commonNumerator1 = numerator1 * denominator2;
	int commonNumerator2 = numerator2 * denominator1;
	resultNumerator = commonNumerator1 + commonNumerator2;
	resultDenominator = commonDenominator;
}

void subtractFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	int commonDenominator = denominator1 * denominator2;
	int commonNumerator1 = numerator1 * denominator2;
	int commonNumerator2 = numerator2 * denominator1;
	resultNumerator = commonNumerator1 - commonNumerator2;
	resultDenominator = commonDenominator;
}

void multiplyFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	resultNumerator = numerator1 * numerator2;
	resultDenominator = denominator1 * denominator2;
}

void divideFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	multiplyFractions(numerator1, denominator1, denominator2, numerator2, resultNumerator, resultDenominator);
}

void printEquation(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator, char fractionOperator)
{
	cout << numerator1 << "/" << denominator1;
	cout << " " << fractionOperator << " ";
	cout << numerator2 << "/" << denominator2;
	cout << " = ";
	cout << resultNumerator << "/" << resultDenominator;
	cout << endl;
}

int main()
{
	cout << "Fraction Calculator" << endl;

	bool done = false;
	while (!done) {
		int choice = menu();
		if (choice == 0) {
			done = true;
			continue;
		}
		int numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator;
		cout << endl;
		getFraction(numerator1, denominator1);
		cout << endl;
		getFraction(numerator2, denominator2);

		cout << endl;

		if (choice == 1) {
			addFractions(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator);
			printEquation(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator, '+');
		} else if (choice == 2) {
			subtractFractions(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator);
			printEquation(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator, '-');
		} else if (choice == 3) {
			multiplyFractions(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator);
			printEquation(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator, '*');
		} else if (choice == 4) {
			divideFractions(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator);
			printEquation(numerator1, denominator1, numerator2, denominator2, resultNumerator, resultDenominator, '/');
		} else {
			cout << "Error: Invalid Selection. Try Again." << endl;
		}

		cout << endl;
	}

	cout << "Goodbye!" << endl;
	return 0;
}