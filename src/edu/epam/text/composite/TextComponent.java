package edu.epam.text.composite;

public interface TextComponent {
    String write();
    boolean add(TextComponent component);
    boolean remove(TextComponent component);
}
