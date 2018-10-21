package edu.epam.text.interpreter;


import java.util.Stack;

public class Context{
    private Stack<Integer> stack = new Stack<>();

    public void push(int i) {
        stack.push(i);
    }

    public int pop() {
        return stack.pop();
    }

}
