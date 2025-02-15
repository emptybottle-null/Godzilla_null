/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Map;

public class ResolvedArrayType
implements ResolvedType {
    private ResolvedType baseType;

    public ResolvedArrayType(ResolvedType baseType) {
        this.baseType = baseType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ResolvedArrayType that = (ResolvedArrayType)o;
        return this.baseType.equals(that.baseType);
    }

    public int hashCode() {
        return this.baseType.hashCode();
    }

    public String toString() {
        return "ResolvedArrayType{" + this.baseType + "}";
    }

    @Override
    public ResolvedArrayType asArrayType() {
        return this;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public String describe() {
        return this.baseType.describe() + "[]";
    }

    public ResolvedType getComponentType() {
        return this.baseType;
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        if (other.isArray()) {
            if (this.baseType.isPrimitive() && other.asArrayType().getComponentType().isPrimitive()) {
                return this.baseType.equals(other.asArrayType().getComponentType());
            }
            return this.baseType.isAssignableBy(other.asArrayType().getComponentType());
        }
        return other.isNull();
    }

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tpToReplace, ResolvedType replaced, Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        ResolvedType baseTypeReplaced = this.baseType.replaceTypeVariables(tpToReplace, replaced, inferredTypes);
        if (baseTypeReplaced == this.baseType) {
            return this;
        }
        return new ResolvedArrayType(baseTypeReplaced);
    }
}

