/*
	Chapter 9. Exercise 5.
	main.cpp

	Simulates a cash register for a breakfast diner.
*/

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

int const MENU_LENGTH = 8;
double const SALES_TAX = 0.06;

/*
	Represents an item on the menu containing the item name, price, and purchase quantity
*/
struct menuItemType
{
	string menuItem;
	double menuPrice;
	int quantity;
};

/*
	Initializes the default menu into an array

	@param menu
	Refernece to an array containing the menu as menuItemTypes
*/
void initMenu(menuItemType menu[])
{
	menuItemType plainEgg;
	plainEgg.menuItem = "Plain Egg";
	plainEgg.menuPrice = 1.45;
	plainEgg.quantity = 0;
	menu[0] = plainEgg;

	menuItemType baconEgg;
	baconEgg.menuItem = "Bacon and Egg";
	baconEgg.menuPrice = 2.45;
	baconEgg.quantity = 0;
	menu[1] = baconEgg;

	menuItemType muffin;
	muffin.menuItem = "Muffin";
	muffin.menuPrice = 0.99;
	muffin.quantity = 0;
	menu[2] = muffin;

	menuItemType frenchToast;
	frenchToast.menuItem = "French Toast";
	frenchToast.menuPrice = 1.99;
	frenchToast.quantity = 0;
	menu[3] = frenchToast;

	menuItemType fruitBasket;
	fruitBasket.menuItem = "Fruit Basket";
	fruitBasket.menuPrice = 2.49;
	fruitBasket.quantity = 0;
	menu[4] = fruitBasket;

	menuItemType cereal;
	cereal.menuItem = "Cereal";
	cereal.menuPrice = 0.69;
	cereal.quantity = 0;
	menu[5] = cereal;

	menuItemType coffee;
	coffee.menuItem = "Coffee";
	coffee.menuPrice = 0.50;
	coffee.quantity = 0;
	menu[6] = coffee;

	menuItemType tea;
	tea.menuItem = "Tea";
	tea.menuPrice = 0.75;
	tea.quantity = 0;
	menu[7] = tea;
}

/*
	Displays the menu and returns the user's choice

	@param menu
	Reference to an array containing the menu

	@param choice
	Reference to an int containing the user's choice
*/
void displayMenu(menuItemType menu[], int& choice)
{
	string h1 = "ID", h2 = "Menu Item", h3 = "Price";
	int pad = 8;

	cout << left << setw(h1.length() + pad) << h1;
	cout << left << setw(h2.length() + pad) << h2;
	cout << left << setw(h3.length() + pad) << h3;
	cout << endl;

	for (int i = 0; i < MENU_LENGTH; i++)
	{
		cout << left << setw(h1.length() + pad) << (i+1);
		cout << left << setw(h2.length() + pad) << menu[i].menuItem;
		cout << left << setw(h3.length() + pad) << menu[i].menuPrice;
		cout << endl;
	}

	cout << "Enter a Menu ID to Add to Order or 0 to Checkout: ";
	cin >> choice;
}

/*
	Calculuates the total before tax for the menu selections

	@param menu
	Reference to an array containing the menu

	@return
	Total cost before taxes
*/
double calculateSubtotal(menuItemType menu[])
{
	double subtotal = 0.0;
	for (int i = 0; i < MENU_LENGTH; i++)
	{
		subtotal += (menu[i].quantity * menu[i].menuPrice);
	}
	return subtotal;
}

/*
	Calculates the sales tax from the subtotal

	@param subtotal
	Subtotal of the purchase

	@return
	Amount of sales tax to be charged
*/
double calculateTax(double subtotal)
{
	return subtotal * SALES_TAX;
}

/*
	Calculates the total of the purchase

	@param subtotal
	Subtotal of the purchase

	@param tax
	Amount of sales tax on the purchase

	@return
	Total cost of the purchase
*/
double calculateTotal(double subtotal, double tax)
{
	return subtotal + tax;
}

/*
	Dispalys the receipt for the purchase

	@param
	Reference to an array containing the menu
*/
void printTicket(menuItemType menu[])
{
	cout << fixed << showpoint << setprecision(2);
	string h1 = "Price", h2 = "Count", h3 = "Menu Item";
	int pad = 2;

	cout << left << setw(h1.length() + pad) << h1;
	cout << left << setw(h2.length() + pad) << h2;
	cout << left << setw(h3.length() + pad) << h3;
	cout << endl;

	for (int i = 0; i < MENU_LENGTH; i++)
	{
		if (menu[i].quantity != 0)
		{
			cout << left << setw(h1.length() + pad) << menu[i].quantity * menu[i].menuPrice;
			cout << left << setw(h2.length() + pad) << menu[i].quantity;
			cout << left << setw(h3.length() + pad) << menu[i].menuItem;
			cout << endl;
		}
	}

	cout << endl;

	double subtotal = calculateSubtotal(menu);
	double tax = calculateTax(subtotal);
	double total = calculateTotal(subtotal, tax);

	string subh = "Subtotal ", taxh = "Tax ", toth = "Total ";

	cout << right << setw(subh.length()) << subh;
	cout << left << setw(8 + pad) << subtotal;
	cout << endl;

	cout << right << setw(subh.length()) << taxh;
	cout << left << setw(8 + pad) << tax;
	cout << endl;

	cout << right << setw(subh.length()) << toth;
	cout << left << setw(8 + pad) << total;
	cout << endl;
}


/*
	Main Insertion Point
*/
int main()
{
	menuItemType menu[MENU_LENGTH];
	initMenu(menu);

	bool done = false;
	int choice = 0;
	while (!done)
	{
		displayMenu(menu, choice);
		if (choice >= 0 && choice <= MENU_LENGTH)
		{
			if (choice == 0)
			{
				done = true;
			} else
			{
				menu[choice - 1].quantity++;
			}
		} else
		{
			cout << "Error: Invalid Selection. Try Again" << endl;
		}
		cout << endl;
	}

	cout << endl << endl;

	cout << "Printing Ticket..." << endl << endl;

	printTicket(menu);

	cout << endl << "Y'all come back now!" << endl;
	return 0;
}