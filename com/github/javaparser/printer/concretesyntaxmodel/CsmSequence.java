/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import java.util.List;
import java.util.Objects;

public class CsmSequence
implements CsmElement {
    private List<CsmElement> elements;

    public CsmSequence(List<CsmElement> elements) {
        if (elements == null) {
            throw new NullPointerException();
        }
        if (elements.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Null element in the sequence");
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
}

