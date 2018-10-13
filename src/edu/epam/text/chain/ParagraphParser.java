package edu.epam.text.chain;

public class ParagraphParser implements DataParser {
    private DataParser next;

    public ParagraphParser(DataParser next) {
        this.next = next;
    }

    @Override
    public void parseText() {

        next.parseText();
    }
}
