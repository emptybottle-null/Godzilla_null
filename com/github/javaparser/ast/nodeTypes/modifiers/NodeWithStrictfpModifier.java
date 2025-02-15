/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes.modifiers;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;

public interface NodeWithStrictfpModifier<N extends Node>
extends NodeWithModifiers<N> {
    default public boolean isStrictfp() {
        return this.hasModifier(Modifier.Keyword.STRICTFP);
    }

    default public N setStrictfp(boolean set) {
        return this.setModifier(Modifier.Keyword.STRICTFP, set);
    }
}

