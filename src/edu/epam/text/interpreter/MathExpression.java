package edu.epam.text.interpreter;

@FunctionalInterface
public interface MathExpression {
    void interpret(Context context);
}
