/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedArrayType;
import com.github.javaparser.resolution.types.ResolvedLambdaConstraintType;
import com.github.javaparser.resolution.types.ResolvedPrimitiveType;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.resolution.types.ResolvedUnionType;
import com.github.javaparser.resolution.types.ResolvedWildcard;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ResolvedType {
    default public boolean isArray() {
        return false;
    }

    default public int arrayLevel() {
        if (this.isArray()) {
            return 1 + this.asArrayType().getComponentType().arrayLevel();
        }
        return 0;
    }

    default public boolean isPrimitive() {
        return false;
    }

    default public boolean isNull() {
        return false;
    }

    default public boolean isUnionType() {
        return false;
    }

    default public boolean isReference() {
        return this.isReferenceType() || this.isArray() || this.isTypeVariable() || this.isNull() || this.isWildcard() || this.isUnionType();
    }

    default public boolean isConstraint() {
        return false;
    }

    default public boolean isReferenceType() {
        return false;
    }

    default public boolean isVoid() {
        return false;
    }

    default public boolean isTypeVariable() {
        return false;
    }

    default public boolean isWildcard() {
        return false;
    }

    default public boolean isInferenceVariable() {
        return false;
    }

    default public ResolvedArrayType asArrayType() {
        throw new UnsupportedOperationException(String.format("%s is not an Array", this));
    }

    default public ResolvedReferenceType asReferenceType() {
        throw new UnsupportedOperationException(String.format("%s is not a Reference Type", this));
    }

    default public ResolvedTypeParameterDeclaration asTypeParameter() {
        throw new UnsupportedOperationException(String.format("%s is not a Type parameter", this));
    }

    default public ResolvedTypeVariable asTypeVariable() {
        throw new UnsupportedOperationException(String.format("%s is not a Type variable", this));
    }

    default public ResolvedPrimitiveType asPrimitive() {
        throw new UnsupportedOperationException(String.format("%s is not a Primitive type", this));
    }

    default public ResolvedWildcard asWildcard() {
        throw new UnsupportedOperationException(String.format("%s is not a Wildcard", this));
    }

    default public ResolvedLambdaConstraintType asConstraintType() {
        throw new UnsupportedOperationException(String.format("%s is not a constraint type", this));
    }

    default public ResolvedUnionType asUnionType() {
        throw new UnsupportedOperationException(String.format("%s is not a union type", this));
    }

    public String describe();

    default public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tp, ResolvedType replaced, Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        return this;
    }

    default public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tp, ResolvedType replaced) {
        return this.replaceTypeVariables(tp, replaced, new HashMap<ResolvedTypeParameterDeclaration, ResolvedType>());
    }

    default public boolean mention(List<ResolvedTypeParameterDeclaration> typeParameters) {
        throw new UnsupportedOperationException(this.getClass().getCanonicalName());
    }

    public boolean isAssignableBy(ResolvedType var1);

    default public boolean isNumericType() {
        return Arrays.stream(ResolvedPrimitiveType.getNumericPrimitiveTypes()).anyMatch(rpt -> rpt.isAssignableBy(this));
    }
}

