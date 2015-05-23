#include <iostream>
#include <fstream>
#include <string>

using namespace std;

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

int main()
{
	ifstream namesFile;
	namesFile.open("C:\\Users\\Nick\\Developer\\Computer_Science\\163\\Assignment 6\\Ch7Ex9V2\\names.txt");

	while (namesFile.good()) {
		string fullName;
		getline(namesFile, fullName);
		string name = reverseName(fullName);
		cout << name << endl;
	}
	getchar();
}