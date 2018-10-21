package edu.epam.text.composite;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;


import javafx.scene.effect.Light;
import java.awt.*;





import java.util.List;
import java.util.Optional;

public class Symbol implements TextComponent {
    private Character character;
    private ComponentType componentType;

    public Symbol(Character character, ComponentType componentType) {
        this.character = character;
        this.componentType = componentType;
    }
    private Point point;

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    @Override
    public String write() {
        return character.toString();
    }

    @Override
    public boolean add(TextComponent component) {
        return false;
    }

    @Override
    public boolean remove(TextComponent component) {
        return false;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public Optional<List<TextComponent>> getTextComponents() {
        return Optional.empty();
    }
}
