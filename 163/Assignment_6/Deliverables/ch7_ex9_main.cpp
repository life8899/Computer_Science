/*
	Chapter 7. Exercise 9.
	main.cpp

	Reads a series of names from a file with the format
		lastName, firstName middleName
	The reads the file and reverses the order of the names to the new formet:
		firstName middleName lastName


	NOTE: CHANGE FILE PATH CONSTANT TO PROPER FILE PATH

	@author Nick Alexander
	@version 6/20/2015
*/

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

//const string FILE_PATH = "C:\\Users\\Nick\\Developer\\Computer_Science\\163\\Assignment 6\\Ch7Ex9V2\\names.txt";
const string FILE_PATH = "/home/nalexander/Desktop/Computer_Science/163/Assignment 6/Ch7Ex9/names.txt";

/*
	Given a line from the source file reverses the names into the target format

	@param
	Source string

	@return
	String in the target format
*/
string reverseName(string fullName)
{
	string firstName = "", middleName = "", lastName = "";

	int comma_index = fullName.find(',', 0);
	lastName = fullName.substr(0, comma_index);
	fullName = fullName.substr(comma_index + 2);
	int space_index = fullName.find(' ', 0);
	firstName = fullName.substr(0, space_index);
	if (space_index != -1)
	{
		middleName = fullName.substr(space_index + 1);
	}

	string reversedName = firstName + " ";
	if (!middleName.empty())
	{
		reversedName += middleName + " ";
	}
	reversedName += lastName;
	
	return reversedName;
}

/*
	Main Insertion Point
*/
int main()
{
	ifstream namesFile;
	namesFile.open(FILE_PATH.c_str());

	while (namesFile.good()) {
		string fullName;
		getline(namesFile, fullName);
		string name = reverseName(fullName);
		cout << name << endl;
	}
	getchar();
}