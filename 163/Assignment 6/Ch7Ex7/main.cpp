/*
	Chapter 7. Exercise 7.
	main.cpp

	Presents a rudimentary interface for performing arithemetic operations on fractions

	@author Nick Alexander
	@version 6/20/2015
*/

#include <iostream>

using namespace std;


/*
	Displays the User Menu and returns the user selection

	@return
	User Selection	
*/
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

/*
	Prompts the user to enter the numerator and denominator for a fraction
	and stores those values in reference parameters

	@param numerator
	Refernece to fraction numerator

	@param denominator
	Reference to fraction denominator
*/
void getFraction(int& numerator, int& denominator)
{
	cout << "Numerator: ";
	cin >> numerator;
	cout << "Denominator: ";
	cin >> denominator;
}

/*
	Adds two fractions denoted by the numberator and denominator parameters and stores the resulting fraction components in reference parameters

	@param numerator1
	Numerator of the first fraction

	@param denominator1
	Denominator of the first fraction

	@param numerator2
	Numerator of the second fraction

	@param denominator2
	Denominator of the second fraction

	@param resultNumerator
	Numerator of the resulting fraction

	@param resultDenominator
	Denominator of the resulting fraction

*/
void addFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	int commonDenominator = denominator1 * denominator2;
	int commonNumerator1 = numerator1 * denominator2;
	int commonNumerator2 = numerator2 * denominator1;
	resultNumerator = commonNumerator1 + commonNumerator2;
	resultDenominator = commonDenominator;
}

/*
	Subtracts two fractions denoted by the numberator and denominator parameters and stores the resulting fraction components in reference parameters

	@param numerator1
	Numerator of the first fraction

	@param denominator1
	Denominator of the first fraction

	@param numerator2
	Numerator of the second fraction

	@param denominator2
	Denominator of the second fraction

	@param resultNumerator
	Numerator of the resulting fraction

	@param resultDenominator
	Denominator of the resulting fraction

*/
void subtractFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	int commonDenominator = denominator1 * denominator2;
	int commonNumerator1 = numerator1 * denominator2;
	int commonNumerator2 = numerator2 * denominator1;
	resultNumerator = commonNumerator1 - commonNumerator2;
	resultDenominator = commonDenominator;
}

/*
	Multiplies two fractions denoted by the numberator and denominator parameters and stores the resulting fraction components in reference parameters

	@param numerator1
	Numerator of the first fraction

	@param denominator1
	Denominator of the first fraction

	@param numerator2
	Numerator of the second fraction

	@param denominator2
	Denominator of the second fraction

	@param resultNumerator
	Numerator of the resulting fraction

	@param resultDenominator
	Denominator of the resulting fraction

*/
void multiplyFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	resultNumerator = numerator1 * numerator2;
	resultDenominator = denominator1 * denominator2;
}

/*
	Divides two fractions denoted by the numberator and denominator parameters and stores the resulting fraction components in reference parameters

	@param numerator1
	Numerator of the first fraction

	@param denominator1
	Denominator of the first fraction

	@param numerator2
	Numerator of the second fraction

	@param denominator2
	Denominator of the second fraction

	@param resultNumerator
	Numerator of the resulting fraction

	@param resultDenominator
	Denominator of the resulting fraction

*/
void divideFractions(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator)
{
	multiplyFractions(numerator1, denominator1, denominator2, numerator2, resultNumerator, resultDenominator);
}

/*
	Displays the fraction equation in the form: operand operator operand = result

	@param numerator1
	Numerator of the first fraction

	@param denominator1
	Denominator of the first fraction

	@param numerator2
	Numerator of the second fraction

	@param denominator2
	Denominator of the second fraction

	@param resultNumerator
	Numerator of the resulting fraction

	@param resultDenominator
	Denominator of the resulting fraction

	@param fractionOperator
	Character representation of the operator
*/
void printEquation(int numerator1, int denominator1, int numerator2, int denominator2, int& resultNumerator, int& resultDenominator, char fractionOperator)
{
	cout << numerator1 << "/" << denominator1;
	cout << " " << fractionOperator << " ";
	cout << numerator2 << "/" << denominator2;
	cout << " = ";
	cout << resultNumerator << "/" << resultDenominator;
	cout << endl;
}

/*
	Main Insertion Point

	@return
	Exit Code
*/
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