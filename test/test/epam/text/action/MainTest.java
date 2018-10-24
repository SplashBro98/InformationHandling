package test.epam.text.action;

import edu.epam.text.action.Performer;
import edu.epam.text.chain.*;
import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.TextComposite;
import edu.epam.text.interpreter.Client;
import edu.epam.text.interpreter.Context;
import edu.epam.text.reader.TextReader;
import edu.epam.text.util.PolandNotationConverter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainTest {
    private static final String FILEPATH = "input\\input.txt";
    private TextReader reader;
    private Client client;
    private Context context;
    private Performer performer;
    private PolandNotationConverter converter;
    private TextComposite mainComposite;
    private DataParser wordParser;
    private DataParser lexemeParser;
    private DataParser sentenceParser;
    private DataParser paragraphParser;
    private DataParser textParser;
    private String text;

    @BeforeClass
    public void setUp() {
        performer = new Performer();
        reader = new TextReader();
        client = new Client();
        context = new Context();
        converter = new PolandNotationConverter();
        text = reader.readInfo(FILEPATH);
        wordParser = new WordParser();
        lexemeParser = new LexemeParser(wordParser);
        sentenceParser = new SentenceParser(lexemeParser);
        paragraphParser = new ParagraphParser(sentenceParser);
        textParser = new TextParser(paragraphParser);
        mainComposite = new TextComposite(ComponentType.TEXT);
        textParser.parseText(mainComposite, text);
    }


    @Test
    public void mainTest() {

        System.out.println(mainComposite.write());
//        String actual = mainComposite.write();
//        String expected = reader.readInfo(FILEPATH);
//        Assert.assertEquals(actual, expected);


//        mainComposite.getTextComponents().get().stream().forEach(c -> c.getTextComponents().get().stream().
//                forEach(o -> performer.sortWordsByLength(o)));
    }


    @Test
    public void sortParagraphs() {
        mainComposite = performer.sortByCountOfSentences(mainComposite).get();
        String actual = mainComposite.write();
        System.out.println(actual);
        String expected = reader.readInfo("input\\sortParagraphs.txt");
        //Assert.assertEquals(actual, expected);
    }

    @Test
    public void sortSentences() {
        mainComposite = performer.sortEverySentence(mainComposite).get();
        String actual = mainComposite.write();
        System.out.println(actual);
        String expected = reader.readInfo("input\\sortParagraphs.txt");
        //Assert.assertEquals(actual, expected);
    }

    @Test
    public void sortLexemes() {
        //System.out.println(performer.countOfSymbol(mainComposite.getTextComponents().get().get(2), 'a'));

        TextComponent paragraph = mainComposite.getTextComponents().get().get(1);
        TextComponent sentence = paragraph.getTextComponents().get().get(0);
        sentence = performer.sortByCountOfSymbol(sentence,'a').get();
        System.out.println(sentence.write());
    }
}