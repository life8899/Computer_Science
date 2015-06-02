/*
	Final Exam
	MoveState.h
	Enumerates all possible Move states

	@author Nick Alexander
	@version 6/1/2015
*/

/*
	An INVALID_MOVE encompasses bad move states such as
	out-of-bounds indexes and indexes that already contain
	a valid token.

	A VALID_MOVE encompasses any move that is not an INVALID_MOVE
	and is not a WINNING_MOVE.

	A WINNING_MOVE is a valid move that causes the game to be won.
*/
enum MoveState {
	INVALID_MOVE, VALID_MOVE, WINNING_MOVE
};