/*
 * Chapter 5. Exercise 12.
 * main.cpp
 *
 * Prompts the user for the population of town A and
 * the population of town B. Also prompts the user for
 * the annual population growth rate of town A and town B.
 * Loops until the population of town A is greater than or
 * equal to that of town B. Note: rate of A must be greater
 * than rate B. Displays the number of years required for
 * town A to out-grow town B.
 * 
 * @author Nick Alexander
 * @version 6/12/2015
 */

#include <iostream>
#include <math.h>

using namespace std;

int main()
{
	long populationA, populationB; // long to prevent integer overflows
	float rateA, rateB;

	cout << "Population of Town A: ";
	cin >> populationA;

	cout << "Growth Rate of Town A: ";
	cin >> rateA;

	if (populationA <= 0 || rateA <= 0) {
		cout << "Error: Population nor Rate cannot be zero or less" << endl;
		return -1;
	}

	cout << "Population of Town B: ";
	cin >> populationB;

	cout << "Growth Rate of Town B: ";
	cin >> rateB;

	if (populationB <= 0 || rateB <= 0) {
		cout << "Error: Population nor Rate cannot be zero or less" << endl;
		return -1;
	}


	if (rateA <= rateB) { // Prevent infinite loop
		cout << "Error: Rate A cannot be less than or equal to Rate B" << endl;
	}

	int years = 0;

	while (populationA < populationB) {
		years++;
		populationA += ceil((populationA * rateA)); // Update population A. Ceiling because cannot have fractional population
		populationB += ceil((populationB * rateB)); // Update population B. Ceiling because cannot have fractional population
	}

	cout << endl;

	cout << "After " << years << " Years:" << endl;
	cout << "\tPopulation of Town A: " << populationA << endl;
	cout << "\tPopulation of Town B: " << populationB << endl;

	return 0;
}