#include <iostream>
#include <math.h>

using namespace std;

/*
	Chapter 2. Exercise 25

	Calculates the number of gallons of paint required to paint a room
	excluding obstacles such as doors, windows, and shelves.

	Nick Alexander
	May 18, 2015
*/
int main()
{
	const int GALLON_AREA = 120; // Area (sq. ft) that 1 gallon paints

	// Room Dimensions
	int roomLength;
	cout << "Room Length: ";
	cin >> roomLength;
	int roomWidth;
	cout << "Room Width: ";
	cin >> roomWidth;
	int roomHeight;
	cout << "Room Height: ";
	cin >> roomHeight;

	if (roomLength < 0 || roomWidth < 0 || roomHeight < 0) {
		cout << "Error: Dimensions must be non-negative" << endl;
		return -1;
	}

	// Room has 4 walls
	// 2 Walls are long (length)
	// 2 Walls are wide (width)
	int roomLongWallArea = roomLength * roomHeight;
	int roomWideWallArea = roomWidth * roomHeight;

	int roomArea = (2 * roomLongWallArea) + (2 * roomWideWallArea);

	cout << "Total Area of Room: " << roomArea << " Square Feet" << endl;

	cout << endl;

	// Door Dimensions
	int doorLength;
	cout << "Door Length: ";
	cin >> doorLength;
	int doorWidth;
	cout << "Door Width: ";
	cin >> doorWidth;

	if (doorLength < 0 || doorWidth < 0) {
		cout << "Error: Dimensions must be non-negative" << endl;
		return -1;
	}

	int doorArea = doorLength * doorWidth;
	cout << "Door Area: " << doorArea << " Square Feet" << endl;

	cout << endl;

	// Window 1 Dimensions
	int window1Length;
	cout << "Window 1 Length: ";
	cin >> window1Length;
	int window1Width;
	cout << "Window 1 Width: ";
	cin >> window1Width;

	if (window1Length < 0 || window1Width < 0) {
		cout << "Error: Dimensions must be non-negative" << endl;
		return -1;
	}

	int window1Area = window1Length * window1Width;
	cout << "Window 1 Area: " << window1Area << " Square Feet" << endl;

	cout << endl;

	// Window 2 Dimensions
	int window2Length;
	cout << "Window 2 Length: ";
	cin >> window2Length;
	int window2Width;
	cout << "Window 2 Width: ";
	cin >> window2Width;

	if (window2Length < 0 || window2Width < 0) {
		cout << "Error: Dimensions must be non-negative" << endl;
		return -1;
	}

	int window2Area = window2Length * window2Width;
	cout << "Window 2 Area: " << window2Area << " Square Feet" << endl;

	cout << endl;

	// Bookshelf Dimensions
	int shelfLength;
	cout << "Booksehlf Length: ";
	cin >> shelfLength;
	int shelfWidth;
	cout << "Bookshelf Width: ";
	cin >> shelfWidth;

	if (shelfLength < 0 || shelfWidth < 0) {
		cout << "Error: Dimensions must be non-negative" << endl;
		return -1;
	}

	int shelfArea = shelfLength * shelfWidth;
	cout << "Bookshelf Area: " << shelfArea << " Square Feet" << endl;

	cout << endl;

	int totalNoPaintArea = doorArea + window1Area + window2Area + shelfArea;
	cout << "Total Unpainted Area: " << totalNoPaintArea << " Square Feet" << endl;

	cout << endl;

	if (totalNoPaintArea > roomArea) {
		cout << "Error: Unpainted Area cannot exceed Room Area" << endl;
		return -2;
	}

	int areaToPaint = roomArea - totalNoPaintArea;

	cout << "Area to Paint: " << areaToPaint << " Square Feet" << endl;
	cout << "1 Gallon Covers: " << GALLON_AREA << " Square Feet" << endl;

	// Ceiling to Round Up because we cannot purchase a fraction of a gallon
	// Cast to float to avoid integer division problem
	int gallonsNeeded = (int)ceil((float)areaToPaint / (float)GALLON_AREA);

	cout << "Gallons Needed: " << gallonsNeeded << " Gallons" << endl;

	cout << endl;

	cout << "Don't make a mess!" << endl;

	return 0;
}