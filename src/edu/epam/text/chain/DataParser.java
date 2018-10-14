package edu.epam.text.chain;

import edu.epam.text.entity.UnitComposite;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DataParser {
    DataParser next;
    String regex;

    public DataParser(String regex) {
        this.regex = regex;
    }

    public void setNext(DataParser parser) {
        next = parser;
    }

    public DataParser getNext() {
        return next;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }


//    public void parseText(UnitComposite composite, String input) {
//        List<String> strings = Arrays.asList(input.split(regex));
//        for (String string : strings) {
//            UnitComposite current = new UnitComposite();
//            composite.add(current);
//            if (next != null) {
//                next.parseText(current, string);
//            }
//        }
//    }
    public void parseText(UnitComposite composite, String input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find()){
            UnitComposite current = new UnitComposite();
            composite.add(current);
            if (next != null) {
                next.parseText(current, m.group());
            }
        }
    }
}
