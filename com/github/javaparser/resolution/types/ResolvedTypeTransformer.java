/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.types.ResolvedType;

@FunctionalInterface
public interface ResolvedTypeTransformer {
    public ResolvedType transform(ResolvedType var1);
}

