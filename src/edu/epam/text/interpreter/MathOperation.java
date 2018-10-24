package edu.epam.text.interpreter;

public enum MathOperation {
    OR("|",1),
    AND("&",3),
    XOR("^",2),
    LEFT_SHIFT("<<",4),
    RIGHT_SHIFT(">>",4),
    NOT("~",5),
    LEFT_BRACKET("(",0),
    RIGHT_BRACKET("(",0);

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



}
