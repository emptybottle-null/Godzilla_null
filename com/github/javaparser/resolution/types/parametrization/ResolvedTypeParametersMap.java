/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types.parametrization;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResolvedTypeParametersMap {
    private Map<String, ResolvedType> nameToValue = new HashMap<String, ResolvedType>();
    private Map<String, ResolvedTypeParameterDeclaration> nameToDeclaration;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResolvedTypeParametersMap)) {
            return false;
        }
        ResolvedTypeParametersMap that = (ResolvedTypeParametersMap)o;
        return this.nameToValue.equals(that.nameToValue) && this.nameToDeclaration.equals(that.nameToDeclaration);
    }

    public int hashCode() {
        return this.nameToValue.hashCode();
    }

    public String toString() {
        return "TypeParametersMap{nameToValue=" + this.nameToValue + '}';
    }

    public static ResolvedTypeParametersMap empty() {
        return new Builder().build();
    }

    private ResolvedTypeParametersMap(Map<String, ResolvedType> nameToValue, Map<String, ResolvedTypeParameterDeclaration> nameToDeclaration) {
        this.nameToValue.putAll(nameToValue);
        this.nameToDeclaration = new HashMap<String, ResolvedTypeParameterDeclaration>();
        this.nameToDeclaration.putAll(nameToDeclaration);
    }

    public ResolvedType getValue(ResolvedTypeParameterDeclaration typeParameter) {
        String qualifiedName = typeParameter.getQualifiedName();
        if (this.nameToValue.containsKey(qualifiedName)) {
            return this.nameToValue.get(qualifiedName);
        }
        return new ResolvedTypeVariable(typeParameter);
    }

    public Optional<ResolvedType> getValueBySignature(String signature) {
        if (this.nameToValue.containsKey(signature)) {
            return Optional.of(this.nameToValue.get(signature));
        }
        return Optional.empty();
    }

    public List<String> getNames() {
        return new ArrayList<String>(this.nameToValue.keySet());
    }

    public List<ResolvedType> getTypes() {
        return new ArrayList<ResolvedType>(this.nameToValue.values());
    }

    public Builder toBuilder() {
        return new Builder(this.nameToValue, this.nameToDeclaration);
    }

    public boolean isEmpty() {
        return this.nameToValue.isEmpty();
    }

    public ResolvedType replaceAll(ResolvedType type) {
        HashMap<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes = new HashMap<ResolvedTypeParameterDeclaration, ResolvedType>();
        for (ResolvedTypeParameterDeclaration typeParameterDeclaration : this.nameToDeclaration.values()) {
            type = type.replaceTypeVariables(typeParameterDeclaration, this.getValue(typeParameterDeclaration), inferredTypes);
        }
        return type;
    }

    public static class Builder {
        private Map<String, ResolvedType> nameToValue = new HashMap<String, ResolvedType>();
        private Map<String, ResolvedTypeParameterDeclaration> nameToDeclaration;

        public Builder() {
            this.nameToDeclaration = new HashMap<String, ResolvedTypeParameterDeclaration>();
        }

        private Builder(Map<String, ResolvedType> nameToValue, Map<String, ResolvedTypeParameterDeclaration> nameToDeclaration) {
            this.nameToValue.putAll(nameToValue);
            this.nameToDeclaration = new HashMap<String, ResolvedTypeParameterDeclaration>();
            this.nameToDeclaration.putAll(nameToDeclaration);
        }

        public ResolvedTypeParametersMap build() {
            return new ResolvedTypeParametersMap(this.nameToValue, this.nameToDeclaration);
        }

        public Builder setValue(ResolvedTypeParameterDeclaration typeParameter, ResolvedType value) {
            String qualifiedName = typeParameter.getQualifiedName();
            this.nameToValue.put(qualifiedName, value);
            this.nameToDeclaration.put(qualifiedName, typeParameter);
            return this;
        }
    }
}

