/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public interface NodeWithImplements<N extends Node> {
    public NodeList<ClassOrInterfaceType> getImplementedTypes();

    default public ClassOrInterfaceType getImplementedTypes(int i) {
        return (ClassOrInterfaceType)this.getImplementedTypes().get(i);
    }

    public N setImplementedTypes(NodeList<ClassOrInterfaceType> var1);

    public void tryAddImportToParentCompilationUnit(Class<?> var1);

    default public N setImplementedType(int i, ClassOrInterfaceType implement) {
        this.getImplementedTypes().set(i, implement);
        return (N)((Node)((Object)this));
    }

    default public N addImplementedType(ClassOrInterfaceType implement) {
        this.getImplementedTypes().add(implement);
        return (N)((Node)((Object)this));
    }

    default public N addImplements(String name) {
        return this.addImplementedType(name);
    }

    default public N addImplements(Class<?> clazz) {
        return this.addImplementedType(clazz);
    }

    default public N addImplementedType(String name) {
        this.getImplementedTypes().add(StaticJavaParser.parseClassOrInterfaceType(name));
        return (N)((Node)((Object)this));
    }

    default public N addImplementedType(Class<?> clazz) {
        this.tryAddImportToParentCompilationUnit(clazz);
        return this.addImplementedType(clazz.getSimpleName());
    }
}

