/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.lexicalpreservation.ChildTextElement;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TextElementMatcher;
import com.github.javaparser.printer.lexicalpreservation.TextElementMatchers;
import com.github.javaparser.printer.lexicalpreservation.TokenTextElement;
import java.util.LinkedList;
import java.util.List;

class NodeText {
    private final List<TextElement> elements;
    public static final int NOT_FOUND = -1;

    NodeText(List<TextElement> elements) {
        this.elements = elements;
    }

    NodeText() {
        this(new LinkedList<TextElement>());
    }

    void addElement(TextElement nodeTextElement) {
        this.elements.add(nodeTextElement);
    }

    void addElement(int index, TextElement nodeTextElement) {
        this.elements.add(index, nodeTextElement);
    }

    void addChild(Node child) {
        this.addElement(new ChildTextElement(child));
    }

    void addChild(int index, Node child) {
        this.addElement(index, new ChildTextElement(child));
    }

    void addToken(int tokenKind, String text) {
        this.elements.add(new TokenTextElement(tokenKind, text));
    }

    void addToken(int index, int tokenKind, String text) {
        this.elements.add(index, new TokenTextElement(tokenKind, text));
    }

    int findElement(TextElementMatcher matcher) {
        return this.findElement(matcher, 0);
    }

    int findElement(TextElementMatcher matcher, int from) {
        int res = this.tryToFindElement(matcher, from);
        if (res == -1) {
            throw new IllegalArgumentException(String.format("I could not find child '%s' from position %d. Elements: %s", matcher, from, this.elements));
        }
        return res;
    }

    int tryToFindElement(TextElementMatcher matcher, int from) {
        for (int i = from; i < this.elements.size(); ++i) {
            TextElement element = this.elements.get(i);
            if (!matcher.match(element)) continue;
            return i;
        }
        return -1;
    }

    int findChild(Node child) {
        return this.findChild(child, 0);
    }

    int findChild(Node child, int from) {
        return this.findElement(TextElementMatchers.byNode(child), from);
    }

    int tryToFindChild(Node child) {
        return this.tryToFindChild(child, 0);
    }

    int tryToFindChild(Node child, int from) {
        return this.tryToFindElement(TextElementMatchers.byNode(child), from);
    }

    public void remove(TextElementMatcher matcher, boolean potentiallyFollowingWhitespace) {
        int i = 0;
        for (TextElement e : this.elements) {
            if (!matcher.match(e)) continue;
            this.elements.remove(e);
            if (potentiallyFollowingWhitespace) {
                if (i < this.elements.size()) {
                    if (this.elements.get(i).isWhiteSpace()) {
                        this.elements.remove(i);
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    void removeElement(int index) {
        this.elements.remove(index);
    }

    void replace(TextElementMatcher position, TextElement newElement) {
        int index = this.findElement(position, 0);
        this.elements.remove(index);
        this.elements.add(index, newElement);
    }

    String expand() {
        StringBuffer sb = new StringBuffer();
        this.elements.forEach(e -> sb.append(e.expand()));
        return sb.toString();
    }

    int numberOfElements() {
        return this.elements.size();
    }

    TextElement getTextElement(int index) {
        return this.elements.get(index);
    }

    List<TextElement> getElements() {
        return this.elements;
    }

    public String toString() {
        return "NodeText{" + this.elements + '}';
    }
}

