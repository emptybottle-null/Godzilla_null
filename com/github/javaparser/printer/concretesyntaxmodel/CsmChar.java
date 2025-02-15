/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;

public class CsmChar
implements CsmElement {
    private final ObservableProperty property;

    public CsmChar(ObservableProperty property) {
        this.property = property;
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        printer.print("'");
        printer.print(this.property.getValueAsStringAttribute(node));
        printer.print("'");
    }
}

