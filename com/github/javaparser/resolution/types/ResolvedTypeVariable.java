/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.List;
import java.util.Map;

public class ResolvedTypeVariable
implements ResolvedType {
    private ResolvedTypeParameterDeclaration typeParameter;

    public ResolvedTypeVariable(ResolvedTypeParameterDeclaration typeParameter) {
        this.typeParameter = typeParameter;
    }

    public String toString() {
        return "TypeVariable {" + this.typeParameter.toString() + "}";
    }

    public String qualifiedName() {
        return this.typeParameter.getQualifiedName();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ResolvedTypeVariable that = (ResolvedTypeVariable)o;
        if (!this.typeParameter.getName().equals(that.typeParameter.getName())) {
            return false;
        }
        if (this.typeParameter.declaredOnType() != that.typeParameter.declaredOnType()) {
            return false;
        }
        return this.typeParameter.declaredOnMethod() == that.typeParameter.declaredOnMethod();
    }

    public int hashCode() {
        return this.typeParameter.hashCode();
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tpToBeReplaced, ResolvedType replaced, Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        if (tpToBeReplaced.getName().equals(this.typeParameter.getName())) {
            inferredTypes.put(this.asTypeParameter(), replaced);
            return replaced;
        }
        return this;
    }

    @Override
    public boolean isReferenceType() {
        return false;
    }

    @Override
    public String describe() {
        return this.typeParameter.getName();
    }

    @Override
    public ResolvedTypeParameterDeclaration asTypeParameter() {
        return this.typeParameter;
    }

    @Override
    public ResolvedTypeVariable asTypeVariable() {
        return this;
    }

    @Override
    public boolean isTypeVariable() {
        return true;
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        if (other.isTypeVariable()) {
            return this.describe().equals(other.describe());
        }
        return true;
    }

    @Override
    public boolean mention(List<ResolvedTypeParameterDeclaration> typeParameters) {
        return typeParameters.contains(this.typeParameter);
    }
}

