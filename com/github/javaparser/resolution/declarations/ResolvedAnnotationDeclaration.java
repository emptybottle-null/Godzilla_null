/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.resolution.declarations.AssociableToAST;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationMemberDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import java.util.List;

public interface ResolvedAnnotationDeclaration
extends ResolvedReferenceTypeDeclaration,
AssociableToAST<AnnotationDeclaration> {
    public List<ResolvedAnnotationMemberDeclaration> getAnnotationMembers();
}

