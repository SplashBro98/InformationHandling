package test.epam.text.reading;


import edu.epam.text.reader.TextReader;
import org.testng.annotations.Test;



public class ReaderTest {
    private static final String FILEPATH = "input\\input.txt";

    @Test
    public void readText(){
        TextReader reader = new TextReader();
        System.out.println(reader.readInfo(FILEPATH));
    }
}
