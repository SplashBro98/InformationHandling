package test.epam.text.util;

import edu.epam.text.interpreter.Client;
import edu.epam.text.interpreter.Context;
import edu.epam.text.util.PolandNotationConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConverterTest {
    private PolandNotationConverter converter;
    private Client client;
    private Context context;

    @BeforeClass
    public void setUp(){
        converter = new PolandNotationConverter();
        client = new Client();
        context = new Context();
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
                {"5 1 2 & 3 4 1 5 ^ 6 47 & | & 3 | | 2 | & 1 | |",5},
                {"7 5 ^ 1 2 2 5 2 >> 71 & | << & | 1200 |",1202},
        };
    }
    @Test(dataProvider = "polska")
    public void checkPolskaNotation(String input, int expected) {
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
                {"(7^5|1&2<<(2|5>>2&71))|1200","7 5 ^ 1 2 2 5 2 >> 71 & | << & | 1200 |"}
        };
    }
    @Test(dataProvider = "convertation")
    public void checkConvert(String input, String expected){
        String actual = converter.transform(input);
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }



}
