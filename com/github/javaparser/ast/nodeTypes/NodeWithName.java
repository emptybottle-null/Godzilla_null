/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.utils.Utils;

public interface NodeWithName<N extends Node> {
    public Name getName();

    public N setName(Name var1);

    default public N setName(String name) {
        Utils.assertNonEmpty(name);
        return this.setName(StaticJavaParser.parseName(name));
    }

    default public String getNameAsString() {
        return this.getName().asString();
    }
}

