/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;

public interface NodeWithArguments<N extends Node> {
    public N setArguments(NodeList<Expression> var1);

    public NodeList<Expression> getArguments();

    default public Expression getArgument(int i) {
        return (Expression)this.getArguments().get(i);
    }

    default public N addArgument(String arg) {
        return this.addArgument((Expression)StaticJavaParser.parseExpression(arg));
    }

    default public N addArgument(Expression arg) {
        this.getArguments().add(arg);
        return (N)((Node)((Object)this));
    }

    default public N setArgument(int i, Expression arg) {
        this.getArguments().set(i, arg);
        return (N)((Node)((Object)this));
    }
}

