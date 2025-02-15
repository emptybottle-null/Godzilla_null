/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResolvedUnionType
implements ResolvedType {
    private List<ResolvedType> elements;

    public ResolvedUnionType(List<ResolvedType> elements) {
        if (elements.size() < 2) {
            throw new IllegalArgumentException("An union type should have at least two elements. This has " + elements.size());
        }
        this.elements = new LinkedList<ResolvedType>(elements);
    }

    public Optional<ResolvedReferenceType> getCommonAncestor() {
        Optional<List> reduce = this.elements.stream().map(ResolvedType::asReferenceType).map(ResolvedReferenceType::getAllAncestors).reduce((a, b) -> {
            ArrayList common = new ArrayList(a);
            common.retainAll((Collection<?>)b);
            return common;
        });
        return ((List)reduce.orElse(new ArrayList())).stream().findFirst();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ResolvedUnionType that = (ResolvedUnionType)o;
        return new HashSet<ResolvedType>(this.elements).equals(new HashSet<ResolvedType>(that.elements));
    }

    public int hashCode() {
        return new HashSet<ResolvedType>(this.elements).hashCode();
    }

    @Override
    public String describe() {
        return String.join((CharSequence)" | ", this.elements.stream().map(ResolvedType::describe).collect(Collectors.toList()));
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        return this.elements.stream().allMatch(e -> e.isAssignableBy(other));
    }

    @Override
    public boolean isUnionType() {
        return true;
    }

    @Override
    public ResolvedUnionType asUnionType() {
        return this;
    }
}

