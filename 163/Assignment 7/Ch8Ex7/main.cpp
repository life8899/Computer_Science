#include <iostream>
#include <string>
#include <iomanip>

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

void getVotes(int votes[], string names[])
{
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		cout << "Votes for " << names[i] << ": ";
		int vote;
		cin >> vote;
		votes[i] = vote;
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

void getPercentages(double percents[], int votes[])
{
	int total = totalVotes(votes);
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		double ratio = static_cast<double>(votes[i]) / total;
		percents[i] = ratio * 100;
	}
}

double totalPercentage(double percents[])
{
	double totalPercentage = 0.0;
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		totalPercentage += percents[i];
	}
	return totalPercentage;
}

int findWinner(double percents[])
{
	double winningPercent = 0.0;
	int winner = 0;
	for (int i = 0; i < NUM_CANDIDATES; i++) {
		if (percents[i] > winningPercent) {
			winningPercent = percents[i];
			winner = i;
		}
	}
	return winner;
}

void displayResults(string names[], int votes[], double percents[])
{
	int winnerIndex = findWinner(percents);

	string h1 = "Candidate", h2 = "Votes Received", h3 = "Percent", h4 = "Winner";
	int pad = 4;
	cout << fixed << showpoint << setprecision(2);
	cout << left << setw(h1.length() + pad) << h1;
	cout << left << setw(h2.length() + pad) << h2;
	cout << left << setw(h3.length() + pad) << h3;
	cout << left << setw(h4.length() + pad) << h4;
	cout << endl;

	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		cout << left << setw(h1.length() + pad) << names[i];
		cout << left << setw(h2.length() + pad) << votes[i];
		cout << left << setw(h3.length() + pad) << percents[i];
		cout << left << setw(h4.length() + pad);
		if (winnerIndex == i)
		{
			cout << "<->";
		}
		cout << endl;
	}
	cout << endl;
	cout << left << setw(h1.length() + pad) << "Totals: ";
	cout << left << setw(h2.length() + pad) << totalVotes(votes);
	cout << left << setw(h3.length() + pad) << totalPercentage(percents);
	cout << endl;
}

int main()
{
	string candidates[NUM_CANDIDATES];
	int votes[NUM_CANDIDATES];
	double percents[NUM_CANDIDATES];

	getNames(candidates);

	cout << endl;

	getVotes(votes, candidates);
	getPercentages(percents, votes);

	cout << endl;

	displayResults(candidates, votes, percents);

	return 0;
}