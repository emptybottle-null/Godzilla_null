/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsmMix
implements CsmElement {
    private List<CsmElement> elements;

    public CsmMix(List<CsmElement> elements) {
        if (elements == null) {
            throw new NullPointerException();
        }
        if (elements.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Null element in the mix");
        }
        this.elements = elements;
    }

    public List<CsmElement> getElements() {
        return this.elements;
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        this.elements.forEach(e -> e.prettyPrint(node, printer));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        CsmMix csmMix = (CsmMix)o;
        return this.elements != null ? this.elements.equals(csmMix.elements) : csmMix.elements == null;
    }

    public int hashCode() {
        return this.elements != null ? this.elements.hashCode() : 0;
    }

    public String toString() {
        return this.elements.stream().map(e -> e.toString()).collect(Collectors.joining(",", "CsmMix[", "]"));
    }
}

