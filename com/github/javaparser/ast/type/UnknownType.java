/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.type;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.UnknownTypeMetaModel;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import java.util.Optional;
import java.util.function.Consumer;

public class UnknownType
extends Type {
    @AllFieldsConstructor
    public UnknownType() {
        this((TokenRange)null);
    }

    public UnknownType(TokenRange tokenRange) {
        super(tokenRange);
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

    @Override
    public UnknownType setAnnotations(NodeList<AnnotationExpr> annotations) {
        if (annotations.size() > 0) {
            throw new IllegalStateException("Inferred lambda types cannot be annotated.");
        }
        return (UnknownType)super.setAnnotations(annotations);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public String asString() {
        return "";
    }

    @Override
    public UnknownType clone() {
        return (UnknownType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public UnknownTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.unknownTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isUnknownType() {
        return true;
    }

    @Override
    public UnknownType asUnknownType() {
        return this;
    }

    @Override
    public void ifUnknownType(Consumer<UnknownType> action) {
        action.accept(this);
    }

    @Override
    public ResolvedType resolve() {
        return this.getSymbolResolver().toResolvedType(this, ResolvedReferenceType.class);
    }

    @Override
    public Optional<UnknownType> toUnknownType() {
        return Optional.of(this);
    }

    @Override
    public boolean isPhantom() {
        return true;
    }
}

