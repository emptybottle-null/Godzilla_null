/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.metamodel.WildcardTypeMetaModel;
import com.github.javaparser.resolution.types.ResolvedWildcard;
import java.util.Optional;
import java.util.function.Consumer;

public class WildcardType
extends Type
implements NodeWithAnnotations<WildcardType> {
    @OptionalProperty
    private ReferenceType extendedType;
    @OptionalProperty
    private ReferenceType superType;

    public WildcardType() {
        this(null, null, null, new NodeList<AnnotationExpr>());
    }

    public WildcardType(ReferenceType extendedType) {
        this(null, extendedType, null, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public WildcardType(ReferenceType extendedType, ReferenceType superType, NodeList<AnnotationExpr> annotations) {
        this(null, extendedType, superType, annotations);
    }

    public WildcardType(TokenRange tokenRange, ReferenceType extendedType, ReferenceType superType, NodeList<AnnotationExpr> annotations) {
        super(tokenRange, annotations);
        this.setExtendedType(extendedType);
        this.setSuperType(superType);
        this.customInitialization();
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Optional<ReferenceType> getExtendedType() {
        return Optional.ofNullable(this.extendedType);
    }

    public Optional<ReferenceType> getSuperType() {
        return Optional.ofNullable(this.superType);
    }

    public WildcardType setExtendedType(ReferenceType extendedType) {
        if (extendedType == this.extendedType) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.EXTENDED_TYPE, this.extendedType, extendedType);
        if (this.extendedType != null) {
            this.extendedType.setParentNode(null);
        }
        this.extendedType = extendedType;
        this.setAsParentNodeOf(extendedType);
        return this;
    }

    public WildcardType setSuperType(ReferenceType superType) {
        if (superType == this.superType) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.SUPER_TYPE, this.superType, superType);
        if (this.superType != null) {
            this.superType.setParentNode(null);
        }
        this.superType = superType;
        this.setAsParentNodeOf(superType);
        return this;
    }

    @Override
    public WildcardType setAnnotations(NodeList<AnnotationExpr> annotations) {
        return (WildcardType)super.setAnnotations(annotations);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.extendedType != null && node == this.extendedType) {
            this.removeExtendedType();
            return true;
        }
        if (this.superType != null && node == this.superType) {
            this.removeSuperType();
            return true;
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        StringBuilder str = new StringBuilder("?");
        this.getExtendedType().ifPresent(t -> str.append(" extends ").append(t.asString()));
        this.getSuperType().ifPresent(t -> str.append(" super ").append(t.asString()));
        return str.toString();
    }

    public WildcardType removeExtendedType() {
        return this.setExtendedType(null);
    }

    public WildcardType removeSuperType() {
        return this.setSuperType(null);
    }

    @Override
    public WildcardType clone() {
        return (WildcardType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public WildcardTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.wildcardTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.extendedType != null && node == this.extendedType) {
            this.setExtendedType((ReferenceType)replacementNode);
            return true;
        }
        if (this.superType != null && node == this.superType) {
            this.setSuperType((ReferenceType)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public WildcardType(TokenRange tokenRange, ReferenceType extendedType, ReferenceType superType) {
        super(tokenRange);
        this.setExtendedType(extendedType);
        this.setSuperType(superType);
        this.customInitialization();
    }

    @Override
    public boolean isWildcardType() {
        return true;
    }

    @Override
    public WildcardType asWildcardType() {
        return this;
    }

    @Override
    public void ifWildcardType(Consumer<WildcardType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedWildcard resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedWildcard.class);
    }

    @Override
    public Optional<WildcardType> toWildcardType() {
        return Optional.of(this);
    }
}

