package edu.epam.text.chain;

import edu.epam.text.entity.Letter;
import edu.epam.text.entity.UnitComposite;

import java.util.Arrays;
import java.util.List;

public class LetterParser extends DataParser {

    public LetterParser(String regex) {
        super(regex);
    }

    @Override
    public void parseText(UnitComposite composite, String input) {
        char[] characters = input.toCharArray();
        for(Character string : characters){
            Letter current = new Letter(string);
            composite.add(current);
        }
    }
}
