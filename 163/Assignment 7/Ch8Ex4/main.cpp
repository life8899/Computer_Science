#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int const scoresCount = 26;

void readScores(string filePath, int refScores[])
{
	ifstream scoresFile;
	scoresFile.open(filePath);
	int counter = 0;
	while (scoresFile.good())
	{
		scoresFile >> refScores[counter];
		counter++;
	}
}

void displayScores(int refScores[])
{
	cout << "0-24:" << endl;
	for (int i = 0; i < scoresCount; i++)
	{
		if (refScores[i] >= 0 && refScores[i] <= 24)
		{
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "25-49:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 25 && refScores[i] <= 49) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "50-74:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 50 && refScores[i] <= 74) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "75-99:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 75 && refScores[i] <= 99) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "100-124:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 100 && refScores[i] <= 124) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "125-149:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 125 && refScores[i] <= 149) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "150-174:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 150 && refScores[i] <= 174) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;

	cout << "175-200:" << endl;
	for (int i = 0; i < scoresCount; i++) {
		if (refScores[i] >= 175 && refScores[i] <= 200) {
			cout << "\t" << refScores[i] << endl;
		}
	}

	cout << endl;
}

int main()
{
	int scores[scoresCount];
	cout << "Path to Scores File: ";
	string scoresFilePath;
	getline(cin, scoresFilePath);
	readScores(scoresFilePath, scores);
	displayScores(scores);
	getchar();
	return 0;
}