/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.metamodel.DerivedProperty;
import java.util.Optional;

public interface NodeWithTypeArguments<N extends Node> {
    public Optional<NodeList<Type>> getTypeArguments();

    public N setTypeArguments(NodeList<Type> var1);

    @DerivedProperty
    default public boolean isUsingDiamondOperator() {
        return this.getTypeArguments().isPresent() && this.getTypeArguments().get().isEmpty();
    }

    default public N setDiamondOperator() {
        return this.setTypeArguments(new NodeList<Type>());
    }

    default public N removeTypeArguments() {
        return this.setTypeArguments((NodeList<Type>)null);
    }

    default public N setTypeArguments(Type ... typeArguments) {
        return this.setTypeArguments(NodeList.nodeList((Node[])typeArguments));
    }
}

