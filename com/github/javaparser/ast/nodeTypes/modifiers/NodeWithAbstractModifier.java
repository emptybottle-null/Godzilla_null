/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes.modifiers;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;

public interface NodeWithAbstractModifier<N extends Node>
extends NodeWithModifiers<N> {
    default public boolean isAbstract() {
        return this.hasModifier(Modifier.Keyword.ABSTRACT);
    }

    default public N setAbstract(boolean set) {
        return this.setModifier(Modifier.Keyword.ABSTRACT, set);
    }
}

