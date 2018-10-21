package edu.epam.text.action;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.UnitComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Performer {
    private static Logger logger = LogManager.getLogger();

    public Optional<UnitComposite> sortByCountOfSentences(UnitComposite composite) {
        if (composite.getComponentType() != ComponentType.TEXT) {
            logger.log(Level.INFO, "This composite isn`t a text");
            return Optional.empty();
        }
        UnitComposite result = new UnitComposite(ComponentType.TEXT);
        List<TextComponent> buffer = new ArrayList<>();
        for(TextComponent component : composite.getTextComponents().get()){
            buffer.add(component);
        }
        buffer.sort(Comparator.comparingInt(TextComponent::getAmount));
        buffer.forEach(o -> result.add(o));
        return Optional.of(result);
    }

    private Optional<UnitComposite> sortWordsByLength(TextComponent composite) {
        if (composite.getComponentType() != ComponentType.SENTENCE) {
            logger.log(Level.INFO, "This composite isn`t a sentence");
            return Optional.empty();
        }
        List<TextComponent> words = new ArrayList<>();
        for (TextComponent lexeme : composite.getTextComponents().get()) {
            for (TextComponent wordOrSymbol : lexeme.getTextComponents().get()) {
                if (wordOrSymbol.getComponentType() == ComponentType.WORD) {
                    words.add(wordOrSymbol);
                }
            }
        }
        words.sort(Comparator.comparingInt(TextComponent::getAmount));

        int i = 0;
        UnitComposite result = new UnitComposite(ComponentType.SENTENCE);
        for (TextComponent lexeme : composite.getTextComponents().get()) {
            TextComponent newLexeme = new UnitComposite(ComponentType.LEXEME);
            for (TextComponent wordOrSymbol : lexeme.getTextComponents().get()) {
                if (wordOrSymbol.getComponentType() == ComponentType.WORD) {
                    newLexeme.add(words.get(i++));
                } else {
                    newLexeme.add(wordOrSymbol);
                }
            }
            result.add(newLexeme);
        }
        return Optional.of(result);
    }

    public Optional<UnitComposite> sortEverySentence(TextComponent component) {
        if (component.getComponentType() != ComponentType.TEXT) {
            logger.log(Level.INFO, "This composite isn`t a text");
            return Optional.empty();
        }
        UnitComposite result = new UnitComposite(ComponentType.TEXT);
        for (TextComponent oldParagraph : component.getTextComponents().get()) {
            UnitComposite newParagraph = new UnitComposite(ComponentType.PARAGRAPH);
            for (TextComponent oldSentence : oldParagraph.getTextComponents().get()) {
                if (sortWordsByLength(oldSentence).isPresent()) {
                    newParagraph.add(sortWordsByLength(oldSentence).get());
                }
            }
            result.add(newParagraph);
        }
        return Optional.of(result);
    }

    public int countOfSymbol(TextComponent composite, Character ch) {
        int result = 0;
        if (composite.getTextComponents().isPresent()) {
            for (TextComponent component : composite.getTextComponents().get()) {
                result += countOfSymbol(component, ch);
            }
        } else {
            if (composite.getCharacter() == ch) {
                return 1;
            }
        }
        return result;
    }

    public Optional<UnitComposite> sortByCountOfSymbol(TextComponent composite, Character ch) {
        if (composite.getComponentType() != ComponentType.SENTENCE) {
            logger.log(Level.INFO, "This composite isn`t a sentence");
            return Optional.empty();
        }
        Performer performer = new Performer();
        UnitComposite result = new UnitComposite(ComponentType.SENTENCE);
        List<TextComponent> buffer = new ArrayList<>();
        for(TextComponent component : composite.getTextComponents().get()){
            buffer.add(component);
        }
        buffer.sort(((Comparator<TextComponent>)
                (o1, o2) -> Integer.compare(performer.countOfSymbol(o1, ch), performer.countOfSymbol(o2, ch))).
                reversed().thenComparing(Comparator.comparing(o -> o.write().toLowerCase())));
        buffer.forEach(b -> result.add(b));
        return Optional.of(result);
    }
}
