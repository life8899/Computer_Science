/*
 * Chapter 6. Exercise 8.
 * main.cpp
 *
 * Prompts the user for the center and a point on a circle. Then uses
 * functions to calculate the distance between two points; the radius
 * of a circle; the circumference of a circle; and the area of a circle.
 *
 * @author Nick Alexander
 * @version 6/14/2015
 *
 */

#include <iostream>
#include <math.h>

using namespace std;

const float PI = 3.14159; // Constant for Pi

/*
 * Returns the Euclidean distance between two points
 *
 * @param point1X
 * x component of the first point
 *
 * @param point1Y
 * y component of the first point
 *
 * @param point2X
 * x component of the second point
 *
 * @param point2Y
 * y component of the second point
 *
 * @return Euclidean distance between the points
 */
double distance(int point1X, int point1Y, int point2X, int point2Y)
{
	int xDif = point2X - point1X;
	int yDif = point2Y - point1Y;
	return sqrt(pow(xDif, 2) + pow(yDif, 2));
}

/*
 * Returns the radius of a circle given the center and a point on the circle.
 *
 * @param point1X
 * x component of the first point
 *
 * @param point1Y
 * y component of the first point
 *
 * @param point2X
 * x component of the second point
 *
 * @param point2Y
 * y component of the second point
 *
 * @return Radius of the circle
 */
double radius(int point1X, int point1Y, int point2X, int point2Y)
{
	return distance(point1X, point1Y, point2X, point2Y);
}

/*
 * Returns the circumference of a circle given the radius
 *
 * @param radius
 * Radius of the circle
 *
 * @return Circumference of the circle
 */
double circumference(double radius)
{
	return 2 * PI * radius;
}

/*
 * Returns the area of a circle given the radius
 *
 * @param radius
 * Radius of the circle
 *
 * @return Area of the circle
 */
double area(double radius)
{
	return PI * pow(radius, 2);
}

/*
 * Main entry point
 */
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