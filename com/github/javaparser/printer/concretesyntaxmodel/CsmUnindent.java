/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;

public class CsmUnindent
implements CsmElement {
    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        printer.unindent();
    }

    public int hashCode() {
        return 2;
    }

    public boolean equals(Object obj) {
        return obj instanceof CsmUnindent;
    }
}

