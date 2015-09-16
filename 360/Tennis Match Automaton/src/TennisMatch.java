import java.util.Arrays;
import java.util.Scanner;

/**
 * Tennis Match Insertion Point that contains the main method
 *
 * Prompts the user for input strings until the user enters
 * 'q' or 'Q.' Each input string is processed by the deterministic
 * finite automaton for a Tennis Match to determine if it is in
 * the language.
 *
 */
public class TennisMatch {

	private static String inputString;
	private static char[] alphabet;

	/**
	 * TennisMatch Insertion Point that contains the main method
	 *
	 * Prompts the user for input strings until the user enters
	 * 'q' or 'Q.' Each input string is processed by the deterministic
	 * finite automaton for a Tennis Match to determine if it is in
	 * the language.
	 *
	 */
	@SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args)
    {
	    alphabet = new char[]{'s', 'o'};

	    while (true) {

		    displayAlphabet();

		    displaySeparator();

		    inputString = getInputString();

		    displaySeparator();

		    displayInputString();

		    displaySeparator();

		    displayStates();

		    displaySeparator();

		    System.out.println("Transitions: [");
		    TennisAutomaton automaton = new TennisAutomaton(alphabet, inputString.toLowerCase(), true);
		    State exitState = automaton.process();
		    System.out.println("]");

		    displaySeparator();

		    if (exitState != State.SERVER_WIN && exitState != State.OPPONENT_WIN) {
			    System.out.println(String.format("The string '%s' is NOT in the language!", inputString));
		    } else {
			    System.out.println(String.format("%s\nThe string '%s' is in the language.", exitState.getMessage(), inputString));
		    }

		    displaySeparator();
		    displaySeparator();
	    }
    }

	/**
	 * Prompts the user for an input string to process by the deterministic finite automaton
	 * for a Tennis Match. If the user enters 'q' or 'Q,' exits the program.
	 *
	 * @return Input string from the user
	 */
	private static String getInputString()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please Enter an Input String: ");
		String input = scanner.nextLine();
		if (input.toLowerCase().equals("q")) {
			System.exit(0);
		}
		return input;
	}

	/**
	 * Prints two empty lines to the console to separate sections
	 */
	private static void displaySeparator()
	{
		System.out.println();
		System.out.println();
	}

	/**
	 * Prints the input string, with label, to the console
	 *
	 * Format:
	 *  Input String: SOS
	 *
	 */
	private static void displayInputString()
	{
		System.out.println(String.format("Input String: %s", inputString));
	}

	/**
	 * Prints the alphabet set, with label, to the console
	 *
	 * Format:
	 *  Alphabet: [S, O]
	 *
	 */
	private static void displayAlphabet() {
		System.out.print("Alphabet: [");
		for (int i = 0; i < alphabet.length; i++) {
			System.out.print(alphabet[i]);
			if (i != alphabet.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	/**
	 * Prints the state set, with label, to the console
	 *
	 * Format:
	 *  States: [
	 *      STATE 1
	 *      STATE 2
	 *  ]
	 *
	 */
	private static void displayStates() {
		System.out.println("States: [");

		Arrays.stream(State.values()).forEach(v -> System.out.println(String.format("\t%17s\t|\t%s", v.name(), v.getMessage())));

		System.out.println("]");
	}
}