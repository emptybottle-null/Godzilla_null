/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedClassDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedEnumDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedInterfaceDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import java.util.Optional;
import java.util.Set;

public interface ResolvedTypeDeclaration
extends ResolvedDeclaration {
    default public Set<ResolvedReferenceTypeDeclaration> internalTypes() {
        throw new UnsupportedOperationException("InternalTypes not available for " + this.getClass().getCanonicalName());
    }

    default public ResolvedReferenceTypeDeclaration getInternalType(String name) {
        Optional<ResolvedReferenceTypeDeclaration> type = this.internalTypes().stream().filter(f -> f.getName().equals(name)).findFirst();
        return type.orElseThrow(() -> new UnsolvedSymbolException("Internal type not found: " + name));
    }

    default public boolean hasInternalType(String name) {
        return this.internalTypes().stream().anyMatch(f -> f.getName().equals(name));
    }

    public Optional<ResolvedReferenceTypeDeclaration> containerType();

    default public boolean isClass() {
        return false;
    }

    default public boolean isInterface() {
        return false;
    }

    default public boolean isEnum() {
        return false;
    }

    default public boolean isTypeParameter() {
        return false;
    }

    @Override
    default public boolean isType() {
        return true;
    }

    default public boolean isAnonymousClass() {
        return false;
    }

    @Override
    default public ResolvedTypeDeclaration asType() {
        return this;
    }

    default public ResolvedClassDeclaration asClass() {
        throw new UnsupportedOperationException(String.format("%s is not a class", this));
    }

    default public ResolvedInterfaceDeclaration asInterface() {
        throw new UnsupportedOperationException(String.format("%s is not an interface", this));
    }

    default public ResolvedEnumDeclaration asEnum() {
        throw new UnsupportedOperationException(String.format("%s is not an enum", this));
    }

    default public ResolvedTypeParameterDeclaration asTypeParameter() {
        throw new UnsupportedOperationException(String.format("%s is not a type parameter", this));
    }

    default public ResolvedReferenceTypeDeclaration asReferenceType() {
        throw new UnsupportedOperationException(String.format("%s is not a reference type", this));
    }

    public String getPackageName();

    public String getClassName();

    public String getQualifiedName();

    default public String getId() {
        String qname = this.getQualifiedName();
        if (qname == null) {
            return String.format("<localClass>:%s", this.getName());
        }
        return qname;
    }
}

