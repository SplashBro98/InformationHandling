package edu.epam.text.entity;

import edu.epam.text.composite.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UnitComposite implements Component {
    List<Component> components = new ArrayList<>();

    public List<Component> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public void write() {
        components.removeIf(p -> p == null);
        components.forEach(p -> p.write());
    }

    @Override
    public boolean add(Component component) {
       return components.add(component);
    }
}
