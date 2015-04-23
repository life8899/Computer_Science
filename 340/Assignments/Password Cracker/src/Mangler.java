/**
 * Implements Common Password Mangles Except Character Prepend and Character Append
 *
 * @author Nick Alexander
 * @version 2-7-2015
 */
public class Mangler
{
    /**
     * Tests a Given Mangle
     * @param mangle Mangle to Test
     * @param word Word to mangle
     * @return Mangled word
     */
    public static String testMangles(Mangle mangle, String word)
    {
        String mangled = "";
        switch (mangle) {
            case NONE: mangled = noMangle(word); break;
            case UPPER_CASE: mangled = uppercaseMangle(word); break;
            case LOWER_CASE: mangled = lowercaseMangle(word); break;
            case TOGGLE_CASE: mangled = toggleCaseMangle(word); break;
            case INVERTED_TOGGLE_CASE: mangled = invertedToggleCase(word); break;
            case CAPITALIZE: mangled = capitalizeMangle(word); break;
            case INVERTED_CAPITALIZE: mangled = invertedCapitalizeMangle(word); break;
            case REFLECT: mangled = reflectionMangle(word); break;
            case DUPLICATE: mangled = duplicateMangle(word); break;
            case TRIM_FIRST: mangled = trimFirstMangle(word); break;
            case TRIM_LAST: mangled = trimLastMangle(word); break;
        }
        return mangled;
    }

    private static String noMangle(String word)
    {
        return word;
    }

    private static String uppercaseMangle(String word)
    {
        word = word.toUpperCase();
        return word;
    }

    private static String lowercaseMangle(String word)
    {
        word = word.toLowerCase();
        return word;
    }

    private static String toggleCaseMangle(String word)
    {
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (i % 2 == 1) {
                newWord += Character.toUpperCase(word.charAt(i));
            } else {
                newWord += word.charAt(i);
            }
        }
        return newWord;
    }

    private static String invertedToggleCase(String word)
    {
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (i % 2 == 0) {
                newWord += Character.toUpperCase(word.charAt(i));
            } else {
                newWord += word.charAt(i);
            }
        }
        return newWord;
    }

    private static String capitalizeMangle(String word)
    {
        word = word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
        return word;
    }

    private static String invertedCapitalizeMangle(String word)
    {
        word = word.substring(0, 1) + word.substring(1, word.length()).toUpperCase();
        return word;
    }

    private static String reflectionMangle(String word)
    {
        word = new StringBuilder(word).reverse().toString();
        return word;
    }

    private static String duplicateMangle(String word)
    {
        word = word + word;
        return word;
    }

    private static String trimFirstMangle(String word)
    {
        word = word.substring(1);
        return word;
    }

    private static String trimLastMangle(String word)
    {
        word = word.substring(0, word.length() - 1);
        return word;
    }
}