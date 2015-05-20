#include <iostream>

using namespace std;

/*
 * Chapter 4. Exercise 17.
 *
 * Accepts Base Price and Square Footage as input from user for
 * a colonial, split-entry, and single story style house.
 * Calculates the price per square foot of each house
 * and displays the home with the lowest.
 *
 * Nick Alexander
 * May 19, 2015
 */
int main()
{
	float colonialBasePrice, colonialSquareFeet, colonialPricePerSquareFoot;
	cout << "Colonial Base Price: ";
	cin >> colonialBasePrice;
	cout << "Colonial Square Feet: ";
	cin >> colonialSquareFeet;
	colonialPricePerSquareFoot = colonialBasePrice / colonialSquareFeet;

	cout << endl;

	float splitBasePrice, splitSquareFeet, splitPricePerSquareFoot;
	cout << "Split-Entry Base Price: ";
	cin >> splitBasePrice;
	cout << "splitEntry Square Feet: ";
	cin >> splitSquareFeet;
	splitPricePerSquareFoot = splitBasePrice / splitSquareFeet;

	cout << endl;

	float singleBasePrice, singleSquareFeet, singlePricePerSquareFoot;
	cout << "Single-Story Base Price: ";
	cin >> singleBasePrice;
	cout << "Single-Story Square Feet: ";
	cin >> singleSquareFeet;
	singlePricePerSquareFoot = singleBasePrice / singleSquareFeet;

	cout << endl;

	cout << "Colonial = " << colonialPricePerSquareFoot << " $/sqft" << endl;
	cout << "Split-Entry = " << splitPricePerSquareFoot << " $/sqft" << endl;
	cout << "Single-Story = " << singlePricePerSquareFoot << " $/sqft" << endl;

	float least;

	if (colonialPricePerSquareFoot > splitPricePerSquareFoot) {                 // (colonialPricePerSquareFoot > splitPricePerSquareFoot)
		if (splitPricePerSquareFoot > singlePricePerSquareFoot) {               // (colonialPricePerSquareFoot > splitPricePerSquareFoot) + (splitPricePerSquareFoot > singlePricePerSquareFoot)
			least = singlePricePerSquareFoot;                                   // colonialPricePerSquareFoot > splitPricePerSquareFoot > singlePricePerSquareFoot
		} else {                                                                // (colonialPricePerSquareFoot > splitPricePerSquareFoot) + (singlePricePerSquareFoot > splitPricePerSquareFoot)
			if (colonialPricePerSquareFoot > singlePricePerSquareFoot) {        // (colonialPricePerSquareFoot > splitPricePerSquareFoot) + (singlePricePerSquareFoot > splitPricePerSquareFoot) + (colonialPricePerSquareFoot > singlePricePerSquareFoot)
				least = splitPricePerSquareFoot;                                // colonialPricePerSquareFoot > singlePricePerSquareFoot > splitPricePerSquareFoot
			} else {                                                            // (colonialPricePerSquareFoot > splitPricePerSquareFoot) + (singlePricePerSquareFoot > splitPricePerSquareFoot) + (singlePricePerSquareFoot > colonialPricePerSquareFoot)
				least = splitPricePerSquareFoot;                                // singlePricePerSquareFoot > colonialPricePerSquareFoot > splitPricePerSquareFoot
			}
		}
	} else {                                                                    // (splitPricePerSquareFoot > colonialPricePerSquareFoot)
		if (colonialPricePerSquareFoot > singlePricePerSquareFoot) {            // (splitPricePerSquareFoot > colonialPricePerSquareFoot) + (colonialPricePerSquareFoot > singlePricePerSquareFoot)
			least = singlePricePerSquareFoot;                                   // splitPricePerSquareFoot > colonialPricePerSquareFoot > singlePricePerSquareFoot
		} else {                                                                // (splitPricePerSquareFoot > colonialPricePerSquareFoot) + (singlePricePerSquareFoot > colonialPricePerSquareFoot)
			if (splitPricePerSquareFoot > singlePricePerSquareFoot) {           // (splitPricePerSquareFoot > colonialPricePerSquareFoot) + (singlePricePerSquareFoot > colonialPricePerSquareFoot) + (splitPricePerSquareFoot > singlePricePerSquareFoot)
				least = colonialPricePerSquareFoot;                             // splitPricePerSquareFoot > singlePricePerSquareFoot > colonialPricePerSquareFoot
			} else {                                                            // (splitPricePerSquareFoot > colonialPricePerSquareFoot) + (singlePricePerSquareFoot > colonialPricePerSquareFoot) + (singlePricePerSquareFoot > splitPricePerSquareFoot)
				least = colonialPricePerSquareFoot;                             // singlePricePerSquareFoot > splitPricePerSquareFoot > colonialPricePerSquareFoot
			}
		}
	}

	cout << endl;

	if (least == colonialPricePerSquareFoot) {
		cout << "Colonial has the Lowest Price per Square Foot at " << colonialPricePerSquareFoot << " $/ft" << endl;
	} else if (least == splitPricePerSquareFoot) {
		cout << "Split-Entry has the Lowest Price per Square Foot at " << splitPricePerSquareFoot << "$/ft" << endl;
	} else if (least == singlePricePerSquareFoot){
		cout << "Single-Story has the Lowest Price per Square Foot at " << singlePricePerSquareFoot << "$/ft" << endl;
	}
	return 0;
}