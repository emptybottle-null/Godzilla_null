/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation.changes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.lexicalpreservation.changes.Change;
import com.github.javaparser.printer.lexicalpreservation.changes.NoChange;
import java.util.Optional;

public class ListReplacementChange
implements Change {
    private final ObservableProperty observableProperty;
    private final int index;
    private final Node newValue;

    public ListReplacementChange(ObservableProperty observableProperty, int index, Node newValue) {
        this.observableProperty = observableProperty;
        this.index = index;
        this.newValue = newValue;
    }

    @Override
    public Object getValue(ObservableProperty property, Node node) {
        if (property == this.observableProperty) {
            Object currentRawValue = new NoChange().getValue(property, node);
            if (currentRawValue instanceof Optional) {
                Optional optional = (Optional)currentRawValue;
                currentRawValue = optional.orElseGet(null);
            }
            if (!(currentRawValue instanceof NodeList)) {
                throw new IllegalStateException("Expected NodeList, found " + currentRawValue.getClass().getCanonicalName());
            }
            NodeList currentNodeList = (NodeList)currentRawValue;
            NodeList<Node> newNodeList = new NodeList<Node>();
            newNodeList.setParentNode(currentNodeList.getParentNodeForChildren());
            newNodeList.addAll(currentNodeList);
            newNodeList.set(this.index, this.newValue);
            return newNodeList;
        }
        return new NoChange().getValue(property, node);
    }
}

