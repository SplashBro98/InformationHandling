package edu.epam.text.util;

import edu.epam.text.interpreter.MathOperation;

import java.util.*;

public class PolandNotationConverter {

    private Map<String,Integer> operationMap = new TreeMap<>();

    public PolandNotationConverter() {
        for(MathOperation operation : MathOperation.values()){
            operationMap.put(operation.getOperation(),operation.getPriority());
        }
    }

    public String transform(String inputString){
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if(Character.isDigit(inputString.charAt(i))){
                int startIndex = i, current = i;
                while (current < inputString.length() && Character.isDigit(inputString.charAt(current))){
                    current++;
                    i++;
                }
                i--;
                result.append(inputString.substring(startIndex,current) + " ");
            }
            else{
                int current = i;
                String temp;
                if(inputString.charAt(current) == '<'){
                    temp = "<<";
                    i++;
                }
                else if(inputString.charAt(current) == '>'){
                    temp = ">>";
                    i++;
                }
                else temp = String.valueOf(inputString.charAt(current));
                if(temp.equals(")")){
                    String peek = stack.pop();
                    while (!peek.equals("(")){
                        result.append(peek + " ");
                        peek = stack.pop();
                    }
                }
                else if(!stack.isEmpty() && !temp.equals("(") && operationMap.get(temp) <= operationMap.get(stack.peek())) {
                    do {
                        result.append(stack.pop() + " ");
                    }
                    while (!stack.isEmpty() && operationMap.get(temp) <= operationMap.get(stack.peek()));
                    stack.push(temp);
                }
                else{
                    stack.push(temp);
                }
            }
        }
        while (!stack.isEmpty()){
            result.append(stack.pop() + " ");
        }
        return result.toString().trim();
    }
}
