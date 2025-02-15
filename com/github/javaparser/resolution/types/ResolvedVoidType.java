/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.types.ResolvedType;

public class ResolvedVoidType
implements ResolvedType {
    public static final ResolvedType INSTANCE = new ResolvedVoidType();

    private ResolvedVoidType() {
    }

    @Override
    public String describe() {
        return "void";
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isVoid() {
        return true;
    }
}

