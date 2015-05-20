#include <iostream>
#include <cmath>

using namespace std;

/*
 * Chapter 4. Exercise 7.
 *
 * Accepts user input for the values of a, b, and c in
 * a quadratic polynomial. After calculating the discriminant,
 * displays the type of roots. If the roots are real, calculates
 * and displays the roots using the quadratic equation.
 *
 * Nick Alexander
 * May 19, 2015
 */
int main()
{
	int a, b, c;

	cout << "a: ";
	cin >> a;

	cout << "b: ";
	cin >> b;

	cout << "c: ";
	cin >> c;

	float discriminant = pow(b, 2) - 4*a*c;
	cout << "Discriminant = " << discriminant << endl;
	if (discriminant > 0) {
		cout << "Equation has two real roots" << endl;
	} else if (discriminant == 0) {
		cout << "Equation has a single root" << endl;
	} else if (discriminant < 0) {
		cout << "Equation has two complex roots" << endl;
	}

	if (discriminant > 0) { // 2 Real
		float root_1 = ((-b + sqrt(pow(b, 2) - 4*a*c)) / 2*a); // Plus
		float root_2 = ((-b - sqrt(pow(b, 2) - 4*a*c)) / 2*a); // Minus
		cout << "\tRoot 1 = " << root_1 << endl;
		cout << "\tRoot 2 = " << root_2 << endl;
	} else if (discriminant == 0) { // 1 Repeated
		float root_1 = ((-b + sqrt(pow(b, 2) - 4*a*c)) / 2*a);
		cout << "\tRoot = " << root_1 << endl;
	}
	return 0;
}