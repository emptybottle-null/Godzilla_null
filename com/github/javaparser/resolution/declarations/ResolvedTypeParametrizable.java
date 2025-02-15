/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import java.util.List;
import java.util.Optional;

public interface ResolvedTypeParametrizable {
    public List<ResolvedTypeParameterDeclaration> getTypeParameters();

    public Optional<ResolvedTypeParameterDeclaration> findTypeParameter(String var1);

    default public boolean isGeneric() {
        return !this.getTypeParameters().isEmpty();
    }
}

