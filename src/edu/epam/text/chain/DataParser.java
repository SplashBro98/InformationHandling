package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.Symbol;

public abstract class DataParser {
    private DataParser next = DefaultDataParser.getInstance();


    public DataParser(){}

    public DataParser(DataParser parser) {
        this.next = parser;
    }

    public void setNext(DataParser parser) {
        next = parser;
    }

    public DataParser getNext() {
        return next;
    }

    abstract public void parseText(TextComponent composite, String input);

    private static class DefaultDataParser extends DataParser{
        private static DefaultDataParser instance;

        public static DefaultDataParser getInstance() {
            return instance;
        }

        @Override
        public void parseText(TextComponent composite, String input) {
            char[] characters = input.toCharArray();
            for(char ch : characters){
                composite.add(new Symbol(ch, ComponentType.LETTER));
            }
        }
    }

}