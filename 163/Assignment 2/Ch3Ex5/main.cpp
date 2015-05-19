#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

/*
 * Chapter 3. Exercise 5.
 *
 * Prompts user for a data file containing ticket sales information.
 * Formats and displays data for each price level as well as totals over all
 * tickets.
 *
 * Note: the data file must follow the format:
 *      ticketPrice -space- ticketsSold
 * For example:
 *      10 100
 *      20 110
 *      30 120
 *
 *
 * Nick Alexander
 * May 18, 2015
 */
int main()
{
	string dataFilePath;
	cout << "Path To Data File: ";
	getline(cin, dataFilePath); // Get path to data from user

	ifstream dataInput;
	dataInput.open(dataFilePath); // Open data file

	int numberOfTicketsSold = 0;
	float ticketSales = 0.0;

	const int colWidth = 12; // Set a constant width for output columns
	cout << fixed << showpoint << setprecision(2); // Set cout to format to 2 decimal places
	cout << left << setw(colWidth) << "Price($)"; // Display a header
	cout << right << setw(colWidth) << "Sold" << endl;

	while (dataInput.good()) { // Loop until file does not have data
		float ticketPrice;
		int ticketsSold;

		dataInput >> ticketPrice >> ticketsSold; // Extract ticket price and tickets sold from data

		numberOfTicketsSold += ticketsSold;
		ticketSales += (ticketPrice * ticketsSold);

		cout << left << setw(colWidth) << ticketPrice; // Display ticket information
		cout << right << setw(colWidth) << ticketsSold << endl;
	}

	dataInput.close(); // Close the data file

	cout << endl;

	cout << left << setw(colWidth) << "Sales"; // Display totals
	cout << right << setw(colWidth ) << "Sold" << endl;
	cout << left << setw(colWidth) << ticketSales;
	cout << right << setw(colWidth) << numberOfTicketsSold << endl;

	return 0;
}