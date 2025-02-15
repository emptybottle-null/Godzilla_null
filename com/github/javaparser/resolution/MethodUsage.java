/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution;

import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.parametrization.ResolvedTypeParametersMap;
import com.github.javaparser.resolution.types.parametrization.ResolvedTypeParametrized;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MethodUsage
implements ResolvedTypeParametrized {
    private ResolvedMethodDeclaration declaration;
    private List<ResolvedType> paramTypes = new ArrayList<ResolvedType>();
    private List<ResolvedType> exceptionTypes = new ArrayList<ResolvedType>();
    private ResolvedType returnType;
    private ResolvedTypeParametersMap typeParametersMap;

    public MethodUsage(ResolvedMethodDeclaration declaration) {
        int i;
        this.typeParametersMap = ResolvedTypeParametersMap.empty();
        this.declaration = declaration;
        for (i = 0; i < declaration.getNumberOfParams(); ++i) {
            this.paramTypes.add(declaration.getParam(i).getType());
        }
        for (i = 0; i < declaration.getNumberOfSpecifiedExceptions(); ++i) {
            this.exceptionTypes.add(declaration.getSpecifiedException(i));
        }
        this.returnType = declaration.getReturnType();
    }

    public MethodUsage(ResolvedMethodDeclaration declaration, List<ResolvedType> paramTypes, ResolvedType returnType) {
        this(declaration, paramTypes, returnType, declaration.getSpecifiedExceptions(), ResolvedTypeParametersMap.empty());
    }

    public MethodUsage(ResolvedMethodDeclaration declaration, List<ResolvedType> paramTypes, ResolvedType returnType, List<ResolvedType> exceptionTypes) {
        this(declaration, paramTypes, returnType, exceptionTypes, ResolvedTypeParametersMap.empty());
    }

    private MethodUsage(ResolvedMethodDeclaration declaration, List<ResolvedType> paramTypes, ResolvedType returnType, List<ResolvedType> exceptionTypes, ResolvedTypeParametersMap typeParametersMap) {
        this.declaration = declaration;
        this.paramTypes = paramTypes;
        this.returnType = returnType;
        this.exceptionTypes = exceptionTypes;
        this.typeParametersMap = typeParametersMap;
    }

    public String toString() {
        return "MethodUsage{declaration=" + this.declaration + ", paramTypes=" + this.paramTypes + '}';
    }

    public ResolvedMethodDeclaration getDeclaration() {
        return this.declaration;
    }

    public String getName() {
        return this.declaration.getName();
    }

    public ResolvedReferenceTypeDeclaration declaringType() {
        return this.declaration.declaringType();
    }

    public ResolvedType returnType() {
        return this.returnType;
    }

    public List<ResolvedType> getParamTypes() {
        return this.paramTypes;
    }

    public MethodUsage replaceParamType(int i, ResolvedType replaced) {
        if (i < 0 || i >= this.getNoParams()) {
            throw new IllegalArgumentException();
        }
        if (this.paramTypes.get(i) == replaced) {
            return this;
        }
        LinkedList<ResolvedType> newParams = new LinkedList<ResolvedType>(this.paramTypes);
        newParams.set(i, replaced);
        return new MethodUsage(this.declaration, newParams, this.returnType, this.exceptionTypes, this.typeParametersMap);
    }

    public MethodUsage replaceExceptionType(int i, ResolvedType replaced) {
        if (i < 0 || i >= this.exceptionTypes.size()) {
            throw new IllegalArgumentException();
        }
        if (this.exceptionTypes.get(i) == replaced) {
            return this;
        }
        LinkedList<ResolvedType> newTypes = new LinkedList<ResolvedType>(this.exceptionTypes);
        newTypes.set(i, replaced);
        return new MethodUsage(this.declaration, this.paramTypes, this.returnType, newTypes, this.typeParametersMap);
    }

    public MethodUsage replaceReturnType(ResolvedType returnType) {
        if (returnType == this.returnType) {
            return this;
        }
        return new MethodUsage(this.declaration, this.paramTypes, returnType, this.exceptionTypes, this.typeParametersMap);
    }

    public int getNoParams() {
        return this.paramTypes.size();
    }

    public ResolvedType getParamType(int i) {
        return this.paramTypes.get(i);
    }

    public MethodUsage replaceTypeParameter(ResolvedTypeParameterDeclaration typeParameter, ResolvedType type) {
        int i;
        if (type == null) {
            throw new IllegalArgumentException();
        }
        MethodUsage res = new MethodUsage(this.declaration, this.paramTypes, this.returnType, this.exceptionTypes, this.typeParametersMap.toBuilder().setValue(typeParameter, type).build());
        HashMap<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes = new HashMap<ResolvedTypeParameterDeclaration, ResolvedType>();
        for (i = 0; i < this.paramTypes.size(); ++i) {
            ResolvedType originalParamType = this.paramTypes.get(i);
            ResolvedType newParamType = originalParamType.replaceTypeVariables(typeParameter, type, inferredTypes);
            res = res.replaceParamType(i, newParamType);
        }
        for (i = 0; i < this.exceptionTypes.size(); ++i) {
            ResolvedType originalType = this.exceptionTypes.get(i);
            ResolvedType newType = originalType.replaceTypeVariables(typeParameter, type, inferredTypes);
            res = res.replaceExceptionType(i, newType);
        }
        ResolvedType oldReturnType = res.returnType;
        ResolvedType newReturnType = oldReturnType.replaceTypeVariables(typeParameter, type, inferredTypes);
        res = res.replaceReturnType(newReturnType);
        return res;
    }

    @Override
    public ResolvedTypeParametersMap typeParametersMap() {
        return this.typeParametersMap;
    }

    public String getQualifiedSignature() {
        return this.getDeclaration().declaringType().getQualifiedName() + "." + this.getSignature();
    }

    public String getSignature() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append("(");
        for (int i = 0; i < this.getNoParams(); ++i) {
            ResolvedType type;
            if (i != 0) {
                sb.append(", ");
            }
            if ((type = this.getParamType(i)).isArray() && this.getDeclaration().getParam(i).isVariadic()) {
                sb.append(type.asArrayType().getComponentType().describe()).append("...");
                continue;
            }
            sb.append(type.describe());
        }
        sb.append(")");
        return sb.toString();
    }

    public List<ResolvedType> exceptionTypes() {
        return this.exceptionTypes;
    }
}

