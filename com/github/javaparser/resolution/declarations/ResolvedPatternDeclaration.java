/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

public interface ResolvedPatternDeclaration
extends ResolvedValueDeclaration {
    @Override
    default public boolean isPattern() {
        return true;
    }

    @Override
    default public ResolvedPatternDeclaration asPattern() {
        return this;
    }

    @Override
    default public boolean hasName() {
        return true;
    }

    default public String describeType() {
        return this.getType().describe();
    }
}

