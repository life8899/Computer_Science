/**
 * Enumerates all possible states for a Tennis Match
 *
 */
public enum State {

	NIL_ALL("0-0"),
	NIL_FIFTEEN("0-15"),
	NIL_THIRTY("0-30"),
	NIL_FORTY("0-40"),

	FIFTEEN_NIL("15-0"),
	FIFTEEN_ALL("15-15"),
	FIFTEEN_THIRTY("15-30"),
	FIFTEEN_FORTY("15-40"),

	THIRTY_NIL("30-0"),
	THIRTY_FIFTEEN("30-15"),
	THIRTY_ALL("30-30"),
	THIRTY_FORTY("30-40"),

	FORTY_NIL("40-0"),
	FORTY_FIFTEEN("40-15"),
	FORTY_THIRTY("40-30"),
	FORTY_ALL("40-40"),

	SERVER_WIN("Server Wins"),
	OPPONENT_WIN("Opponent Wins"),

	FAIL("Error: Input string is not in the language");

	private String message;

	/**
	 * Constructor for the enumeration.
	 *
	 * @param s User-friendly message suitable for console output
	 *
	 */
	State(String s)
	{
		this.message = s;
	}

	/**
	 * Returns the user-friendly message suitable for console output
	 *
	 * @return User-friendly message
	 *
	 */
	public String getMessage()
	{
		return this.message;
	}

}