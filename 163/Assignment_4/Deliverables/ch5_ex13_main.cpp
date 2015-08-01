/*
 * Chapter 5. Exercise 13.
 * main.cpp
 *
 * Iterates over a sequence given an initial value, x, and
 * a number of iterations to perform, k. The sequence is:
 * 
 * if x is even:
 * 	x = x / 2
 * 
 * else:
 * 	x = 3x+1
 *
 * @author Nick Alexander
 * @version 6/10/2015
 */

#include <iostream>

using namespace std;

int main()
{
	int x; // Initial Value
	cout << "x: ";
	cin >> x;

	int k; // Sequence Iterations
	cout << "k: ";
	cin >> k;

	cout << endl;

	int a = x; // Sequence value
	int sequenceCount = 0; // Represents position in sequence

	while (sequenceCount < k) { // Loop k times.
		cout << "a" << sequenceCount << " = " << a << endl;
		sequenceCount++;
		if (a % 2 == 0) { // Even
			a = a / 2;
		} else { // Odd
			a = 3 * a + 1;
		}
	}

	cout << "a" << sequenceCount << " = " << 1 << endl; // Problem states that ak = 1

	return 0;
}