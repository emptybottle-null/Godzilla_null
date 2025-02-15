/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedEnumConstantDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedPatternDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeDeclaration;

public interface ResolvedDeclaration {
    default public boolean hasName() {
        return true;
    }

    public String getName();

    default public boolean isField() {
        return false;
    }

    default public boolean isVariable() {
        return false;
    }

    default public boolean isEnumConstant() {
        return false;
    }

    default public boolean isPattern() {
        return false;
    }

    default public boolean isParameter() {
        return false;
    }

    default public boolean isType() {
        return false;
    }

    default public boolean isMethod() {
        return false;
    }

    default public ResolvedFieldDeclaration asField() {
        throw new UnsupportedOperationException(String.format("%s is not a FieldDeclaration", this));
    }

    default public ResolvedParameterDeclaration asParameter() {
        throw new UnsupportedOperationException(String.format("%s is not a ParameterDeclaration", this));
    }

    default public ResolvedTypeDeclaration asType() {
        throw new UnsupportedOperationException(String.format("%s is not a TypeDeclaration", this));
    }

    default public ResolvedMethodDeclaration asMethod() {
        throw new UnsupportedOperationException(String.format("%s is not a MethodDeclaration", this));
    }

    default public ResolvedEnumConstantDeclaration asEnumConstant() {
        throw new UnsupportedOperationException(String.format("%s is not an EnumConstantDeclaration", this));
    }

    default public ResolvedPatternDeclaration asPattern() {
        throw new UnsupportedOperationException(String.format("%s is not a Pattern", this));
    }
}

