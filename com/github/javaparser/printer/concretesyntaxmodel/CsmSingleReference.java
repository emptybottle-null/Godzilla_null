/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.ConcreteSyntaxModel;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;

public class CsmSingleReference
implements CsmElement {
    private final ObservableProperty property;

    public ObservableProperty getProperty() {
        return this.property;
    }

    public CsmSingleReference(ObservableProperty property) {
        this.property = property;
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        Node child = this.property.getValueAsSingleReference(node);
        if (child != null) {
            ConcreteSyntaxModel.genericPrettyPrint(child, printer);
        }
    }
}

