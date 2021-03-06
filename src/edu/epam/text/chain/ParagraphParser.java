package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.TextComposite;
import edu.epam.text.util.Separator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends DataParser {
    private static final ComponentType NEW_COMPONENT_TYPE = ComponentType.SENTENCE;


    public ParagraphParser(DataParser dataParser) {
        super(dataParser);
    }

    @Override
    public void parseText(TextComponent composite, String input) {
        Pattern p = Pattern.compile(Separator.DIVIDE_INTO_SENTENCES);
        Matcher m = p.matcher(input);
        while (m.find()) {
            TextComposite current = new TextComposite(NEW_COMPONENT_TYPE);
            composite.add(current);
            this.getNext().parseText(current, m.group(1));
        }
    }
}
