/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public interface NodeWithExtends<N extends Node> {
    public NodeList<ClassOrInterfaceType> getExtendedTypes();

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public ClassOrInterfaceType getExtendedTypes(int i) {
        return (ClassOrInterfaceType)this.getExtendedTypes().get(i);
    }

    public N setExtendedTypes(NodeList<ClassOrInterfaceType> var1);

    default public N setExtendedType(int i, ClassOrInterfaceType extend) {
        this.getExtendedTypes().set(i, extend);
        return (N)((Node)((Object)this));
    }

    default public N addExtendedType(ClassOrInterfaceType extend) {
        this.getExtendedTypes().add(extend);
        return (N)((Node)((Object)this));
    }

    @Deprecated
    default public N addExtends(Class<?> clazz) {
        return this.addExtendedType(clazz);
    }

    @Deprecated
    default public N addExtends(String name) {
        return this.addExtendedType(name);
    }

    default public N addExtendedType(Class<?> clazz) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addExtendedType(clazz.getSimpleName());
    }

    default public N addExtendedType(String name) {
        this.getExtendedTypes().add(StaticJavaParser.parseClassOrInterfaceType(name));
        return (N)((Node)((Object)this));
    }
}

