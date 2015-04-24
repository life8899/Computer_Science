package util;

public enum FileMode {
    READ('r'),
    WRITE('w'),
    APPEND('a');

    private char mode;

    FileMode(char mode)
    {
        this.mode = mode;
    }
}