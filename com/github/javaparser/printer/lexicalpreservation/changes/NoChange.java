/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation.changes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.lexicalpreservation.changes.Change;

public class NoChange
implements Change {
    @Override
    public Object getValue(ObservableProperty property, Node node) {
        return property.getRawValue(node);
    }
}

