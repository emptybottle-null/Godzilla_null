/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.javadoc.description;

import com.github.javaparser.javadoc.description.JavadocDescriptionElement;

public class JavadocSnippet
implements JavadocDescriptionElement {
    private String text;

    public JavadocSnippet(String text) {
        if (text == null) {
            throw new NullPointerException();
        }
        this.text = text;
    }

    @Override
    public String toText() {
        return this.text;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        JavadocSnippet that = (JavadocSnippet)o;
        return this.text.equals(that.text);
    }

    public int hashCode() {
        return this.text.hashCode();
    }

    public String toString() {
        return "JavadocSnippet{text='" + this.text + '\'' + '}';
    }
}

