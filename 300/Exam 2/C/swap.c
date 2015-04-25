#include <stdio.h>

void swap(int *x, int *y)
{
	int tmp = *y; // Set the value of tmp to the value at y
	*y = *x; // Set the value at y to the value at x
	*x = tmp; // Set the value at x to the value of tmp
}

int main()
{
	int done = 0;
	while (!done) {
		int first, second;
		printf("Enter an integer or -1 to Quit: ");
		scanf("%i", &first);
		printf("Enter an integer or -1 to Quit: ");
		scanf("%i", &second);
		if (first == -1 || second == -1) {
			printf("Goodbye!\n");
			return 0;
		}
		printf("First: %i\n", first);
		printf("Second: %i\n", second);
		printf("Swapping!\n");
		swap(&first, &second);
		printf("First Swapped: %i\n", first);
		printf("Second Swapped: %i\n", second);
	}
}