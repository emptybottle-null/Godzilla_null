/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes.modifiers;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;

public interface NodeWithPublicModifier<N extends Node>
extends NodeWithModifiers<N> {
    default public boolean isPublic() {
        return this.hasModifier(Modifier.Keyword.PUBLIC);
    }

    default public N setPublic(boolean set) {
        return this.setModifier(Modifier.Keyword.PUBLIC, set);
    }
}

