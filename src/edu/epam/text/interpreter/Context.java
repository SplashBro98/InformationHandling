package edu.epam.text.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context{
    private Deque<Integer> deque = new ArrayDeque<>();

    public void push(int i) {
        deque.push(i);
    }

    public int pop() {
        return deque.pop();
    }

}
