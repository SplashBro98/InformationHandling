package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.UnitComposite;

import java.util.Arrays;
import java.util.List;

public class SentenceParser extends DataParser {
    private static final String DIVIDE_INTO_LEXEMS = "\\p{Blank}+";
    private static final ComponentType NEW_COMPONENT_TYPE = ComponentType.LEXEME;


    public SentenceParser(DataParser dataParser) {
        super(dataParser);

    }

    @Override
    public void parseText(TextComponent composite, String input) {
        List<String> stringList = Arrays.asList(input.trim().split(DIVIDE_INTO_LEXEMS));
        for (String string : stringList) {
            UnitComposite current = new UnitComposite(NEW_COMPONENT_TYPE);
            composite.add(current);
            this.getNext().parseText(current, string);
        }
    }
}
