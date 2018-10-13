package edu.epam.text.entity;

import edu.epam.text.composite.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Sentence implements Component {
    private List<Lexema> components = new ArrayList<>();

    public void operate(Consumer<List<Lexema>> consumer){
        consumer.accept(components);
    }

    @Override
    public void write() {
        components.removeIf(s -> s ==null);
        components.forEach(s -> s.write());
    }
}
