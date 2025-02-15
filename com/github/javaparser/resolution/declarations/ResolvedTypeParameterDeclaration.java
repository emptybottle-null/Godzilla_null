/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParametrizable;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.List;
import java.util.Optional;

public interface ResolvedTypeParameterDeclaration
extends ResolvedTypeDeclaration {
    public static ResolvedTypeParameterDeclaration onType(final String name, final String classQName, final List<Bound> bounds) {
        return new ResolvedTypeParameterDeclaration(){

            @Override
            public String getName() {
                return name;
            }

            @Override
            public boolean declaredOnType() {
                return true;
            }

            @Override
            public boolean declaredOnMethod() {
                return false;
            }

            @Override
            public boolean declaredOnConstructor() {
                return false;
            }

            @Override
            public String getContainerQualifiedName() {
                return classQName;
            }

            @Override
            public String getContainerId() {
                return classQName;
            }

            @Override
            public ResolvedTypeParametrizable getContainer() {
                return null;
            }

            @Override
            public List<Bound> getBounds() {
                return bounds;
            }

            public String toString() {
                return "TypeParameter onType " + name;
            }

            @Override
            public Optional<ResolvedReferenceTypeDeclaration> containerType() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String getName();

    default public boolean declaredOnType() {
        return this.getContainer() instanceof ResolvedReferenceTypeDeclaration;
    }

    default public boolean declaredOnMethod() {
        return this.getContainer() instanceof ResolvedMethodDeclaration;
    }

    default public boolean declaredOnConstructor() {
        return this.getContainer() instanceof ResolvedConstructorDeclaration;
    }

    @Override
    default public String getPackageName() {
        throw new UnsupportedOperationException();
    }

    @Override
    default public String getClassName() {
        throw new UnsupportedOperationException();
    }

    @Override
    default public String getQualifiedName() {
        return String.format("%s.%s", this.getContainerId(), this.getName());
    }

    public String getContainerQualifiedName();

    public String getContainerId();

    public ResolvedTypeParametrizable getContainer();

    public List<Bound> getBounds();

    default public boolean hasLowerBound() {
        for (Bound b : this.getBounds()) {
            if (!b.isExtends()) continue;
            return true;
        }
        return false;
    }

    default public boolean hasUpperBound() {
        for (Bound b : this.getBounds()) {
            if (!b.isSuper()) continue;
            return true;
        }
        return false;
    }

    default public ResolvedType getLowerBound() {
        for (Bound b : this.getBounds()) {
            if (!b.isExtends()) continue;
            return b.getType();
        }
        throw new IllegalStateException();
    }

    default public ResolvedType getUpperBound() {
        for (Bound b : this.getBounds()) {
            if (!b.isSuper()) continue;
            return b.getType();
        }
        throw new IllegalStateException();
    }

    public static class Bound {
        private boolean extendsBound;
        private ResolvedType type;

        private Bound(boolean extendsBound, ResolvedType type) {
            this.extendsBound = extendsBound;
            this.type = type;
        }

        public static Bound extendsBound(ResolvedType type) {
            return new Bound(true, type);
        }

        public static Bound superBound(ResolvedType type) {
            return new Bound(false, type);
        }

        public ResolvedType getType() {
            return this.type;
        }

        public boolean isExtends() {
            return this.extendsBound;
        }

        public boolean isSuper() {
            return !this.isExtends();
        }

        public String toString() {
            return "Bound{extendsBound=" + this.extendsBound + ", type=" + this.type + '}';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Bound bound = (Bound)o;
            if (this.extendsBound != bound.extendsBound) {
                return false;
            }
            return this.type != null ? this.type.equals(bound.type) : bound.type == null;
        }

        public int hashCode() {
            int result = this.extendsBound ? 1 : 0;
            result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
            return result;
        }
    }
}

