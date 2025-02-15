/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResolvedIntersectionType
implements ResolvedType {
    private List<ResolvedType> elements;

    public ResolvedIntersectionType(Collection<ResolvedType> elements) {
        if (elements.size() < 2) {
            throw new IllegalArgumentException("An intersection type should have at least two elements. This has " + elements.size());
        }
        this.elements = new LinkedList<ResolvedType>(elements);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ResolvedIntersectionType that = (ResolvedIntersectionType)o;
        return new HashSet<ResolvedType>(this.elements).equals(new HashSet<ResolvedType>(that.elements));
    }

    public int hashCode() {
        return new HashSet<ResolvedType>(this.elements).hashCode();
    }

    @Override
    public String describe() {
        return String.join((CharSequence)" & ", this.elements.stream().map(ResolvedType::describe).collect(Collectors.toList()));
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        return this.elements.stream().allMatch(e -> e.isAssignableBy(other));
    }

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tp, ResolvedType replaced, Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        List<ResolvedType> elementsReplaced = this.elements.stream().map(e -> e.replaceTypeVariables(tp, replaced, inferredTypes)).collect(Collectors.toList());
        if (elementsReplaced.equals(this.elements)) {
            return this;
        }
        return new ResolvedIntersectionType(elementsReplaced);
    }
}

