/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ReferenceType;

public interface NodeWithThrownExceptions<N extends Node> {
    public N setThrownExceptions(NodeList<ReferenceType> var1);

    public NodeList<ReferenceType> getThrownExceptions();

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public ReferenceType getThrownException(int i) {
        return (ReferenceType)this.getThrownExceptions().get(i);
    }

    default public N addThrownException(ReferenceType throwType) {
        this.getThrownExceptions().add(throwType);
        return (N)((Node)((Object)this));
    }

    default public N addThrownException(Class<? extends Throwable> clazz) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addThrownException(StaticJavaParser.parseClassOrInterfaceType(clazz.getSimpleName()));
    }

    default public boolean isThrown(Class<? extends Throwable> clazz) {
        return this.isThrown(clazz.getSimpleName());
    }

    default public boolean isThrown(String throwableName) {
        return this.getThrownExceptions().stream().anyMatch(t -> t.toString().equals(throwableName));
    }
}

