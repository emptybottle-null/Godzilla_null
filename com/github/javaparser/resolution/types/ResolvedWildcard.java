/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.types;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.List;
import java.util.Map;

public class ResolvedWildcard
implements ResolvedType {
    public static ResolvedWildcard UNBOUNDED = new ResolvedWildcard(null, null);
    private BoundType type;
    private ResolvedType boundedType;

    private ResolvedWildcard(BoundType type, ResolvedType boundedType) {
        if (type == null && boundedType != null) {
            throw new IllegalArgumentException();
        }
        if (type != null && boundedType == null) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        this.boundedType = boundedType;
    }

    public static ResolvedWildcard superBound(ResolvedType type) {
        return new ResolvedWildcard(BoundType.SUPER, type);
    }

    public static ResolvedWildcard extendsBound(ResolvedType type) {
        return new ResolvedWildcard(BoundType.EXTENDS, type);
    }

    public String toString() {
        return "WildcardUsage{type=" + (Object)((Object)this.type) + ", boundedType=" + this.boundedType + '}';
    }

    @Override
    public boolean isWildcard() {
        return true;
    }

    @Override
    public ResolvedWildcard asWildcard() {
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResolvedWildcard)) {
            return false;
        }
        ResolvedWildcard that = (ResolvedWildcard)o;
        if (this.boundedType != null ? !this.boundedType.equals(that.boundedType) : that.boundedType != null) {
            return false;
        }
        return this.type == that.type;
    }

    public int hashCode() {
        int result = this.type != null ? this.type.hashCode() : 0;
        result = 31 * result + (this.boundedType != null ? this.boundedType.hashCode() : 0);
        return result;
    }

    @Override
    public String describe() {
        if (this.type == null) {
            return "?";
        }
        if (this.type == BoundType.SUPER) {
            return "? super " + this.boundedType.describe();
        }
        if (this.type == BoundType.EXTENDS) {
            return "? extends " + this.boundedType.describe();
        }
        throw new UnsupportedOperationException();
    }

    public boolean isSuper() {
        return this.type == BoundType.SUPER;
    }

    public boolean isExtends() {
        return this.type == BoundType.EXTENDS;
    }

    public boolean isBounded() {
        return this.isSuper() || this.isExtends();
    }

    public ResolvedType getBoundedType() {
        if (this.boundedType == null) {
            throw new IllegalStateException();
        }
        return this.boundedType;
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        if (this.boundedType == null) {
            return false;
        }
        if (this.type == BoundType.SUPER) {
            return this.boundedType.isAssignableBy(other);
        }
        if (this.type == BoundType.EXTENDS) {
            return false;
        }
        throw new RuntimeException();
    }

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tpToReplace, ResolvedType replaced, Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        if (replaced == null) {
            throw new IllegalArgumentException();
        }
        if (this.boundedType == null) {
            return this;
        }
        ResolvedType boundedTypeReplaced = this.boundedType.replaceTypeVariables(tpToReplace, replaced, inferredTypes);
        if (boundedTypeReplaced == null) {
            throw new RuntimeException();
        }
        if (boundedTypeReplaced != this.boundedType) {
            return new ResolvedWildcard(this.type, boundedTypeReplaced);
        }
        return this;
    }

    @Override
    public boolean mention(List<ResolvedTypeParameterDeclaration> typeParameters) {
        return this.boundedType != null && this.boundedType.mention(typeParameters);
    }

    public boolean isUpperBounded() {
        return this.isSuper();
    }

    public boolean isLowerBounded() {
        return this.isExtends();
    }

    public static enum BoundType {
        SUPER,
        EXTENDS;

    }
}

