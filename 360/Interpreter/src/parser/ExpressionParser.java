package parser;

import util.InterpreterExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser
{
	private static final Pattern OPERATOR_PATTERN = Pattern.compile("\\+|\\-|\\*|/");
	private static final Pattern ARITHMETIC_EXPRESSION_PATTERN = Pattern.compile(
		NumericParser.getNumericConstantPattern() + "\\s*" + getOperatorPattern() + "\\s*" + NumericParser.getNumericConstantPattern()
	);

	protected static String getOperatorPattern()
	{
		return "(" + OPERATOR_PATTERN.pattern() + ")";
	}

	public static InterpreterExpression parseArithmeticExpression(String input)
	{
		Matcher operatorMatcher = OPERATOR_PATTERN.matcher(input);
		//noinspection ResultOfMethodCallIgnored
		operatorMatcher.find();
		String operator = operatorMatcher.group().trim();
		String[] expressionComponents = new String[3];
		expressionComponents[0] = input.split("\\" + operator)[0].trim();
		expressionComponents[1] = operator;
		expressionComponents[2] = input.split("\\" + operator)[1].trim();
		return new InterpreterExpression(expressionComponents);
	}

	public static boolean isArithmeticExpression(String input)
	{
		return ARITHMETIC_EXPRESSION_PATTERN.matcher(input).matches();
	}
}
