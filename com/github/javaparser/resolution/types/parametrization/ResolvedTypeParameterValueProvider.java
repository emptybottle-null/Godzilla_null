/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types.parametrization;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedWildcard;
import java.util.Optional;

public interface ResolvedTypeParameterValueProvider {
    public Optional<ResolvedType> typeParamValue(ResolvedTypeParameterDeclaration var1);

    default public ResolvedType useThisTypeParametersOnTheGivenType(ResolvedType type) {
        Optional<ResolvedType> typeParam;
        ResolvedTypeParameterDeclaration typeParameter;
        if (type.isTypeVariable() && (typeParameter = type.asTypeParameter()).declaredOnType() && (typeParam = this.typeParamValue(typeParameter)).isPresent()) {
            type = typeParam.get();
        }
        if (type.isWildcard() && type.asWildcard().isBounded()) {
            if (type.asWildcard().isExtends()) {
                return ResolvedWildcard.extendsBound(this.useThisTypeParametersOnTheGivenType(type.asWildcard().getBoundedType()));
            }
            return ResolvedWildcard.superBound(this.useThisTypeParametersOnTheGivenType(type.asWildcard().getBoundedType()));
        }
        if (type.isReferenceType()) {
            type = type.asReferenceType().transformTypeParameters(this::useThisTypeParametersOnTheGivenType);
        }
        return type;
    }

    public Optional<ResolvedType> getGenericParameterByName(String var1);
}

