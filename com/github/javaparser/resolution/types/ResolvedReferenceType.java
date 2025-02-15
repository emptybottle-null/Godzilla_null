/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedPrimitiveType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedTypeTransformer;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.resolution.types.ResolvedWildcard;
import com.github.javaparser.resolution.types.parametrization.ResolvedTypeParameterValueProvider;
import com.github.javaparser.resolution.types.parametrization.ResolvedTypeParametersMap;
import com.github.javaparser.resolution.types.parametrization.ResolvedTypeParametrized;
import com.github.javaparser.utils.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ResolvedReferenceType
implements ResolvedType,
ResolvedTypeParametrized,
ResolvedTypeParameterValueProvider {
    protected ResolvedReferenceTypeDeclaration typeDeclaration;
    protected ResolvedTypeParametersMap typeParametersMap;

    public ResolvedReferenceType(ResolvedReferenceTypeDeclaration typeDeclaration) {
        this(typeDeclaration, ResolvedReferenceType.deriveParams(typeDeclaration));
    }

    public ResolvedReferenceType(ResolvedReferenceTypeDeclaration typeDeclaration, List<ResolvedType> typeArguments) {
        if (typeDeclaration == null) {
            throw new IllegalArgumentException("TypeDeclaration is not expected to be null");
        }
        if (typeDeclaration.isTypeParameter()) {
            throw new IllegalArgumentException("You should use only Classes, Interfaces and enums");
        }
        if (typeArguments.size() > 0 && typeArguments.size() != typeDeclaration.getTypeParameters().size()) {
            throw new IllegalArgumentException(String.format("expected either zero type arguments or has many as defined in the declaration (%d). Found %d", typeDeclaration.getTypeParameters().size(), typeArguments.size()));
        }
        ResolvedTypeParametersMap.Builder typeParametersMapBuilder = new ResolvedTypeParametersMap.Builder();
        for (int i = 0; i < typeArguments.size(); ++i) {
            typeParametersMapBuilder.setValue(typeDeclaration.getTypeParameters().get(i), typeArguments.get(i));
        }
        this.typeParametersMap = typeParametersMapBuilder.build();
        this.typeDeclaration = typeDeclaration;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ResolvedReferenceType that = (ResolvedReferenceType)o;
        if (!this.typeDeclaration.equals(that.typeDeclaration)) {
            return false;
        }
        return this.typeParametersMap.equals(that.typeParametersMap);
    }

    public int hashCode() {
        int result = this.typeDeclaration.hashCode();
        result = 31 * result + this.typeParametersMap.hashCode();
        return result;
    }

    public String toString() {
        return "ReferenceType{" + this.getQualifiedName() + ", typeParametersMap=" + this.typeParametersMap + '}';
    }

    @Override
    public final boolean isReferenceType() {
        return true;
    }

    @Override
    public ResolvedReferenceType asReferenceType() {
        return this;
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();
        if (this.hasName()) {
            sb.append(this.typeDeclaration.getQualifiedName());
        } else {
            sb.append("<anonymous class>");
        }
        if (!this.typeParametersMap().isEmpty()) {
            sb.append("<");
            sb.append(String.join((CharSequence)", ", this.typeDeclaration.getTypeParameters().stream().map(tp -> this.typeParametersMap().getValue((ResolvedTypeParameterDeclaration)tp).describe()).collect(Collectors.toList())));
            sb.append(">");
        }
        return sb.toString();
    }

    public abstract ResolvedType transformTypeParameters(ResolvedTypeTransformer var1);

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tpToReplace, ResolvedType replaced, Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        if (replaced == null) {
            throw new IllegalArgumentException();
        }
        ResolvedReferenceType result = this;
        int i = 0;
        for (ResolvedType tp : this.typeParametersValues()) {
            ResolvedType transformedTp = tp.replaceTypeVariables(tpToReplace, replaced, inferredTypes);
            if (tp.isTypeVariable() && tp.asTypeVariable().describe().equals(tpToReplace.getName())) {
                inferredTypes.put(tp.asTypeParameter(), replaced);
            }
            List<ResolvedType> typeParametersCorrected = result.asReferenceType().typeParametersValues();
            typeParametersCorrected.set(i, transformedTp);
            result = this.create(this.typeDeclaration, typeParametersCorrected);
            ++i;
        }
        List<ResolvedType> values = result.typeParametersValues();
        if (values.contains(tpToReplace)) {
            int index = values.indexOf(tpToReplace);
            values.set(index, replaced);
            if (result.getTypeDeclaration().isPresent()) {
                return this.create(result.getTypeDeclaration().get(), values);
            }
        }
        return result;
    }

    @Override
    public abstract boolean isAssignableBy(ResolvedType var1);

    public abstract List<ResolvedReferenceType> getAllAncestors();

    public abstract List<ResolvedReferenceType> getDirectAncestors();

    public final List<ResolvedReferenceType> getAllInterfacesAncestors() {
        return this.getAllAncestors().stream().filter(it -> it.getTypeDeclaration().isPresent()).filter(it -> it.getTypeDeclaration().get().isInterface()).collect(Collectors.toList());
    }

    public final List<ResolvedReferenceType> getAllClassesAncestors() {
        return this.getAllAncestors().stream().filter(it -> it.getTypeDeclaration().isPresent()).filter(it -> it.getTypeDeclaration().get().isClass()).collect(Collectors.toList());
    }

    @Override
    public Optional<ResolvedType> getGenericParameterByName(String name) {
        for (ResolvedTypeParameterDeclaration tp : this.typeDeclaration.getTypeParameters()) {
            if (!tp.getName().equals(name)) continue;
            return Optional.of(this.typeParametersMap().getValue(tp));
        }
        return Optional.empty();
    }

    public List<ResolvedType> typeParametersValues() {
        return this.typeParametersMap.isEmpty() ? Collections.emptyList() : this.typeDeclaration.getTypeParameters().stream().map(tp -> this.typeParametersMap.getValue((ResolvedTypeParameterDeclaration)tp)).collect(Collectors.toList());
    }

    public List<Pair<ResolvedTypeParameterDeclaration, ResolvedType>> getTypeParametersMap() {
        ArrayList<Pair<ResolvedTypeParameterDeclaration, ResolvedType>> typeParametersMap = new ArrayList<Pair<ResolvedTypeParameterDeclaration, ResolvedType>>();
        if (!this.isRawType()) {
            for (int i = 0; i < this.typeDeclaration.getTypeParameters().size(); ++i) {
                typeParametersMap.add(new Pair<ResolvedTypeParameterDeclaration, ResolvedType>(this.typeDeclaration.getTypeParameters().get(i), this.typeParametersValues().get(i)));
            }
        }
        return typeParametersMap;
    }

    @Override
    public ResolvedTypeParametersMap typeParametersMap() {
        return this.typeParametersMap;
    }

    public final Optional<ResolvedReferenceTypeDeclaration> getTypeDeclaration() {
        return Optional.of(this.typeDeclaration);
    }

    public Optional<ResolvedType> getFieldType(String name) {
        if (!this.typeDeclaration.hasField(name)) {
            return Optional.empty();
        }
        ResolvedType type = this.typeDeclaration.getField(name).getType();
        type = this.useThisTypeParametersOnTheGivenType(type);
        return Optional.of(type);
    }

    public boolean hasName() {
        return this.typeDeclaration.hasName();
    }

    public String getQualifiedName() {
        return this.typeDeclaration.getQualifiedName();
    }

    public String getId() {
        return this.typeDeclaration.getId();
    }

    public abstract Set<MethodUsage> getDeclaredMethods();

    public abstract Set<ResolvedFieldDeclaration> getDeclaredFields();

    public boolean isRawType() {
        if (!this.typeDeclaration.getTypeParameters().isEmpty()) {
            if (this.typeParametersMap().isEmpty()) {
                return true;
            }
            for (String name : this.typeParametersMap().getNames()) {
                Optional<ResolvedType> value = this.typeParametersMap().getValueBySignature(name);
                if (value.isPresent() && value.get().isTypeVariable() && value.get().asTypeVariable().qualifiedName().equals(name)) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public Optional<ResolvedType> typeParamValue(ResolvedTypeParameterDeclaration typeParameterDeclaration) {
        if (typeParameterDeclaration.declaredOnMethod()) {
            throw new IllegalArgumentException();
        }
        if (!this.getTypeDeclaration().isPresent()) {
            return Optional.empty();
        }
        String typeId = this.getTypeDeclaration().get().getId();
        if (typeId.equals(typeParameterDeclaration.getContainerId())) {
            return Optional.of(this.typeParametersMap().getValue(typeParameterDeclaration));
        }
        for (ResolvedReferenceType ancestor : this.getAllAncestors()) {
            if (!ancestor.getId().equals(typeParameterDeclaration.getContainerId())) continue;
            return Optional.of(ancestor.typeParametersMap().getValue(typeParameterDeclaration));
        }
        return Optional.empty();
    }

    public abstract ResolvedType toRawType();

    public List<ResolvedMethodDeclaration> getAllMethods() {
        if (!this.getTypeDeclaration().isPresent()) {
            return new ArrayList<ResolvedMethodDeclaration>();
        }
        LinkedList<ResolvedMethodDeclaration> allMethods = new LinkedList<ResolvedMethodDeclaration>(this.getTypeDeclaration().get().getDeclaredMethods());
        this.getDirectAncestors().forEach(a -> allMethods.addAll(a.getAllMethods()));
        return allMethods;
    }

    public List<ResolvedFieldDeclaration> getAllFieldsVisibleToInheritors() {
        LinkedList<ResolvedFieldDeclaration> res = new LinkedList<ResolvedFieldDeclaration>(this.getDeclaredFields().stream().filter(f -> f.accessSpecifier() != AccessSpecifier.PRIVATE).collect(Collectors.toList()));
        this.getDirectAncestors().forEach(a -> res.addAll(a.getAllFieldsVisibleToInheritors()));
        return res;
    }

    public List<ResolvedMethodDeclaration> getAllMethodsVisibleToInheritors() {
        return this.getAllMethods().stream().filter(m -> m.accessSpecifier() != AccessSpecifier.PRIVATE).collect(Collectors.toList());
    }

    protected abstract ResolvedReferenceType create(ResolvedReferenceTypeDeclaration var1, List<ResolvedType> var2);

    protected ResolvedReferenceType create(ResolvedReferenceTypeDeclaration typeDeclaration, ResolvedTypeParametersMap typeParametersMap) {
        return this.create(typeDeclaration, typeDeclaration.getTypeParameters().stream().map(typeParametersMap::getValue).collect(Collectors.toList()));
    }

    protected abstract ResolvedReferenceType create(ResolvedReferenceTypeDeclaration var1);

    protected boolean isCorrespondingBoxingType(String typeName) {
        ResolvedPrimitiveType resolvedPrimitiveType = (ResolvedPrimitiveType)ResolvedPrimitiveType.byName(typeName);
        return this.getQualifiedName().equals(resolvedPrimitiveType.getBoxTypeQName());
    }

    protected boolean compareConsideringTypeParameters(ResolvedReferenceType other) {
        if (other.equals(this)) {
            return true;
        }
        if (this.getQualifiedName().equals(other.getQualifiedName())) {
            if (this.isRawType() || other.isRawType()) {
                return true;
            }
            List<ResolvedType> typeParametersValues = this.typeParametersValues();
            if (typeParametersValues.size() != other.typeParametersValues().size()) {
                throw new IllegalStateException();
            }
            for (int i = 0; i < typeParametersValues.size(); ++i) {
                ResolvedType otherParam;
                ResolvedType thisParam = typeParametersValues.get(i);
                if (thisParam.equals(otherParam = other.typeParametersValues().get(i))) continue;
                if (thisParam instanceof ResolvedWildcard) {
                    ResolvedWildcard thisParamAsWildcard = (ResolvedWildcard)thisParam;
                    if (thisParamAsWildcard.isSuper() && otherParam.isAssignableBy(thisParamAsWildcard.getBoundedType()) || thisParamAsWildcard.isExtends() && thisParamAsWildcard.getBoundedType().isAssignableBy(otherParam) || !thisParamAsWildcard.isBounded()) continue;
                    return false;
                }
                if (thisParam instanceof ResolvedTypeVariable && otherParam instanceof ResolvedTypeVariable) {
                    List thisBounds = thisParam.asTypeVariable().asTypeParameter().getBounds().stream().map(ResolvedTypeParameterDeclaration.Bound::getType).collect(Collectors.toList());
                    List otherBounds = otherParam.asTypeVariable().asTypeParameter().getBounds().stream().map(ResolvedTypeParameterDeclaration.Bound::getType).collect(Collectors.toList());
                    return thisBounds.size() == otherBounds.size() && otherBounds.containsAll(thisBounds);
                }
                if (!(thisParam instanceof ResolvedTypeVariable) && otherParam instanceof ResolvedTypeVariable) {
                    return this.compareConsideringVariableTypeParameters(thisParam, (ResolvedTypeVariable)otherParam);
                }
                if (thisParam instanceof ResolvedTypeVariable && !(otherParam instanceof ResolvedTypeVariable)) {
                    return this.compareConsideringVariableTypeParameters(otherParam, (ResolvedTypeVariable)thisParam);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean compareConsideringVariableTypeParameters(ResolvedType referenceType, ResolvedTypeVariable typeVariable) {
        List<ResolvedTypeParameterDeclaration.Bound> bounds = typeVariable.asTypeVariable().asTypeParameter().getBounds();
        if (bounds.size() == 1) {
            ResolvedType boundType = bounds.get(0).getType();
            boolean hasTypeParameter = boundType.isReferenceType() && !boundType.asReferenceType().typeParametersMap.isEmpty();
            return hasTypeParameter ? this.compareConsideringTypeParameters(boundType.asReferenceType()) : boundType.isAssignableBy(referenceType);
        }
        return false;
    }

    private static List<ResolvedType> deriveParams(ResolvedReferenceTypeDeclaration typeDeclaration) {
        if (typeDeclaration == null) {
            throw new IllegalArgumentException("TypeDeclaration is not expected to be null");
        }
        List<ResolvedTypeParameterDeclaration> typeParameters = typeDeclaration.getTypeParameters();
        if (typeParameters == null) {
            throw new RuntimeException("Type parameters are not expected to be null");
        }
        return typeParameters.stream().map(ResolvedTypeVariable::new).collect(Collectors.toList());
    }

    public abstract ResolvedReferenceType deriveTypeParameters(ResolvedTypeParametersMap var1);

    public boolean isJavaLangObject() {
        return this.isReferenceType() && this.hasName() && this.getQualifiedName().equals(Object.class.getCanonicalName());
    }

    public boolean isJavaLangEnum() {
        return this.isReferenceType() && this.hasName() && this.getQualifiedName().equals(Enum.class.getCanonicalName());
    }

    public boolean isUnboxable() {
        return Arrays.stream(ResolvedPrimitiveType.values()).anyMatch(pt -> this.getQualifiedName().equals(pt.getBoxTypeQName()));
    }

    public boolean isUnboxableTo(ResolvedPrimitiveType primitiveType) {
        return primitiveType.getBoxTypeQName().equals(this.asReferenceType().describe());
    }

    public Optional<ResolvedPrimitiveType> toUnboxedType() {
        return Arrays.stream(ResolvedPrimitiveType.values()).filter(pt -> this.asReferenceType().getQualifiedName().equals(pt.getBoxTypeQName())).findFirst();
    }
}

