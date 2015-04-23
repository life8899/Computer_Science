import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Analyzer
{
	private static Object IOException;

	public static void main(String[] args)
	{
		try {
			String filePath = "/Users/Nick/Developer/ComputerScience/300/program.txt";
			FileInputStream fis = new FileInputStream(filePath);
			char current = ' ';
			boolean done = false;

			do {
				if (fis.available() > 0) {
					current = (char) fis.read();
				} else done = true;

				String lexeme = "";
				if (current == ' ') {
					System.out.println("Lexeme " + current + "\t|\tToken SPACE");
				} else if (current == '+') {
					System.out.println("Lexeme " + current + "\t|\tToken ADD_OP");
				} else if (current == '*') {
					System.out.println("Lexeme " + current + "\t|\tToken MULT_OP");
				} else if (current == '=') {
					System.out.println("Lexeme " + current + "\t|\tToken ASSIGN_OP");
				} else if (Character.isLetter(current)) {
					do {
						lexeme += current;
						if (fis.available() > 0) {
							current = (char)fis.read();
						} else current = ' ';
					} while (Character.isLetter(current) || Character.isDigit(current));
					System.out.println("Lexeme " + lexeme + "\t|\tToken IDENT");
				} else if (Character.isDigit(current)) {
					do {
						lexeme += current;
						if (fis.available() > 0) {
							current = (char)fis.read();
						} else current = ' ';
					} while (Character.isDigit(current));
					System.out.println("Lexeme " + lexeme + "\t|\tToken INT_CONST");
				} else {
					System.out.println("Lexeme " + current + "\t|\tUNDEFINED TOKEN");
				}
			} while (!done);
		} catch (java.io.IOException e) {
			System.out.println("IO Error!");
			System.exit(-1);
		}
	}
}