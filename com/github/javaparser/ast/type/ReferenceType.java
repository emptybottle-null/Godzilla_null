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
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ReferenceTypeMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class ReferenceType
extends Type {
    public ReferenceType() {
        this(null, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public ReferenceType(NodeList<AnnotationExpr> annotations) {
        this(null, annotations);
    }

    public ReferenceType(TokenRange tokenRange, NodeList<AnnotationExpr> annotations) {
        super(tokenRange, annotations);
        this.customInitialization();
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public ReferenceType clone() {
        return (ReferenceType)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ReferenceTypeMetaModel getMetaModel() {
        return JavaParserMetaModel.referenceTypeMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isReferenceType() {
        return true;
    }

    @Override
    public ReferenceType asReferenceType() {
        return this;
    }

    @Override
    public void ifReferenceType(Consumer<ReferenceType> action) {
        action.accept(this);
    }

    @Override
    public Optional<ReferenceType> toReferenceType() {
        return Optional.of(this);
    }

    @Override
    public abstract String toDescriptor();
}

