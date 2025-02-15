/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.HasAccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParametrizable;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface ResolvedMethodLikeDeclaration
extends ResolvedDeclaration,
ResolvedTypeParametrizable,
HasAccessSpecifier {
    default public String getPackageName() {
        return this.declaringType().getPackageName();
    }

    default public String getClassName() {
        return this.declaringType().getClassName();
    }

    default public String getQualifiedName() {
        return this.declaringType().getQualifiedName() + "." + this.getName();
    }

    default public String getSignature() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append("(");
        for (int i = 0; i < this.getNumberOfParams(); ++i) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(this.getParam(i).describeType());
        }
        sb.append(")");
        return sb.toString();
    }

    default public String getQualifiedSignature() {
        return this.declaringType().getId() + "." + this.getSignature();
    }

    public ResolvedReferenceTypeDeclaration declaringType();

    public int getNumberOfParams();

    public ResolvedParameterDeclaration getParam(int var1);

    default public ResolvedParameterDeclaration getLastParam() {
        if (this.getNumberOfParams() == 0) {
            throw new UnsupportedOperationException("This method has no typeParametersValues, therefore it has no a last parameter");
        }
        return this.getParam(this.getNumberOfParams() - 1);
    }

    default public boolean hasVariadicParameter() {
        if (this.getNumberOfParams() == 0) {
            return false;
        }
        return this.getParam(this.getNumberOfParams() - 1).isVariadic();
    }

    @Override
    default public Optional<ResolvedTypeParameterDeclaration> findTypeParameter(String name) {
        for (ResolvedTypeParameterDeclaration tp : this.getTypeParameters()) {
            if (!tp.getName().equals(name)) continue;
            return Optional.of(tp);
        }
        return this.declaringType().findTypeParameter(name);
    }

    public int getNumberOfSpecifiedExceptions();

    public ResolvedType getSpecifiedException(int var1);

    default public List<ResolvedType> getSpecifiedExceptions() {
        if (this.getNumberOfSpecifiedExceptions() == 0) {
            return Collections.emptyList();
        }
        LinkedList<ResolvedType> exceptions = new LinkedList<ResolvedType>();
        for (int i = 0; i < this.getNumberOfSpecifiedExceptions(); ++i) {
            exceptions.add(this.getSpecifiedException(i));
        }
        return exceptions;
    }
}

