package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.Symbol;

public class WordParser extends DataParser {

    public WordParser() {
    }

    @Override
    public void parseText(TextComponent composite, String input) {
        char[] characters = input.toCharArray();
        for(char ch : characters){
            composite.add(new Symbol(ch, ComponentType.LETTER));
        }
    }
}
