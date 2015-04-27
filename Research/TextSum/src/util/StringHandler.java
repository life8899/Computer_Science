package util;

import java.text.NumberFormat;
import java.util.Locale;

public class StringHandler
{
	public static boolean isAlphabetic(String string)
	{
		for (char c : string.toCharArray()) {
			if (!Character.isAlphabetic(c)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumber(String string)
	{
		try {
			double d = Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static String addThousandsSeparator(int number)
	{
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}

	public static String addThousandsSeparator(double number)
	{
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}
}
