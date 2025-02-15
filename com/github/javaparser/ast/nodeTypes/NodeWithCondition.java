/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;

public interface NodeWithCondition<N extends Node> {
    public Expression getCondition();

    public N setCondition(Expression var1);
}

