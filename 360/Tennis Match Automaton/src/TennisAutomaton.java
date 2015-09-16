/**
 * Accepts an input string and an alphabet and determines if the input string is in the language
 * denoted by the deterministic finite automaton for a Tennis Match.
 *
 */
public class TennisAutomaton {

	private char[] alphabet;
	private State currentState;
	private String inputString;
	private boolean printTransitions;

	/**
	 * Creates an instance of the deterministic finite automaton for a Tennis Match
	 *
	 * @param alphabet All characters that are available in the language
	 * @param inputString String to find in the language
	 * @param printTransitions True if all state transitions should be logged to the console
	 *
	 */
	public TennisAutomaton(char[] alphabet, String inputString, boolean printTransitions)
	{
		this.inputString = inputString;
		this.currentState = State.NIL_ALL;
		this.alphabet = alphabet;
		this.printTransitions = printTransitions;
	}

	/**
	 * Runs the input string through the deterministic finite automaton to determine if it exists in the language
	 *
	 * @return Current state after all characters in the input string have been processed
	 *
	 */
	public State process()
	{
		if (this.inputString.equals("")) {
			return null;
		}

		for (char currentCharacter : this.inputString.toCharArray()) {
			if (isInAlphabet(currentCharacter)) {
				transitionToState(delta(currentCharacter));
			} else {
				return null;
			}
		}

		return this.currentState;
	}

	/**
	 * Transition function over an input character
	 *
	 * @param input Current character in the string
	 *
	 * @return New state after the transition
	 *
	 */
	private State delta(char input)
	{
		switch (currentState) {
			case NIL_ALL: {
				switch(input) {
					case 's': {
						return State.FIFTEEN_NIL;
					}
					case 'o': {
						return State.NIL_FIFTEEN;
					}
					default: {
						return null;
					}
				}
			}
			case NIL_FIFTEEN: {
				switch(input) {
					case 's': {
						return State.FIFTEEN_ALL;
					}
					case 'o': {
						return State.NIL_THIRTY;
					}
					default: {
						return null;
					}
				}
			}
			case NIL_THIRTY: {
				switch(input) {
					case 's': {
						return State.FIFTEEN_THIRTY;
					}
					case 'o': {
						return State.NIL_FORTY;
					}
					default: {
						return null;
					}
				}
			}
			case NIL_FORTY: {
				switch(input) {
					case 's': {
						return State.FIFTEEN_FORTY;
					}
					case 'o': {
						return State.OPPONENT_WIN;
					}
					default: {
						return null;
					}
				}
			}
			case FIFTEEN_NIL: {
				switch(input) {
					case 's': {
						return State.THIRTY_NIL;
					}
					case 'o': {
						return State.FIFTEEN_ALL;
					}
					default: {
						return null;
					}
				}
			}
			case FIFTEEN_ALL: {
				switch(input) {
					case 's': {
						return State.THIRTY_FIFTEEN;
					}
					case 'o': {
						return State.FIFTEEN_THIRTY;
					}
					default: {
						return null;
					}
				}
			}
			case FIFTEEN_THIRTY: {
				switch(input) {
					case 's': {
						return State.THIRTY_ALL;
					}
					case 'o': {
						return State.FIFTEEN_FORTY;
					}
					default: {
						return null;
					}
				}
			}
			case THIRTY_NIL: {
				switch(input) {
					case 's': {
						return State.FORTY_NIL;
					}
					case 'o': {
						return State.THIRTY_FIFTEEN;
					}
					default: {
						return null;
					}
				}
			}
			case THIRTY_FIFTEEN: {
				switch(input) {
					case 's': {
						return State.FORTY_FIFTEEN;
					}
					case 'o': {
						return State.THIRTY_ALL;
					}
					default: {
						return null;
					}
				}
			}
			case THIRTY_ALL: {
				switch(input) {
					case 's': {
						return State.FORTY_THIRTY;
					}
					case 'o': {
						return State.THIRTY_FORTY;
					}
					default: {
						return null;
					}
				}
			}
			case THIRTY_FORTY: {
				switch(input) {
					case 's': {
						return State.FORTY_ALL;
					}
					case 'o': {
						return State.OPPONENT_WIN;
					}
					default: {
						return null;
					}
				}
			}
			case FORTY_NIL: {
				switch(input) {
					case 's': {
						return State.SERVER_WIN;
					}
					case 'o': {
						return State.FORTY_FIFTEEN;
					}
					default: {
						return null;
					}
				}
			}
			case FORTY_FIFTEEN: {
				switch(input) {
					case 's': {
						return State.SERVER_WIN;
					}
					case 'o': {
						return State.FORTY_THIRTY;
					}
					default: {
						return null;
					}
				}
			}
			case FORTY_THIRTY: {
				switch(input) {
					case 's': {
						return State.SERVER_WIN;
					}
					case 'o': {
						return State.FORTY_ALL;
					}
					default: {
						return null;
					}
				}
			}
			case FORTY_ALL: {
				switch(input) {
					case 's': {
						return State.FORTY_THIRTY;
					}
					case 'o': {
						return State.THIRTY_FORTY;
					}
					default: {
						return null;
					}
				}
			}
			case SERVER_WIN: {
				switch(input) {
					case 's': {
						return State.FAIL;
					}
					case 'o': {
						return State.FAIL;
					}
					default: {
						return null;
					}
				}
			}
			case OPPONENT_WIN: {
				switch(input) {
					case 's': {
						return State.FAIL;
					}
					case 'o': {
						return State.FAIL;
					}
					default: {
						return null;
					}
				}
			}
			case FAIL: {
				switch(input) {
					case 's': {
						return State.FAIL;
					}
					case 'o': {
						return State.FAIL;
					}
					default: {
						return null;
					}
				}
			}
			default: {
				return null;
			}
		}
	}

	/**
	 * Determines if the input character is in the deterministic finite automaton's alphabet
	 *
	 * @param inputCharacter Input character
	 *
	 * @return True if the character is in the alphabet
	 *
	 */
	private boolean isInAlphabet(char inputCharacter)
	{
		for (char current : alphabet) {
			if (current == inputCharacter) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the state of the deterministic finite automaton to the new state. Also logs the state transition to the
	 * console if desired
	 *
	 * @param newState New state after delta
	 *
	 */
	private void transitionToState(State newState)
	{
		if (printTransitions) {
			System.out.println(String.format("\t%17s -> %s", this.currentState.name(), newState.name()));
		}
		this.currentState = newState;
	}
}