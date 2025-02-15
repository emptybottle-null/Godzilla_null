/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes.modifiers;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;

public interface NodeWithFinalModifier<N extends Node>
extends NodeWithModifiers<N> {
    default public boolean isFinal() {
        return this.hasModifier(Modifier.Keyword.FINAL);
    }

    default public N setFinal(boolean set) {
        return this.setModifier(Modifier.Keyword.FINAL, set);
    }
}

