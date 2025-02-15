/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.resolution.declarations.AssociableToAST;
import com.github.javaparser.resolution.declarations.HasAccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParametrizable;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import java.util.ArrayList;
import java.util.List;

public interface ResolvedInterfaceDeclaration
extends ResolvedReferenceTypeDeclaration,
ResolvedTypeParametrizable,
HasAccessSpecifier,
AssociableToAST<ClassOrInterfaceDeclaration> {
    @Override
    default public boolean isInterface() {
        return true;
    }

    public List<ResolvedReferenceType> getInterfacesExtended();

    default public List<ResolvedReferenceType> getAllInterfacesExtended() {
        ArrayList<ResolvedReferenceType> interfaces = new ArrayList<ResolvedReferenceType>();
        for (ResolvedReferenceType interfaceDeclaration : this.getInterfacesExtended()) {
            interfaces.add(interfaceDeclaration);
            interfaces.addAll(interfaceDeclaration.getAllInterfacesAncestors());
        }
        return interfaces;
    }
}

