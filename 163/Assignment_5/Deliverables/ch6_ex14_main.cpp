/*
 * Chapter 6. Exercise 14.
 * main.cpp
 *
 * Demonstrates the capability of combining reference paramters
 * with function return values. Demonstrates how reference parameters
 * modify the data at a memory location whereas simple parameters are local
 * to a function.
 *
 * @author Nick Alexander
 * @version 6/14/2015
 *
 */

#include <iostream>
#include <iomanip>

using namespace std;

/*
 * Initializes x and y as 0. Initializes z as empty character.
 *
 * @param x
 * Reference to an integer
 *
 * @param y
 * Reference to an integer
 *
 * @param z
 * Reference to a char
 *
 */
void initialize(int& x, int &y, char& z)
{
	x = 0;
	y = 0;
	z = ' ';
}

/*
 * Displays the hourly rate and hours worked
 *
 * @param hours
 * Number of hours worked
 *
 * @param rate
 * Hourly rate
 *
 */
void getHoursRate(double& hours, double& rate)
{
	cout << "Hourly Rate: ";
	cin >> rate;
	cout << "Hours Worked: ";
	cin >> hours;
}

/*
 * Returns the wages earned by the worker based on hours worked
 * and hourly rate
 *
 * @param hours
 * Number of hours worked
 *
 * @param rate
 * Hourly rate
 *
 * @return Wages earned by the worker
 *
 */
double payCheck(double rate, double hours)
{
	double wages = 0.0;

	if (hours > 40) {
		double overTime = hours - 40; // Hours over 40
		double overTimeRate = 1.5 * rate; // 1.5 times normal rate
		double nonOverTime = hours - overTime; // Regular hours

		wages += nonOverTime * rate;
		wages += overTime * overTimeRate;
	} else {
		wages += hours * rate;
	}

	return wages;
}

/*
 * Displays the check stub for the worker including the hourly rate,
 * hours worked, and wages earned.
 *
 * @param rate
 * Hourly rate
 *
 * @param hours
 * Number of hours worked
 *
 * @param wages
 * Wages earned by the worker
 *
 */
void printCheck(double rate, double hours, double wages)
{
	cout << fixed << showpoint << setprecision(2);
	cout << "Hours Worked: " << hours << endl;
	cout << "Hourly Rate: " << rate << endl;
	cout << "Wages: $" << wages << endl;
}


/*
 * Prompts the user for a number and sets the value of
 * a x to 2x + y - number
 *
 * @param x
 * Reference to an integer
 *
 * @param y
 * Integer
 *
 */
void funcOne(int& x, int y)
{
	int number;
	cout << "Enter a Number: ";
	cin >> number;
	x = 2 * x + y - number;
}

/*
 * Changes a character to the next character in the ASCII table
 * 
 * @param z
 * Character to increment to next ASCII value
 */
void nextChar(char& z)
{
	int zValue = (int)z; // Cast to int to get ASCII decimal value of character
	int nextVal = zValue + 1; // Move to next ASCII decimal value
	z = (char)nextVal; // Cast ASCII decimal value back to character value
}

/*
 * Main entry point
 */
int main()
{
	int x,y;
	char z;
	double rate, hours;
	double amount;


	x = 5, y = 10, z = 'w';
	cout << "x = " << x << ", y = " << y << ", z = " << z << endl;

	cout << endl;

	cout << "Initialize x, y, and z." << endl;
	initialize(x, y, z);
	cout << "x = " << x << ", y = " << y << ", z = " << z << endl;

	cout << endl;

	x = 12, y = 15, z = 'a';
	cout << "x = " << x << ", y = " << y << ", z = " << z << endl;

	cout << endl;

	cout << "Changing x using funcOne. Changing z using nextChar" << endl;
	funcOne(x, y);
	nextChar(z);
	cout << "x = " << x << ", y = " << y << ", z = " << z << endl;

	cout << endl;

	cout << "Initialize Hours and Rate" << endl;
	getHoursRate(hours, rate);

	cout << endl;

	double wages = payCheck(rate, hours);

	cout << endl;

	printCheck(rate, hours, wages);

	cout << endl;

	return 0;
}