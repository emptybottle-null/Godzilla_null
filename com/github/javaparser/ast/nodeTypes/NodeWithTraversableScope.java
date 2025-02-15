/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.expr.Expression;
import java.util.Optional;

public interface NodeWithTraversableScope {
    public Optional<Expression> traverseScope();
}

