package edu.epam.text.interpreter;

public enum MathOperation {
    OR("|",1),
    AND("&",3),
    XOR("^",2),
    LEFTSHIFT("<<",4),
    RIGHTSHIFT(">>",4),
    NOT("~",5),
    LEFTBRACKET("(",0),
    RIGHTBRACKET("(",0);

    private String operation;
    private int priority;

    MathOperation(String operation, int priority) {
        this.operation = operation;
        this.priority = priority;
    }

    public String getOperation() {
        return operation;
    }
    public int getPriority(){ return priority; }

    //    OR("|"),
//    OR("|"),
//    OR("|"),
//    OR("|"),
//    OR("|"),
//    OR("|"),
//    public static final String NOT = "~";
//    public static final String AND = "&";
//    public static final String XOR = "^";
//    public static final String LEFTSHIFT = "<<";
//    public static final String RIGHTSHIFT = ">>";
//    public static final String LEFTBRACKET = "(";
//    public static final String RIGHTBRACKET = ")";
//    public static final String PLUS = "+";
//    public static final String MINUS = "-";
//    public static final String MULTIPLY = "*";
//    public static final String DIVIDE = "/";


}
