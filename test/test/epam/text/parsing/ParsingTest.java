package test.epam.text.parsing;

import edu.epam.text.chain.*;
import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComposite;
import edu.epam.text.reader.TextReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParsingTest {
    private static final String FILEPATH = "input\\input.txt";


    @Test
    public void demoParse(){
        String input = "Is the best! Almost like Oneil, stargate. Also like  (3&5)|8 Klopp. And all current liverpool " +
                "squad: Van Djik, Gomez, Robertson, Alexander-Arnold.";
        //"[\\w\\s]+(\\.|!|\\?|...)"
        DataParser wordParser = new WordParser();
        DataParser lexemeParser = new LexemeParser(wordParser);
        DataParser sentenceParser = new SentenceParser(lexemeParser);
        DataParser paragraphParser = new ParagraphParser(sentenceParser);
        DataParser textParser = new TextParser(paragraphParser);

        TextReader reader = new TextReader();

        TextComposite text = new TextComposite(ComponentType.TEXT);
        textParser.parseText(text,reader.readInfo(FILEPATH));


        System.out.println(text.write());

    }

    @Test
    public void checkIsExpression(){
        String test = "5|(1&2&(3|(4&(1^5|6&47)|3)|2)|1)";
        LexemeParser parser = new LexemeParser();
        boolean actual = parser.isExpression(test);
        Assert.assertTrue(actual);
    }
}
