package edu.epam.text.entity;

import edu.epam.text.composite.ComponentType;
import edu.epam.text.composite.TextComponent;

public class Symbol implements TextComponent {
    private Character character;
    private ComponentType componentType;

    public Symbol(Character character, ComponentType componentType) {
        this.character = character;
        this.componentType = componentType;
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
}
