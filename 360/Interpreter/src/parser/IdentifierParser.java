package parser;

import identifier.Identifier;
import main.Interpreter;
import util.InterpreterNumber;

import java.util.regex.Pattern;

public class IdentifierParser
{
	private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[a-z]");
	private static final Pattern CONSTANT_ASSIGNMENT_PATTERN = Pattern.compile(
		getIdentifierPattern() + "\\s*=\\s*" + NumericParser.getNumericConstantPattern()
	);
	private static final Pattern IDENTIFIER_ASSIGNMENT_PATTERN = Pattern.compile(
		getIdentifierPattern() + "\\s*=\\s*" + getIdentifierPattern()
	);
	private static final Pattern IDENTIFIER_CONSTANT_EXPRESSION_ASSIGNMENT = Pattern.compile(
		getIdentifierPattern() + "\\s*=\\s*" + getIdentifierPattern() + "\\s*" + ExpressionParser.getOperatorPattern() +
			"\\s*" + NumericParser.getNumericConstantPattern()
	);
	private static final Pattern IDENTIFIER_EXPRESSION_ASSIGNMENT = Pattern.compile(
		getIdentifierPattern() + "\\s*=\\s*" + getIdentifierPattern() + "\\s*" + ExpressionParser.getOperatorPattern() +
			"\\s*" + getIdentifierPattern()
	);

	protected static String getIdentifierPattern()
	{
		return "(" + IDENTIFIER_PATTERN.pattern() + ")";
	}

	public static Identifier<InterpreterNumber> parseConstantAssignment(String input)
	{
		String[] assignmentComponents = input.split("=");
		String identifierName = assignmentComponents[0].trim();
		String identifierValue = assignmentComponents[1].trim();
		return new Identifier<>(identifierName, new InterpreterNumber(identifierValue));
	}

	public static boolean isIdentifier(String input)
	{
		return IDENTIFIER_PATTERN.matcher(input).matches();
	}

	public static boolean isConstantAssignment(String input)
	{
		return CONSTANT_ASSIGNMENT_PATTERN.matcher(input).matches();
	}

	public static boolean isIdentifierAssignment(String input)
	{
		return IDENTIFIER_ASSIGNMENT_PATTERN.matcher(input).matches();
	}

	public static String[] parseIdentifierAssignment(String input)
	{
		String[] assignmentComponents = input.split("=");
		return new String[]{assignmentComponents[0].trim(), assignmentComponents[1].trim()};
	}

	public static boolean isIdentifierConstantExpressionAssignment(String input)
	{
		return IDENTIFIER_CONSTANT_EXPRESSION_ASSIGNMENT.matcher(input).matches();
	}

	public static String[] parseIdentifierConstantExpressionAssignment(String input)
	{
		String[] assignmentComponents = input.split("=");
		String firstIdentifierName = assignmentComponents[0].trim();
		String secondIdentifierName = assignmentComponents[1].split(ExpressionParser.getOperatorPattern())[0].trim();
		String arithmeticExpression = assignmentComponents[1].replace(secondIdentifierName, Interpreter.numbers.lookup(secondIdentifierName).toString());
		return new String[]{firstIdentifierName, arithmeticExpression};
	}

	public static boolean isIdentifierExpressionAssignment(String input)
	{
		return IDENTIFIER_EXPRESSION_ASSIGNMENT.matcher(input).matches();
	}

	public static String[] parseIdentifierExpressionAssignment(String input)
	{
		String[] assignmentComponents = input.split("=");
		String firstIdentifierName = assignmentComponents[0].trim();
		String[] expressionComponents = assignmentComponents[1].split(ExpressionParser.getOperatorPattern());
		String secondIdentifierName = expressionComponents[0].trim();
		String thirdIdentifierName = expressionComponents[1].trim();
		String arithmeticExpression = assignmentComponents[1].replace(secondIdentifierName, Interpreter.numbers.lookup(secondIdentifierName).toString());
		arithmeticExpression = arithmeticExpression.replace(thirdIdentifierName, Interpreter.numbers.lookup(thirdIdentifierName).toString());
		return new String[]{firstIdentifierName, arithmeticExpression};
	}
}
