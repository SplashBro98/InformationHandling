package edu.epam.text.chain;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.entity.Symbol;
import edu.epam.text.entity.UnitComposite;
import edu.epam.text.interpreter.Client;
import edu.epam.text.util.PolandNotationConverter;

import java.util.regex.Pattern;

public class LexemeParser extends DataParser {

    private static final String EXPRESSION_REGEX = "[\\d|&<<>>()~^]+";

    public LexemeParser() {
    }

    public LexemeParser(DataParser dataParser) {
        super(dataParser);
    }

    private boolean checkSymbol(char ch){
        return (Character.isLetterOrDigit(ch) || ch == '-');
    }

    public boolean isExpression(String input){
        return Pattern.compile(EXPRESSION_REGEX).matcher(input).matches();
    }

    @Override
    public void parseText(UnitComposite composite, String input) {
        if(isExpression(input)){
            PolandNotationConverter converter = new PolandNotationConverter();
            Client client = new Client();
            client.parse(converter.transform(input));
            int actual = client.calculate();
            UnitComposite current = new UnitComposite(ComponentType.WORD);
            composite.add(current);
            this.getNext().parseText(current, String.valueOf(actual));
            return;
        }
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int startIndex = i;
            if(checkSymbol(characters[i])){
                do{
                    i++;
                }while (i < characters.length && checkSymbol(characters[i]));
                UnitComposite current = new UnitComposite(ComponentType.WORD);
                composite.add(current);
                this.getNext().parseText(current, input.substring(startIndex,i));
                i--;
            }
            else{
                do{
                    composite.add(new Symbol(characters[i],ComponentType.SIGN));
                    i++;
                }while (i < characters.length && !checkSymbol(characters[i]));
                i--;
            }
        }
    }
}
