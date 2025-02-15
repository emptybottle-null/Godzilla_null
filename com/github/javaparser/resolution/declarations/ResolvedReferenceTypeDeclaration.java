/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParametrizable;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface ResolvedReferenceTypeDeclaration
extends ResolvedTypeDeclaration,
ResolvedTypeParametrizable {
    @Override
    default public ResolvedReferenceTypeDeclaration asReferenceType() {
        return this;
    }

    default public List<ResolvedReferenceType> getAncestors() {
        return this.getAncestors(false);
    }

    public List<ResolvedReferenceType> getAncestors(boolean var1);

    default public List<ResolvedReferenceType> getAllAncestors() {
        ArrayList<ResolvedReferenceType> ancestors = new ArrayList<ResolvedReferenceType>();
        if (!this.isJavaLangObject()) {
            for (ResolvedReferenceType ancestor : this.getAncestors()) {
                ancestors.add(ancestor);
                for (ResolvedReferenceType inheritedAncestor : ancestor.getAllAncestors()) {
                    if (ancestors.contains(inheritedAncestor)) continue;
                    ancestors.add(inheritedAncestor);
                }
            }
        }
        return ancestors;
    }

    default public ResolvedFieldDeclaration getField(String name) {
        Optional<ResolvedFieldDeclaration> field = this.getAllFields().stream().filter(f -> f.getName().equals(name)).findFirst();
        if (field.isPresent()) {
            return field.get();
        }
        throw new UnsolvedSymbolException("Field not found: " + name);
    }

    default public ResolvedFieldDeclaration getVisibleField(String name) {
        Optional<ResolvedFieldDeclaration> field = this.getVisibleFields().stream().filter(f -> f.getName().equals(name)).findFirst();
        if (field.isPresent()) {
            return field.get();
        }
        throw new IllegalArgumentException();
    }

    default public boolean hasField(String name) {
        return this.getAllFields().stream().anyMatch(f -> f.getName().equals(name));
    }

    default public boolean hasVisibleField(String name) {
        return this.getVisibleFields().stream().anyMatch(f -> f.getName().equals(name));
    }

    public List<ResolvedFieldDeclaration> getAllFields();

    default public List<ResolvedFieldDeclaration> getVisibleFields() {
        return this.getAllFields().stream().filter(f -> f.declaringType().equals(this) || f.accessSpecifier() != AccessSpecifier.PRIVATE).collect(Collectors.toList());
    }

    default public List<ResolvedFieldDeclaration> getAllNonStaticFields() {
        return this.getAllFields().stream().filter(it -> !it.isStatic()).collect(Collectors.toList());
    }

    default public List<ResolvedFieldDeclaration> getAllStaticFields() {
        return this.getAllFields().stream().filter(it -> it.isStatic()).collect(Collectors.toList());
    }

    default public List<ResolvedFieldDeclaration> getDeclaredFields() {
        return this.getAllFields().stream().filter(it -> it.declaringType().getQualifiedName().equals(this.getQualifiedName())).collect(Collectors.toList());
    }

    public Set<ResolvedMethodDeclaration> getDeclaredMethods();

    public Set<MethodUsage> getAllMethods();

    public boolean isAssignableBy(ResolvedType var1);

    default public boolean canBeAssignedTo(ResolvedReferenceTypeDeclaration other) {
        return other.isAssignableBy(this);
    }

    public boolean isAssignableBy(ResolvedReferenceTypeDeclaration var1);

    public boolean hasDirectlyAnnotation(String var1);

    default public boolean hasAnnotation(String qualifiedName) {
        if (this.hasDirectlyAnnotation(qualifiedName)) {
            return true;
        }
        return this.getAllAncestors().stream().filter(it -> it.asReferenceType().getTypeDeclaration().isPresent()).anyMatch(it -> it.asReferenceType().getTypeDeclaration().get().hasDirectlyAnnotation(qualifiedName));
    }

    public boolean isFunctionalInterface();

    @Override
    default public Optional<ResolvedTypeParameterDeclaration> findTypeParameter(String name) {
        for (ResolvedTypeParameterDeclaration tp : this.getTypeParameters()) {
            if (!tp.getName().equals(name)) continue;
            return Optional.of(tp);
        }
        if (this.containerType().isPresent()) {
            return this.containerType().get().findTypeParameter(name);
        }
        return Optional.empty();
    }

    public List<ResolvedConstructorDeclaration> getConstructors();

    default public boolean isJavaLangObject() {
        return this.isClass() && !this.isAnonymousClass() && this.hasName() && this.getQualifiedName().equals(Object.class.getCanonicalName());
    }

    default public boolean isJavaLangEnum() {
        return this.isEnum() && this.getQualifiedName().equals(Enum.class.getCanonicalName());
    }
}

