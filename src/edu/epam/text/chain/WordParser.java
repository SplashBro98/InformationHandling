package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.entity.Symbol;
import edu.epam.text.entity.UnitComposite;

public class WordParser extends DataParser {

    public WordParser() {
    }

    @Override
    public void parseText(UnitComposite composite, String input) {
        char[] characters = input.toCharArray();
        for(char ch : characters){
            composite.add(new Symbol(ch, ComponentType.LETTER));
        }
    }
}
