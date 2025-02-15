/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;

public class CsmIndent
implements CsmElement {
    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        printer.indent();
    }

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        return obj instanceof CsmIndent;
    }
}

