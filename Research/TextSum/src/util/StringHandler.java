package util;

public class StringHandler
{

	public StringHandler()
	{

	}

	public boolean isAlphabetic(String string)
	{
		for (char c : string.toCharArray()) {
			if (!Character.isAlphabetic(c)) {
				return false;
			}
		}
		return true;
	}

	public boolean isNumber(String string)
	{
		try {
			double d = Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
