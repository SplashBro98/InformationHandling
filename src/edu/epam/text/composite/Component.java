package edu.epam.text.composite;

import java.util.List;
import java.util.function.Consumer;

public interface Component {
    void write();
    boolean add(Component component);
}
