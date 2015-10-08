package parser;

import java.util.regex.Pattern;

public class NumericParser
{
    private static final Pattern INTEGER_CONSTANT_PATTERN = Pattern.compile("-?\\d+");
    private static final Pattern FLOAT_CONSTANT_PATTERN = Pattern.compile("-?\\d*\\.\\d+");
    private static final Pattern NUMERIC_CONSTANT_PATTERN = Pattern.compile(
        getIntegerConstantPattern() + "|" + getFloatConstantPattern()
    );

	protected static String getIntegerConstantPattern()
	{
		return "(" + INTEGER_CONSTANT_PATTERN.pattern() + ")";
	}

	protected static String getFloatConstantPattern()
	{
		return "(" + FLOAT_CONSTANT_PATTERN.pattern() + ")";
	}

	protected static String getNumericConstantPattern()
	{
		return "(" + NUMERIC_CONSTANT_PATTERN.pattern() + ")";
	}

    public static boolean isNumericConstant(String input)
    {
        return NUMERIC_CONSTANT_PATTERN.matcher(input).matches();
    }
}