/*
	Final Exam
	TicTacToe.h
	Represents a TicTacToe Game

	@author Nick Alexander
	@version 6/1/2015
*/

#include "Token.h"
#include "MoveState.h"

/* Number of cells on playing board. Normal TicTacToe has 9 cells */
const int BOARD_SIZE = 9;

/*
	Represents a TicTacToe Game
*/
class TicTacToe {

private:
	/* Tracks which player is taking a turn */
	int turn;

	/* Token representing player 1 */
	Token player1;

	/* Token representing player 2 */
	Token player2;

	/* Token representing the player who won the game */
	Token winner;

	/* Array of Tokens representing the playing board */
	Token board[BOARD_SIZE];

	/*
		Accepts two-dimensions, a row and a column, and returns a single
		dimension board array index. Because human input is not zero based
		(i.e. language says Row 1 is the first whereas computationally Row 0
		is the first), when adjust is true, both row and column are decremented
		to transform human, 1-based, input to computational, 0-based values.
		Using the formula 3row + column: a row, column pair is transformed to
		a board array index according to the following diagram and examples.

		Col Col Col
		0   1   2

		Row 0	0 | 1 | 2
				--+---+--
		Row 1	3 | 4 | 5
				--+---+--
		Row 2	6 | 7 | 8

		For example:
		First Cell: row = 0, column = 0:
		(3 * row + column) = (3 * 0 + 0) = (0 + 0) = 0

		Middle Cell: row = 1, column = 1:
		(3 * row + column) = (3 * 1 + 1) = (3 + 1) = 4

		Last Cell: row = 2, column = 2:
		(3 * row + column) = (3 * 2 + 2) = (6 + 2) = 8

		@param row
		Selected Row

		@param column
		Selected Column

		@param adjust
		True if rom and column should be adjusted to correspond with zero-base

		@return Single-dimension board array index
	*/
	int convertToIndex(int, int, bool);

	/*
		Checks each of the rows for a horizontal win.

		0 | 1 | 2
		--+---+--
		3 | 4 | 5
		--+---+--
		6 | 7 | 8

		Checks:
		[0, 1, 2],
		[3, 4, 5],
		[6, 7, 8]

		@param testWinner
		Referene to a Token that is initialzed only if there is a winner.

		@return True if a win was found in the rows
	*/
	bool horizontalCheck(Token&);

	/*
		Checks each of the columns for a vertical win.

		0 | 1 | 2
		--+---+--
		3 | 4 | 5
		--+---+--
		6 | 7 | 8

		Checks:
		[0, 3, 6],
		[1, 4, 7],
		[2, 5, 8]

		@param testWinner
		Referene to a Token that is initialzed only if there is a winner.

		@return True if a win was found in the columns
	*/
	bool verticalCheck(Token&);

	/*
		Checks each of the diagonals for a diagonal win.

		0 | 1 | 2
		--+---+--
		3 | 4 | 5
		--+---+--
		6 | 7 | 8

		Checks:
		[0, 4, 8],
		[2, 4, 6]

		@param testWinner
		Referene to a Token that is initialzed only if there is a winner.

		@return True if a win was found in the diagonals
	*/
	bool diagonalCheck(Token&);

	/*
		Creates a default token to store the winner if found. Calls
		each of the checks to check the entire board for a win. If a
		win is found, the default token is initialzed to the winning
		player token and sets this->winner to the token and returns true.

		@return True if a win was found
	*/
	bool checkForWin();

	/*
		Alternates the turn from player 1 to player 2 or vice versa.
	*/
	void changeTurn();

	/*
		Given a row and column pair, determines if the corresponding
		board array index points to a valid move position. An INVALID_MOVE
		would be if the array index was outside the bounds of the board
		or the specified index is already filled with a player token.

		@param row
		Selected Row

		@param column
		Selected Column

		@return GameState::VALID_MOVE if the move is valid; GameState::INVALID_MOVE otherwise
	*/
	MoveState validateMove(int, int);

public:
	/*
		Default Constructor. Initializes the current turn to 1
		because normal TicTacToe always starts with player 1. Both player 1
		and player 2 are initialized to default Tokens. Winner is also
		initialized to a default Token. Both player 1 and player 2 must be
		updated with setPlayer1/setPlayer2 before the game can begin.
	*/
	TicTacToe();

	/*
		Returns a string representation of the playing board
		according to the standard representation of a TicTacToe
		game:

		X | O | X
		--+---+--
		O | X | O
		--+---+--
		X | O | X

		@return String representation of the playing board
	*/
	std::string boardToString();

	/*
		Sets player1 to a new Token created with tokenSymbol and playerName.

		@param tokenSymbol
		Symbol to be used on the playing board

		@param playerName
		Name of the player
	*/
	void setPlayer1(char, std::string);

	/*
		Sets player2 to a new Token created with tokenSymbol and playerName.

		@param tokenSymbol
		Symbol to be used on the playing board

		@param playerName
		Name of the player
	*/
	void setPlayer2(char, std::string);

	/*
		Accepts a row and column pair corresponding to where the current player,
		specified by turn, wants to place a token. If the move is valid, the token
		is placed, the turn alternates, and checks to see if there is a win on the
		board after the last token placement; otherwise, the move is invalid, returns
		INVALID_MOVE with no other action. If the move was valid, the board is checked
		for a win. If a win is found, returns WINNING_MOVE; otherwise, VALID_MOVE is
		returned.

		@param row
		Selected Row

		@param column
		Selected Column

		@return INVALID_MOVE is the move validation failed;
		VALID_MOVE if the move validation passed but a win was not found;
		WINNING_MOVE if the move validation passed and a win was found.
	*/
	MoveState move(int, int);

	/*
		Resets the game board to a default, playable state.
		The turn is set back to 1; the winner is set to a default token;
		and the board array is reset to all default tokens. Note: the player
		tokens are not changed to allow continued play.
	*/
	void reset();

	/*
		Returns the Winner Token.

		@return Winner Token
	*/
	Token getWinnerToken();

	/*
		Returns the player Token that corresponds to the current turn.

		@return Player 1 Token if turn = 1;
		Player 2 Token if turn = 2.
	*/
	Token getCurrentTurnToken();
};