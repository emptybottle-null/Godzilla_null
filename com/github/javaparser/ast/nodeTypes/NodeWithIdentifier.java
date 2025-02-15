/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.utils.Utils;

public interface NodeWithIdentifier<N extends Node> {
    public String getIdentifier();

    public N setIdentifier(String var1);

    default public String getId() {
        return this.getIdentifier();
    }

    default public N setId(String identifier) {
        Utils.assertNonEmpty(identifier);
        return this.setIdentifier(identifier);
    }
}

