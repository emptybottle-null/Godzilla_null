/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.types.ResolvedType;

public class ResolvedLambdaConstraintType
implements ResolvedType {
    private ResolvedType bound;

    private ResolvedLambdaConstraintType(ResolvedType bound) {
        this.bound = bound;
    }

    @Override
    public String describe() {
        return "? super " + this.bound.describe();
    }

    public ResolvedType getBound() {
        return this.bound;
    }

    @Override
    public boolean isConstraint() {
        return true;
    }

    @Override
    public ResolvedLambdaConstraintType asConstraintType() {
        return this;
    }

    public static ResolvedLambdaConstraintType bound(ResolvedType bound) {
        return new ResolvedLambdaConstraintType(bound);
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        return this.bound.isAssignableBy(other);
    }

    public String toString() {
        return "LambdaConstraintType{bound=" + this.bound + '}';
    }
}

