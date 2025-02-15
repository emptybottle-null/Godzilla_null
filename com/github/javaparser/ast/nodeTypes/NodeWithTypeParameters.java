/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.TypeParameter;

public interface NodeWithTypeParameters<N extends Node> {
    public NodeList<TypeParameter> getTypeParameters();

    default public TypeParameter getTypeParameter(int i) {
        return (TypeParameter)this.getTypeParameters().get(i);
    }

    default public N setTypeParameter(int i, TypeParameter typeParameter) {
        this.getTypeParameters().set(i, typeParameter);
        return (N)((Node)((Object)this));
    }

    default public N addTypeParameter(TypeParameter typeParameter) {
        this.getTypeParameters().add(typeParameter);
        return (N)((Node)((Object)this));
    }

    default public N addTypeParameter(String typeParameter) {
        return this.addTypeParameter(StaticJavaParser.parseTypeParameter(typeParameter));
    }

    public N setTypeParameters(NodeList<TypeParameter> var1);

    default public boolean isGeneric() {
        return this.getTypeParameters().size() > 0;
    }
}

