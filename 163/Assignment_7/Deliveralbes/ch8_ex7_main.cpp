/*
	Chapter 8. Exercise 7.
	main.cpp

	Prompts the user for number candidates in an election as well as the number of votes for each candidate. Displays a table
	of candidates with the number of cotes received, the percentage of the votes received, and the winner.

	NOTE: Change NUM_CANDIDATES constant to change the number of candidates

	@author Nick Alexander
	@version 6/25/2015
*/

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

int const NUM_CANDIDATES = 5;

/*
	Loops over NUM_CANDIDATES to obtain that many candidate names

	@param names
	Reference to an array containing the candidate names
*/
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

/*
	Loops over NUM_CANDIDATES to obtain the number of votes for each candidate

	@param votes
	Reference to an array containing the number of votes for the candidate

	@param names
	Reference to an array containing the names of the candidate
*/
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

/*
	Loops over NUM_CANDIDATES and counts the total number of votes across all candidates

	@param votes
	Reference to an array containing the number of votes for each candidate

	@return
	Total number of votes
*/
int totalVotes(int votes[])
{
	int total = 0;
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		total += votes[i];
	}
	return total;
}

/*
	Loops over NUM_CANDIDATES to calculate the percentage of total votes for each candidate

	@param percents
	Reference to an array containing the percentage of votes for each candidate

	@votes
	Reference to an array containing the votes for each candidate
*/
void getPercentages(double percents[], int votes[])
{
	int total = totalVotes(votes);
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		double ratio = static_cast<double>(votes[i]) / total;
		percents[i] = ratio * 100;
	}
}

/*
	Loops over NUM_CANDIDATES to calculate the toal percentage of votes

	@param percents
	Reference to an array cotnaining the percentage of votes for each candidate

	@return
	Total percetnage of votes
*/
double totalPercentage(double percents[])
{
	double totalPercentage = 0.0;
	for (int i = 0; i < NUM_CANDIDATES; i++)
	{
		totalPercentage += percents[i];
	}
	return totalPercentage;
}

/*
	Determines the winner of the election and return the array index of the winner

	@param percents
	Reference to an array containing the percentage of votes for each candidate

	@return
	Array index of the winner
*/
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

/*
	Displays the table of the results of the elction

	@param names
	Refernece to an array containing the names of the candidates

	@param votes
	Reference to an array containing the number of votes for each candidate

	@param percents
	Reference to an array containing the percentage of votes for each candidate
*/
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

/*
	Main Insertion Point
*/
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