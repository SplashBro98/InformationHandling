package test.epam.text.reading;


import edu.epam.text.reader.TextReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ReaderTest {

    private TextReader reader;

    @BeforeClass
    public void setUp(){
        reader = new TextReader();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void checkFilepath(){
        String string = reader.readInfo("input\\in.txt");
    }

}
