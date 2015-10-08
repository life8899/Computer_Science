package parser;

import util.InterpreterNumber;

import java.util.regex.Pattern;

public class StringParser
{
	private static Pattern QUIT_PATTERN = Pattern.compile("quit", Pattern.CASE_INSENSITIVE);
	private static Pattern PRINT_CONSTANT_PATTERN = Pattern.compile(
		"print\\s+" + NumericParser.getNumericConstantPattern()
	);
	private static Pattern PRINT_IDENTIFIER_PATTERN = Pattern.compile(
		"print\\s+" + IdentifierParser.getIdentifierPattern()
	);

	public static boolean isQuit(String input)
	{
		return QUIT_PATTERN.matcher(input).matches();
	}

	public static boolean isPrintConstant(String input)
	{
		return PRINT_CONSTANT_PATTERN.matcher(input).matches();
	}

	public static InterpreterNumber parsePrintConstant(String input)
	{
		String constant = input.replace("print ", "");
		return new InterpreterNumber(constant.trim());
	}

	public static boolean isPrintIdentifier(String input)
	{
		return PRINT_IDENTIFIER_PATTERN.matcher(input).matches();
	}

	public static String parsePrintIdentifier(String input)
	{
		String identifierName = input.replace("print ", "");
		return identifierName.trim();
	}
}
