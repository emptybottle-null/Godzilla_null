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

public class ListRemovalChange
implements Change {
    private final ObservableProperty observableProperty;
    private final int index;

    public ListRemovalChange(ObservableProperty observableProperty, int index) {
        this.observableProperty = observableProperty;
        this.index = index;
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
            NodeList newNodeList = new NodeList();
            newNodeList.setParentNode(currentNodeList.getParentNodeForChildren());
            newNodeList.addAll(currentNodeList);
            newNodeList.remove(this.index);
            return newNodeList;
        }
        return new NoChange().getValue(property, node);
    }
}

