package edu.epam.text.entity;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UnitComposite implements TextComponent {
    List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType componentType;

    public List<TextComponent> getTextComponents() {
        return Collections.unmodifiableList(textComponents);
    }

    public UnitComposite(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public String write() {
        textComponents.removeIf(p -> p == null);
        StringBuilder builder = new StringBuilder();
        switch (componentType){
            case TEXT:
                builder.append("\t");
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write() + "\n\t");
                }
                break;
            case PARAGRAPH:
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write());
                }
                break;
            case SENTENCE:
                for(TextComponent textComponent : textComponents){
                    builder.append(textComponent.write() + " ");
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
        return builder.toString();
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
