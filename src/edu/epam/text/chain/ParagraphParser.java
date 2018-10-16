package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.entity.UnitComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends DataParser {
    private static final String DIVIDE_INTO_SENTENCES = "([^.!?]+[.!?])";
    private static final ComponentType NEW_COMPONENT_TYPE = ComponentType.SENTENCE;


    public ParagraphParser(DataParser dataParser) {
        super(dataParser);
    }

    @Override
    public void parseText(UnitComposite composite, String input) {
        Pattern p = Pattern.compile(DIVIDE_INTO_SENTENCES);
        Matcher m = p.matcher(input);
        while (m.find()) {
            UnitComposite current = new UnitComposite(NEW_COMPONENT_TYPE);
            composite.add(current);
            this.getNext().parseText(current, m.group(1));

        }
    }
}
