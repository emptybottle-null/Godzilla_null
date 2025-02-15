/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.concretesyntaxmodel;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.SourcePrinter;
import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.concretesyntaxmodel.CsmNone;
import java.util.Arrays;
import java.util.List;

public class CsmConditional
implements CsmElement {
    private final Condition condition;
    private final List<ObservableProperty> properties;
    private final CsmElement thenElement;
    private final CsmElement elseElement;

    public Condition getCondition() {
        return this.condition;
    }

    public ObservableProperty getProperty() {
        if (this.properties.size() > 1) {
            throw new IllegalStateException();
        }
        return this.properties.get(0);
    }

    public List<ObservableProperty> getProperties() {
        return this.properties;
    }

    public CsmElement getThenElement() {
        return this.thenElement;
    }

    public CsmElement getElseElement() {
        return this.elseElement;
    }

    public CsmConditional(ObservableProperty property, Condition condition, CsmElement thenElement, CsmElement elseElement) {
        this.properties = Arrays.asList(property);
        this.condition = condition;
        this.thenElement = thenElement;
        this.elseElement = elseElement;
    }

    public CsmConditional(List<ObservableProperty> properties, Condition condition, CsmElement thenElement, CsmElement elseElement) {
        if (properties.size() < 1) {
            throw new IllegalArgumentException();
        }
        this.properties = properties;
        this.condition = condition;
        this.thenElement = thenElement;
        this.elseElement = elseElement;
    }

    public CsmConditional(ObservableProperty property, Condition condition, CsmElement thenElement) {
        this(property, condition, thenElement, (CsmElement)new CsmNone());
    }

    @Override
    public void prettyPrint(Node node, SourcePrinter printer) {
        boolean test = false;
        for (ObservableProperty prop : this.properties) {
            test = test || this.condition.evaluate(node, prop);
        }
        if (test) {
            this.thenElement.prettyPrint(node, printer);
        } else {
            this.elseElement.prettyPrint(node, printer);
        }
    }

    public static enum Condition {
        IS_EMPTY,
        IS_NOT_EMPTY,
        IS_PRESENT,
        FLAG;


        boolean evaluate(Node node, ObservableProperty property) {
            if (this == IS_PRESENT) {
                return !property.isNullOrNotPresent(node);
            }
            if (this == FLAG) {
                return property.getValueAsBooleanAttribute(node);
            }
            if (this == IS_EMPTY) {
                NodeList<? extends Node> value = property.getValueAsMultipleReference(node);
                return value == null || value.isEmpty();
            }
            if (this == IS_NOT_EMPTY) {
                NodeList<? extends Node> value = property.getValueAsMultipleReference(node);
                return value != null && !value.isEmpty();
            }
            throw new UnsupportedOperationException(this.name());
        }
    }
}

