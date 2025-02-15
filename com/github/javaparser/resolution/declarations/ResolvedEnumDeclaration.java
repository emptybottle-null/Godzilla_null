/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.HasAccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedEnumConstantDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import java.util.List;

public interface ResolvedEnumDeclaration
extends ResolvedReferenceTypeDeclaration,
HasAccessSpecifier {
    @Override
    default public boolean isEnum() {
        return true;
    }

    @Override
    default public ResolvedEnumDeclaration asEnum() {
        return this;
    }

    public List<ResolvedEnumConstantDeclaration> getEnumConstants();

    default public boolean hasEnumConstant(String name) {
        return this.getEnumConstants().stream().anyMatch(c -> c.getName().equals(name));
    }

    default public ResolvedEnumConstantDeclaration getEnumConstant(String name) {
        return this.getEnumConstants().stream().filter(c -> c.getName().equals(name)).findFirst().orElseThrow(() -> new IllegalArgumentException("No constant named " + name));
    }
}

