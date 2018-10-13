package edu.epam.text.interpreter;

import java.util.EmptyStackException;
import java.util.Stack;

public class Context {
    private Stack<Integer> stack = new Stack<>();

    public void push(int i){
        stack.push(i);
    }
    public int pop(){
        if(!stack.isEmpty()) {
            return stack.pop();
        }
        throw new EmptyStackException();
    }


//    public void interpret(BiFunction<Integer,Integer,Integer> function){
//        int first = stack.pop();
//        int second = stack.pop();
//        stack.push(function.apply(second,first));
//    }
//    public void interpret(Function<Integer,Integer> function){
//        int first = stack.pop();
//        stack.push(function.apply(first));
//    }

}
