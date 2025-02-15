/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.Node;
import com.github.javaparser.resolution.declarations.AssociableToAST;
import com.github.javaparser.resolution.declarations.HasAccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParametrizable;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import java.util.List;
import java.util.Optional;

public interface ResolvedClassDeclaration
extends ResolvedReferenceTypeDeclaration,
ResolvedTypeParametrizable,
HasAccessSpecifier,
AssociableToAST<Node> {
    @Override
    default public boolean isClass() {
        return true;
    }

    public Optional<ResolvedReferenceType> getSuperClass();

    public List<ResolvedReferenceType> getInterfaces();

    public List<ResolvedReferenceType> getAllSuperClasses();

    public List<ResolvedReferenceType> getAllInterfaces();

    @Override
    public List<ResolvedConstructorDeclaration> getConstructors();
}

