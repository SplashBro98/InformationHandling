package test.epam.text.action;

import edu.epam.text.chain.*;
import edu.epam.text.composite.ComponentType;
import edu.epam.text.entity.UnitComposite;
import edu.epam.text.interpreter.Client;
import edu.epam.text.interpreter.Context;
import edu.epam.text.reader.TextReader;
import edu.epam.text.util.PolandNotationConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MainTest {
    private static final String FILEPATH = "input\\input.txt";
    private TextReader reader;
    private Client client;
    private Context context;
    private PolandNotationConverter converter;

    @BeforeClass
    public void setUp(){
        reader = new TextReader();
        client = new Client();
        context = new Context();
        converter = new PolandNotationConverter();
    }






    @Test
    public void mainTest() {
        String text = reader.readInfo(FILEPATH);
        DataParser wordParser = new WordParser();
        DataParser lexemeParser = new LexemeParser(wordParser);
        DataParser sentenceParser = new SentenceParser(lexemeParser);
        DataParser paragraphParser = new ParagraphParser(sentenceParser);
        DataParser textParser = new TextParser(paragraphParser);

        UnitComposite mainComposite = new UnitComposite(ComponentType.TEXT);

        textParser.parseText(mainComposite,text);

        System.out.println(mainComposite.write());



    }
}
