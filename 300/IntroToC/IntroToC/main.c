#include <stdio.h>
#include <stdlib.h>

void partOne()
{
	printf("Hello, World!");
}

void partTwo()
{
	float first, second;
	printf("Enter a Decimal Number: ");
	scanf("%f", &first);
	printf("Enter a Decimal Number: ");
	scanf("%f", &second);
	printf("Sum: %f\n", first+second);
	printf("Difference: %f\n", second-first);
	printf("Product: %f\n", first*second);
	printf("Quotient: %f\n", first/second);
}

void partThree()
{
	float floats[4];
	int size = sizeof(floats) / sizeof(float);
	floats[0] = 1.7;
	floats[1] = 2.4;
	floats[2] = 3.16;
	floats[3] = 8.91;
	float min = 0;
	float max = 0;
	float average = 0;
	for (int i = 0; i < size; i++) {
		printf("%8.2f\n", floats[i]);
		
		if (floats[i] > max)
			max = floats[i];
		
		if (floats[i] < min || i == 0)
			min = floats[i];
		
		average += floats[i];
	}
	average = average / size;
	
	printf("Average: %8.2f\n", average);
	printf("Max: %f\n", max);
	printf("Min: %f\n", min);
}

void partFour()
{
	int length = 4;
	float *floats = malloc(length * sizeof(float));
	*floats = 1.7;
	*(floats+1) = 2.4;
	*(floats+2) = 3.16;
	*(floats+3) = 8.91;
	float min = 0;
	float max = 0;
	float average = 0;
	for (int i = 0; i < length; i++) {
		printf("%8.2f\n", *(floats+i));
		
		if (*(floats+i) > max)
			max = *(floats+i);
		
		if (*(floats+i) < min || i == 0)
			min = *(floats+i);
		
		average += *(floats+i);
	}
	average = average / length;
	
	printf("Average: %8.2f\n", average);
	printf("Max: %f\n", max);
	printf("Min: %f\n", min);
}

int numberOfElementsGreaterThanZero(int array[], int arrLen)
{
	int count = 0;
	for (int i = 0; i < arrLen; i++) {
		if (array[i] > 0)
			count++;
	}
	return count;
}

float average(int array[], int arrLen)
{
	int sum = 0;
	for (int i = 0; i < arrLen; i++)
		sum += array[i];
	return (float)sum / arrLen;
}

void partFive()
{
	int nums[7] = {-2, 3, -8, 7, 24, -11, 41};
	int length = sizeof(nums) / sizeof(int);
	int greaterThanZero = numberOfElementsGreaterThanZero(nums, length);
	float avg = average(nums, length);
	printf("N>0: %i\n", greaterThanZero);
	printf("Avg: %.2f\n", avg);
}

int main()
{
	partOne();
	printf("\n");
	
	partTwo();
	printf("\n");
	
	partThree();
	printf("\n");
	
	partFour();
	printf("\n");
	
	partFive();
	
	return 0;
}