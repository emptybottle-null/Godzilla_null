/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

public interface ResolvedEnumConstantDeclaration
extends ResolvedValueDeclaration {
    @Override
    public String getName();

    @Override
    default public boolean isEnumConstant() {
        return true;
    }

    @Override
    default public ResolvedEnumConstantDeclaration asEnumConstant() {
        return this;
    }
}

