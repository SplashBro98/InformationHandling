package edu.epam.text.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static edu.epam.text.util.Separator.*;


public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType componentType;

    @Override
    public Optional<List<TextComponent>> getTextComponents() {
        return Optional.of(Collections.unmodifiableList(textComponents));
    }

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public int getAmount() {
        return textComponents.size();
    }

    @Override
    public Character getCharacter() {
        return null;
    }

    @Override
    public String write() {
        textComponents.removeIf(p -> p == null);
        StringBuilder builder = new StringBuilder();
        switch (componentType){
            case TEXT:
                builder.append(TABULATION);
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write() + LINE_TRANSLATION);
                }
                break;
            case PARAGRAPH:
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write());
                }
                break;
            case SENTENCE:
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write() + SPACE_REGEX);
                }
                break;
            case LEXEME:
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write());
                }
                break;
            case WORD:
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write());
                }
                break;
        }
        return builder.toString().trim();
    }

    @Override
    public boolean add(TextComponent component) {
       return textComponents.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return textComponents.remove(component);
    }
}
