package edu.epam.text.entity;

import edu.epam.text.composite.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Paragraph implements Component {
    private List<Sentence> components = new ArrayList<>();



    public void operate(Consumer<List<Sentence>> consumer) {
        consumer.accept(components);
    }

    @Override
    public void write() {
        components.removeIf(p -> p == null);
        components.forEach(p -> p.write());
    }
}
