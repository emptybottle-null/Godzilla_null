/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithTraversableScope;
import java.util.Optional;

public interface NodeWithOptionalScope<N extends Node>
extends NodeWithTraversableScope {
    public Optional<Expression> getScope();

    public N setScope(Expression var1);

    public N removeScope();

    @Override
    default public Optional<Expression> traverseScope() {
        return this.getScope();
    }
}

