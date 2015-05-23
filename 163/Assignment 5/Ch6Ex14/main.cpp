#include <iostream>
#include <iomanip>

using namespace std;

void initialize(int& x, int &y, char& z)
{
	x = 0;
	y = 0;
	z = ' ';
}

void getHoursRate(double& hours, double& rate)
{
	cout << "Hourly Rate: ";
	cin >> rate;
	cout << "Hours Worked: ";
	cin >> hours;
}

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

void printCheck(double rate, double hours, double wages)
{
	cout << fixed << showpoint << setprecision(2);
	cout << "Hours Worked: " << hours << endl;
	cout << "Hourly Rate: " << rate << endl;
	cout << "Wages: $" << wages << endl;
}

void funcOne(int& x, int y)
{
	int number;
	cout << "Enter a Number: ";
	cin >> number;
	x = 2 * x + y - number;
}

void nextChar(char& z)
{
	int zValue = (int)z; // Cast to int to get ASCII decimal value of character
	int nextVal = zValue + 1; // Move to next ASCII decimal value
	z = (char)nextVal; // Cast ASCII decimal value back to character value
}

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