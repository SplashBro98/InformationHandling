package edu.epam.text.action;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Performer {
    private static Logger logger = LogManager.getLogger();

    public Optional<TextComposite> sortByCountOfSentences(TextComposite composite) {
        if (composite.getComponentType() != ComponentType.TEXT) {
            logger.log(Level.INFO, "This composite isn`t a text");
            return Optional.empty();
        }
        TextComposite result = new TextComposite(ComponentType.TEXT);
        List<TextComponent> buffer = new ArrayList<>();
        for(TextComponent component : composite.getTextComponents().get()){
            buffer.add(component);
        }
        buffer.sort(Comparator.comparingInt(TextComponent::getAmount));
        buffer.forEach(o -> result.add(o));
        return Optional.of(result);
    }

    private Optional<TextComposite> sortWordsByLength(TextComponent composite) {
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
        TextComposite result = new TextComposite(ComponentType.SENTENCE);
        for (TextComponent lexeme : composite.getTextComponents().get()) {
            TextComponent newLexeme = new TextComposite(ComponentType.LEXEME);
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

    public Optional<TextComposite> sortEverySentence(TextComponent component) {
        if (component.getComponentType() != ComponentType.TEXT) {
            logger.log(Level.INFO, "This composite isn`t a text");
            return Optional.empty();
        }
        TextComposite result = new TextComposite(ComponentType.TEXT);
        for (TextComponent oldParagraph : component.getTextComponents().get()) {
            TextComposite newParagraph = new TextComposite(ComponentType.PARAGRAPH);
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

    public Optional<TextComposite> sortByCountOfSymbol(TextComponent composite, Character ch) {
        if (composite.getComponentType() != ComponentType.SENTENCE) {
            logger.log(Level.INFO, "This composite isn`t a sentence");
            return Optional.empty();
        }
        Performer performer = new Performer();
        TextComposite result = new TextComposite(ComponentType.SENTENCE);
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
