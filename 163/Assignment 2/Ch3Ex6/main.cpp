#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;


/*
 * Chapter 3. Exercise 6.
 *
 * Prompts user for path to data file containing employee information.
 * Formats and displays employee first name, last name, and salary after
 * raise.
 *
 * Note: data file must respect the following format:
 *      lastName -space- firstName -space- Salary -space- PercentRaise
 *
 * For Example:
 *      Alexander Nicholas 35000.50 5
 *
 * Nick Alexander
 * May 19, 2015
 */
int main()
{
	string dataFilePath;
	cout << "Path to Data File: ";
	getline(cin, dataFilePath); // Get data file path from user

	ifstream dataFile;
	dataFile.open(dataFilePath);

	const int colWidth = 10;
	cout << fixed << showpoint << setprecision(2); // format to 2 decimal places

	// Header
	cout << left << setw(colWidth) << "First";
	cout << left << setw(colWidth) << "Last";
	cout << left << setw(colWidth) << "New Salary" << endl;

	cout << endl;

	while (dataFile.good()) { // Loop until file does not have data
		string lastName, firstName;
		float currentSalary, percentRaise;

		dataFile >> lastName >> firstName >> currentSalary >> percentRaise; // Extract data from file

		float updatedSalary = currentSalary + (currentSalary * (percentRaise/100)); // Calculate new salary

		cout << left << setw(colWidth) << firstName << setw(colWidth) << lastName << setw(colWidth) << updatedSalary << endl;
	}

	return 0;
}