/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.resolution.declarations.AssociableToAST;
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;

public interface ResolvedConstructorDeclaration
extends ResolvedMethodLikeDeclaration,
AssociableToAST<ConstructorDeclaration> {
    @Override
    public ResolvedReferenceTypeDeclaration declaringType();
}

