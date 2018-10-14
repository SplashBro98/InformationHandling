package test.epam.text.parsing;

import edu.epam.text.chain.*;
import edu.epam.text.entity.UnitComposite;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingTest {


    @Test
    public void demoParse(){
        String input = "Steven Gerrard is the best! Almost like Oneil, stargate. Also like Klopp.";
        String input2 = "Steven2 Gerrard3, Thompson";
        DataParser parser2 = new SentenceParser("[\\w\\s]+(\\.|!|\\?|...)");
        DataParser parser3 = new LexemaParser("\\w+");
        DataParser parser4 = new LetterParser("");
        parser2.setNext(parser3);
        parser3.setNext(parser4);
        UnitComposite text = new UnitComposite();
        parser2.parseText(text,input);

        text.write();

//        Pattern p = Pattern.compile("\\w+");
//        Matcher m = p.matcher(input2);
//        while (m.find()){
//            System.out.println(m.group());
//        }

    }
}
