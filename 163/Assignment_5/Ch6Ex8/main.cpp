#include <iostream>
#include <math.h>

using namespace std;

const float PI = 3.14159;

double distance(int point1X, int point1Y, int point2X, int point2Y)
{
	int xDif = point2X - point1X;
	int yDif = point2Y - point1Y;
	return sqrt(pow(xDif, 2) + pow(yDif, 2));
}

double radius(int point1X, int point1Y, int point2X, int point2Y)
{
	return distance(point1X, point1Y, point2X, point2Y);
}

double circumference(double radius)
{
	return 2 * PI * radius;
}

double area(double radius)
{
	return PI * pow(radius, 2);
}

int main()
{
	int centerX, centerY;
	cout << "Center of Circle:" << endl;
	cout << "\tx = ";
	cin >> centerX;
	cout << "\ty = ";
	cin >> centerY;

	cout << endl;

	int pointX, pointY;
	cout << "Point on Circle:" << endl;
	cout << "\tx = ";
	cin >> pointX;
	cout << "\ty = ";
	cin >> pointY;

	cout << endl;

	cout << "Center of Circle = (" << centerX << ", " << centerY << ")" << endl;
	cout << "Point on Circle = (" << pointX << ", " << pointY << ")" << endl;

	cout << endl;

	double circleRadius = radius(centerX, centerY, pointX, pointY);
	cout << "Radius = " << circleRadius << endl;
	cout << "Circumference = " << circumference(circleRadius) << endl;
	cout << "Area = " << area(circleRadius) << endl;

	return 0;
}