/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.resolution.declarations.AssociableToAST;
import com.github.javaparser.resolution.declarations.HasAccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

public interface ResolvedFieldDeclaration
extends ResolvedValueDeclaration,
HasAccessSpecifier,
AssociableToAST<FieldDeclaration> {
    public boolean isStatic();

    @Override
    default public boolean isField() {
        return true;
    }

    @Override
    default public ResolvedFieldDeclaration asField() {
        return this;
    }

    public ResolvedTypeDeclaration declaringType();
}

