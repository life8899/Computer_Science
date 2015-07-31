#include <iostream>

using namespace std;

/*
 * Chapter 2. Exercise 19.
 *
 * Given a number of nickels, dimes, and quarters, calculates the total
 * value and that same value in pennies.
 *
 * Nick Alexander
 * May 18, 2015
 */
int main()
{
	const float PENNY = 0.01;
	const float NICKEL = 0.05;
	const float DIME = 0.10;
	const float QUARTER = 0.25;

	int nickels;
	cout << "Number of Nickels: ";
	cin >> nickels;

	if (nickels < 0) {
		cout << "Error: Number of coins must be non-negative" << endl;
		return -1;
	}

	int dimes;
	cout << "Number of Dimes: ";
	cin >> dimes;

	if (dimes < 0) {
		cout << "Error: Number of coins must be non-negative" << endl;
		return -1;
	}

	int quarters;
	cout << "Number of Quarters: ";
	cin >> quarters;

	if (quarters < 0) {
		cout << "Error: Number of coins must be non-negative" << endl;
		return -1;
	}

	cout << endl;

	float total = (nickels * NICKEL) + (dimes * DIME) + (quarters * QUARTER);

	cout << "Total: $" << total << endl;

	int totalInPennies = (int)(total / PENNY); // int is OK because we cannot have a fraction of a penny

	cout << "Total In Pennies: " << totalInPennies << " Pennies" << endl;

	return 0;
}