/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;

public interface NodeWithExpression<N extends Node> {
    public Expression getExpression();

    public N setExpression(Expression var1);

    default public N setExpression(String expression) {
        return this.setExpression((Expression)StaticJavaParser.parseExpression(expression));
    }
}

