/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;

public interface ResolvedValueDeclaration
extends ResolvedDeclaration {
    public ResolvedType getType();
}

