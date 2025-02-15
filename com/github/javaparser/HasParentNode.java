/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.Observable;
import java.util.Optional;
import java.util.function.Predicate;

public interface HasParentNode<T>
extends Observable {
    default public boolean hasParentNode() {
        return this.getParentNode().isPresent();
    }

    public Optional<Node> getParentNode();

    public T setParentNode(Node var1);

    public Node getParentNodeForChildren();

    default public <N> Optional<N> findAncestor(Class<N> type) {
        return this.findAncestor(type, x -> true);
    }

    default public <N> Optional<N> findAncestor(Class<N> type, Predicate<N> predicate) {
        Optional<Node> possibleParent = this.getParentNode();
        while (possibleParent.isPresent()) {
            Node parent = possibleParent.get();
            if (type.isAssignableFrom(parent.getClass()) && predicate.test(type.cast(parent))) {
                return Optional.of(type.cast(parent));
            }
            possibleParent = parent.getParentNode();
        }
        return Optional.empty();
    }

    default public boolean isDescendantOf(Node ancestor) {
        return this.findAncestor(Node.class, n -> n == ancestor).isPresent();
    }
}

