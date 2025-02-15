/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.Node;
import java.util.Optional;

public interface AssociableToAST<N extends Node> {
    default public Optional<N> toAst() {
        throw new UnsupportedOperationException();
    }
}

