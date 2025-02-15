/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.utils.Utils;

public interface NodeWithSimpleName<N extends Node> {
    public SimpleName getName();

    public N setName(SimpleName var1);

    default public N setName(String name) {
        Utils.assertNonEmpty(name);
        return this.setName(new SimpleName(name));
    }

    default public String getNameAsString() {
        return this.getName().getIdentifier();
    }

    default public NameExpr getNameAsExpression() {
        return new NameExpr(this.getName());
    }
}

