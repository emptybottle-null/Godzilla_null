/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmIndent;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.concretesyntaxmodel.CsmUnindent;
import com.github.javaparser.printer.lexicalpreservation.ChildTextElement;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;
import com.github.javaparser.printer.lexicalpreservation.LexicalDifferenceCalculator;
import com.github.javaparser.printer.lexicalpreservation.TextElement;
import com.github.javaparser.printer.lexicalpreservation.TokenTextElement;

public class Added
implements DifferenceElement {
    private final CsmElement element;

    Added(CsmElement element) {
        this.element = element;
    }

    public String toString() {
        return "Added{" + this.element + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Added added = (Added)o;
        return this.element.equals(added.element);
    }

    public int hashCode() {
        return this.element.hashCode();
    }

    @Override
    public CsmElement getElement() {
        return this.element;
    }

    @Override
    public boolean isAdded() {
        return true;
    }

    @Override
    public boolean isRemoved() {
        return false;
    }

    public boolean isIndent() {
        return this.element instanceof CsmIndent;
    }

    public boolean isUnindent() {
        return this.element instanceof CsmUnindent;
    }

    public TextElement toTextElement() {
        if (this.element instanceof LexicalDifferenceCalculator.CsmChild) {
            return new ChildTextElement(((LexicalDifferenceCalculator.CsmChild)this.element).getChild());
        }
        if (this.element instanceof CsmToken) {
            return new TokenTextElement(((CsmToken)this.element).getTokenType(), ((CsmToken)this.element).getContent(null));
        }
        throw new UnsupportedOperationException(this.element.getClass().getSimpleName());
    }
}

