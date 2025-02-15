/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithTraversableScope;
import java.util.Optional;

public interface NodeWithScope<N extends Node>
extends NodeWithTraversableScope {
    public Expression getScope();

    public N setScope(Expression var1);

    @Override
    default public Optional<Expression> traverseScope() {
        return Optional.of(this.getScope());
    }
}

