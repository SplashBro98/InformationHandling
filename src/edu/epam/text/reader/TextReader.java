package edu.epam.text.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class TextReader {
    private static Logger logger = LogManager.getLogger();

    public String readInfo(String filepath){
        FileReader fileReader;
        try{
            fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> stream = bufferedReader.lines();
            StringBuilder builder = new StringBuilder();
            stream.forEach(o -> builder.append(" " + o));
            return builder.toString().trim();

        }catch (IOException e){
            logger.log(Level.FATAL,"Wrong filepath: " + filepath, e);
            throw new RuntimeException(e);
        }
    }
}
