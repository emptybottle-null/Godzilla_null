/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmToken;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;
import com.github.javaparser.printer.lexicalpreservation.LexicalDifferenceCalculator;

public class Removed
implements DifferenceElement {
    private final CsmElement element;

    Removed(CsmElement element) {
        this.element = element;
    }

    public String toString() {
        return "Removed{" + this.element + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Removed removed = (Removed)o;
        return this.element.equals(removed.element);
    }

    public int hashCode() {
        return this.element.hashCode();
    }

    @Override
    public CsmElement getElement() {
        return this.element;
    }

    public Node getChild() {
        if (this.isChild()) {
            LexicalDifferenceCalculator.CsmChild csmChild = (LexicalDifferenceCalculator.CsmChild)this.element;
            return csmChild.getChild();
        }
        throw new IllegalStateException("Removed is not a " + LexicalDifferenceCalculator.CsmChild.class.getSimpleName());
    }

    public int getTokenType() {
        if (this.isToken()) {
            CsmToken csmToken = (CsmToken)this.element;
            return csmToken.getTokenType();
        }
        throw new IllegalStateException("Removed is not a " + CsmToken.class.getSimpleName());
    }

    @Override
    public boolean isAdded() {
        return false;
    }

    public boolean isToken() {
        return this.element instanceof CsmToken;
    }

    public boolean isChild() {
        return this.element instanceof LexicalDifferenceCalculator.CsmChild;
    }

    public boolean isPrimitiveType() {
        if (this.isChild()) {
            LexicalDifferenceCalculator.CsmChild csmChild = (LexicalDifferenceCalculator.CsmChild)this.element;
            return csmChild.getChild() instanceof PrimitiveType;
        }
        return false;
    }

    public boolean isWhiteSpace() {
        if (this.isToken()) {
            CsmToken csmToken = (CsmToken)this.element;
            return csmToken.isWhiteSpace();
        }
        return false;
    }

    @Override
    public boolean isRemoved() {
        return true;
    }

    public boolean isNewLine() {
        if (this.isToken()) {
            CsmToken csmToken = (CsmToken)this.element;
            return csmToken.isNewLine();
        }
        return false;
    }
}

