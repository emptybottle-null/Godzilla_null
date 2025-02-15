/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

public interface ResolvedParameterDeclaration
extends ResolvedValueDeclaration {
    @Override
    default public boolean isParameter() {
        return true;
    }

    @Override
    default public boolean hasName() {
        return true;
    }

    @Override
    default public ResolvedParameterDeclaration asParameter() {
        return this;
    }

    public boolean isVariadic();

    default public String describeType() {
        if (this.isVariadic()) {
            return this.getType().asArrayType().getComponentType().describe() + "...";
        }
        return this.getType().describe();
    }
}

