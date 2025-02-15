/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.observer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.Observable;
import com.github.javaparser.ast.observer.ObservableProperty;

public abstract class PropagatingAstObserver
implements AstObserver {
    public static PropagatingAstObserver transformInPropagatingObserver(final AstObserver observer) {
        if (observer instanceof PropagatingAstObserver) {
            return (PropagatingAstObserver)observer;
        }
        return new PropagatingAstObserver(){

            @Override
            public void concretePropertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
                observer.propertyChange(observedNode, property, oldValue, newValue);
            }

            @Override
            public void concreteListChange(NodeList<?> observedNode, AstObserver.ListChangeType type, int index, Node nodeAddedOrRemoved) {
                observer.listChange(observedNode, type, index, nodeAddedOrRemoved);
            }

            @Override
            public void parentChange(Node observedNode, Node previousParent, Node newParent) {
                observer.parentChange(observedNode, previousParent, newParent);
            }
        };
    }

    @Override
    public final void propertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
        this.considerRemoving(oldValue);
        this.considerAdding(newValue);
        this.concretePropertyChange(observedNode, property, oldValue, newValue);
    }

    @Override
    public final void listChange(NodeList<?> observedNode, AstObserver.ListChangeType type, int index, Node nodeAddedOrRemoved) {
        if (type == AstObserver.ListChangeType.REMOVAL) {
            this.considerRemoving(nodeAddedOrRemoved);
        } else if (type == AstObserver.ListChangeType.ADDITION) {
            this.considerAdding(nodeAddedOrRemoved);
        }
        this.concreteListChange(observedNode, type, index, nodeAddedOrRemoved);
    }

    @Override
    public void listReplacement(NodeList<?> observedNode, int index, Node oldNode, Node newNode) {
        if (oldNode == newNode) {
            return;
        }
        this.considerRemoving(oldNode);
        this.considerAdding(newNode);
        this.concreteListReplacement(observedNode, index, oldNode, newNode);
    }

    public void concretePropertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
    }

    public void concreteListChange(NodeList<?> observedNode, AstObserver.ListChangeType type, int index, Node nodeAddedOrRemoved) {
    }

    public void concreteListReplacement(NodeList<?> observedNode, int index, Node oldValue, Node newValue) {
    }

    @Override
    public void parentChange(Node observedNode, Node previousParent, Node newParent) {
    }

    private void considerRemoving(Object element) {
        if (element instanceof Observable && ((Observable)element).isRegistered(this)) {
            ((Observable)element).unregister(this);
        }
    }

    private void considerAdding(Object element) {
        if (element instanceof Node) {
            ((Node)element).registerForSubtree(this);
        } else if (element instanceof Observable) {
            ((Observable)element).register(this);
        }
    }
}

