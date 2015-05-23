#include <iostream>
#include <string>

using namespace std;

int const NUM_CANDIDATES = 5;

void getNames(string names[])
{
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		cout << "Candidate " << (i+1) << ": ";
		string name;
		getline(cin, name);
		names[i] = name;
	}
}

void displayCandidates(string names[])
{
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		cout << names[i] << endl;
	}
}

void getVotes(int votes[], string names[])
{
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		cout << "Votes for " << names[i] << ": ";
		int vote;
		cin >> vote;
		votes[i] = vote;
	}
	cout << "Here!" << endl;
}

void displayVotes(int votes[], string names[])
{
	for (int i = 0; i < NUM_CANDIDATES; i++) {
		cout << names[i] << " = " << votes[i] << endl;
	}
}

int totalVotes(int votes[])
{
	int total = 0;
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		total += votes[i];
	}
	return total;
}

void getPercentages()
{
	
}

int main()
{
	string candidates[NUM_CANDIDATES];
	int votes[NUM_CANDIDATES];
	double percents[NUM_CANDIDATES];

	getNames(candidates);
	displayCandidates(candidates);

	getVotes(votes, candidates);
	displayVotes(votes, candidates);

	getchar();

	return 0;
}