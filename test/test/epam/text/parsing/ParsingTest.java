package test.epam.text.parsing;

import edu.epam.text.chain.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParsingTest {

    @Test
    public void checkIsExpression(){
        String test = "5|(1&2&(3|(4&(1^5|6&47)|3)|2)|1)";
        LexemeParser parser = new LexemeParser();
        boolean actual = parser.isExpression(test);
        Assert.assertTrue(actual);
    }
}
