/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.observer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;

public abstract class AstObserverAdapter
implements AstObserver {
    @Override
    public void propertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
    }

    @Override
    public void parentChange(Node observedNode, Node previousParent, Node newParent) {
    }

    @Override
    public void listChange(NodeList<?> observedNode, AstObserver.ListChangeType type, int index, Node nodeAddedOrRemoved) {
    }

    @Override
    public void listReplacement(NodeList<?> observedNode, int index, Node oldNode, Node newNode) {
    }
}

