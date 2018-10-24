package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.TextComposite;
import edu.epam.text.util.Separator;

import java.util.Arrays;
import java.util.List;

public class TextParser extends DataParser {
    private static final ComponentType NEW_COMPONENT_TYPE = ComponentType.PARAGRAPH;

    public TextParser(DataParser dataParser) {
        super(dataParser);
    }

    public void parseText(TextComponent composite, String input) {
        List<String> stringList = Arrays.asList(input.trim().split(Separator.TABULATION));
        for (String string : stringList) {
            TextComposite current = new TextComposite(NEW_COMPONENT_TYPE);
            composite.add(current);
            this.getNext().parseText(current, string);
        }
    }
}
