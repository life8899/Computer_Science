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