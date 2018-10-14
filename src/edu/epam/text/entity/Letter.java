package edu.epam.text.entity;

import edu.epam.text.composite.Component;

public class Letter implements Component {
    private Character character;

    public Letter(Character character) {
        this.character = character;
    }

    @Override
    public void write() {
        System.out.print(character);
    }

    @Override
    public boolean add(Component component) {
        return false;
    }
}
