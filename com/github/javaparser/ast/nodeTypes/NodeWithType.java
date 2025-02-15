/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.utils.Utils;

public interface NodeWithType<N extends Node, T extends Type> {
    public T getType();

    public N setType(T var1);

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public N setType(Class<?> typeClass) {
        this.tryAddImportToParentCompilationUnit(typeClass);
        return this.setType(StaticJavaParser.parseType(typeClass.getSimpleName()));
    }

    default public N setType(String typeString) {
        Utils.assertNonEmpty(typeString);
        return this.setType(StaticJavaParser.parseType(typeString));
    }

    default public String getTypeAsString() {
        return ((Type)this.getType()).asString();
    }
}

