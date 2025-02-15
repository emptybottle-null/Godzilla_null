/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.javadoc.description;

import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.javadoc.description.JavadocInlineTag;
import com.github.javaparser.javadoc.description.JavadocSnippet;
import com.github.javaparser.utils.Pair;
import java.util.LinkedList;
import java.util.List;

public class JavadocDescription {
    private List<JavadocDescriptionElement> elements = new LinkedList<JavadocDescriptionElement>();

    public static JavadocDescription parseText(String text) {
        Pair<Integer, Integer> nextInlineTagPos;
        JavadocDescription instance = new JavadocDescription();
        int index = 0;
        while ((nextInlineTagPos = JavadocDescription.indexOfNextInlineTag(text, index)) != null) {
            if ((Integer)nextInlineTagPos.a != index) {
                instance.addElement(new JavadocSnippet(text.substring(index, (Integer)nextInlineTagPos.a)));
            }
            instance.addElement(JavadocInlineTag.fromText(text.substring((Integer)nextInlineTagPos.a, (Integer)nextInlineTagPos.b + 1)));
            index = (Integer)nextInlineTagPos.b + 1;
        }
        if (index < text.length()) {
            instance.addElement(new JavadocSnippet(text.substring(index)));
        }
        return instance;
    }

    private static Pair<Integer, Integer> indexOfNextInlineTag(String text, int start) {
        int index = text.indexOf("{@", start);
        if (index == -1) {
            return null;
        }
        int closeIndex = text.indexOf("}", index);
        if (closeIndex == -1) {
            return null;
        }
        return new Pair<Integer, Integer>(index, closeIndex);
    }

    public JavadocDescription() {
    }

    public JavadocDescription(List<JavadocDescriptionElement> elements) {
        this();
        this.elements.addAll(elements);
    }

    public boolean addElement(JavadocDescriptionElement element) {
        return this.elements.add(element);
    }

    public List<JavadocDescriptionElement> getElements() {
        return this.elements;
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        this.elements.forEach(e -> sb.append(e.toText()));
        return sb.toString();
    }

    public boolean isEmpty() {
        return this.toText().isEmpty();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        JavadocDescription that = (JavadocDescription)o;
        return this.elements.equals(that.elements);
    }

    public int hashCode() {
        return this.elements.hashCode();
    }

    public String toString() {
        return "JavadocDescription{elements=" + this.elements + '}';
    }
}

