package edu.epam.text.util;

public class Separator {
    public static final String DIVIDE_INTO_LEXEMES = "\\p{Blank}+";
    public static final String TABULATION = "\\t";
    public static final String DIVIDE_INTO_SENTENCES = "([^.!?]+[.!?])";
    public static final String EXPRESSION_REGEX = "[\\d|&<{2}>{2}()~^]+";
    public static final String SPACE_REGEX = " ";
    public static final String LINE_TRANSLATION = "\n\t";
    public static final Character HYPHEN = '-';
    public static final Character LEFT_SHIFT_SYMBOL = '<';
    public static final Character RIGHT_SHIFT_SYMBOL = '>';

}
