/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.resolution.declarations.AssociableToAST;
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;

public interface ResolvedMethodDeclaration
extends ResolvedMethodLikeDeclaration,
AssociableToAST<MethodDeclaration> {
    public ResolvedType getReturnType();

    public boolean isAbstract();

    public boolean isDefaultMethod();

    public boolean isStatic();
}

