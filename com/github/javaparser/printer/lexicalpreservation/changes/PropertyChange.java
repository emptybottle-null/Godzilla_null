/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation.changes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.lexicalpreservation.changes.Change;

public class PropertyChange
implements Change {
    private final ObservableProperty property;
    private final Object oldValue;
    private final Object newValue;

    public PropertyChange(ObservableProperty property, Object oldValue, Object newValue) {
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public ObservableProperty getProperty() {
        return this.property;
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public Object getNewValue() {
        return this.newValue;
    }

    @Override
    public Object getValue(ObservableProperty property, Node node) {
        if (property == this.property) {
            return this.newValue;
        }
        return property.getRawValue(node);
    }
}

