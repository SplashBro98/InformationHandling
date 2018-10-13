package test.epam.text;

import edu.epam.text.entity.Paragraph;
import edu.epam.text.entity.Sentence;
import edu.epam.text.interpreter.Client;
import edu.epam.text.interpreter.Context;
import edu.epam.text.util.PolandNotationConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Stack;

public class MainTest {

    private Client client;
    private Context context;
    private PolandNotationConverter converter;

    @BeforeClass
    public void setUp(){
        client = new Client();
        context = new Context();
        converter = new PolandNotationConverter();
    }


    @DataProvider(name = "polska")
    public Object[][] dataForPolskaNotation(){
        return new Object[][]{
                {"13 2 <<",52},
                {"4 5 6 | ^",3},
                {"3 5 & 8 |",9},
                {"2 ~ 5 |",-3},
                {"3 5 >>",0},
                {"6 ~ 9 & 3 4 & |",9},
                {"5 1 2 & 3 4 1 5 ^ 6 47 & | & 3 | | 2 | & 1 | |",5}
        };
    }
    @Test(dataProvider = "polska")
    public void checkPolskaNotation(String input, int expected) {
        Client client = new Client();
        client.parse(input);
        int actual = client.calculate();
        Assert.assertEquals(actual,expected);
    }

    @DataProvider(name = "convertation")
    public Object[][] dataToConvert(){
        return new Object[][]{
                {"(3&5)|8","3 5 & 8 |"},
                {"13<<2","13 2 <<"},
                {"3>>5","3 5 >>"},
                {"~6&9|(3&4)","6 ~ 9 & 3 4 & |"},
                {"5|(1&2&(3|(4&(1^5|6&47)|3)|2)|1)","5 1 2 & 3 4 1 5 ^ 6 47 & | & 3 | | 2 | & 1 | |"},
        };
    }
    @Test(dataProvider = "convertation")
    public void checkConvert(String input, String expected){
        String actual = converter.transform(input);
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void composite() {
        Paragraph base = new Paragraph();
        Sentence first = new Sentence();
        Sentence second = new Sentence();
        base.write();
        System.out.println(5|(1&2&(3|(4&(1^5|6&47)|3)|2)|1));

    }
}
