package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.entity.UnitComposite;

import java.util.Arrays;
import java.util.List;

public class TextParser extends DataParser {
    private static final String DIVIDE_INTO_PARAGRAPHS = "\\t";//change regex
    private static final ComponentType NEW_COMPONENT_TYPE = ComponentType.PARAGRAPH;

    public TextParser(DataParser dataParser) {
        super(dataParser);
    }

    public void parseText(UnitComposite composite, String input) {
        List<String> stringList = Arrays.asList(input.trim().split(DIVIDE_INTO_PARAGRAPHS));
        for (String string : stringList) {
            UnitComposite current = new UnitComposite(NEW_COMPONENT_TYPE);
            composite.add(current);
            this.getNext().parseText(current, string);
        }
    }
}
