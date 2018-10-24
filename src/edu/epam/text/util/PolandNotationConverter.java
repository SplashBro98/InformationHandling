package edu.epam.text.util;

import edu.epam.text.interpreter.MathOperation;

import java.util.*;
import static edu.epam.text.util.Separator.*;

public class PolandNotationConverter {

    private Map<String, Integer> operationMap = new TreeMap<>();

    public PolandNotationConverter() {
        for (MathOperation operation : MathOperation.values()) {
            operationMap.put(operation.getOperation(), operation.getPriority());
        }
    }

    public String transform(String inputString) {
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if (Character.isDigit(inputString.charAt(i))) {
                int startIndex = i, current = i;
                while (current < inputString.length() && Character.isDigit(inputString.charAt(current))) {
                    current++;
                    i++;
                }
                i--;
                result.append(inputString.substring(startIndex, current) + SPACE_REGEX);
            } else {
                int current = i;
                String temp;
                if (inputString.charAt(current) == LEFT_SHIFT_SYMBOL) {
                    temp = MathOperation.LEFT_SHIFT.getOperation();
                    i++;
                } else if (inputString.charAt(current) == RIGHT_SHIFT_SYMBOL) {
                    temp = MathOperation.RIGHT_SHIFT.getOperation();
                    i++;
                } else temp = String.valueOf(inputString.charAt(current));
                if (temp.equals(MathOperation.RIGHT_BRACKET.getOperation())) {
                    String peek = deque.pop();
                    while (!peek.equals(MathOperation.LEFT_BRACKET.getOperation())) {
                        result.append(peek + SPACE_REGEX);
                        peek = deque.pop();
                    }
                } else if (!deque.isEmpty() && !temp.equals(MathOperation.LEFT_BRACKET.getOperation()) &&
                        operationMap.get(temp) <= operationMap.get(deque.peek())) {
                    do {
                        result.append(deque.pop() + SPACE_REGEX);
                    }
                    while (!deque.isEmpty() && operationMap.get(temp) <= operationMap.get(deque.peek()));
                    deque.push(temp);
                } else {
                    deque.push(temp);
                }
            }
        }
        while (!deque.isEmpty()) {
            result.append(deque.pop() + SPACE_REGEX);
        }
        return result.toString().trim();
    }
}
