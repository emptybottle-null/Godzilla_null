/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ArrayCreationLevelMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;

public class ArrayCreationLevel
extends Node
implements NodeWithAnnotations<ArrayCreationLevel> {
    @OptionalProperty
    private Expression dimension;
    private NodeList<AnnotationExpr> annotations = new NodeList();

    public ArrayCreationLevel() {
        this(null, null, new NodeList<AnnotationExpr>());
    }

    public ArrayCreationLevel(int dimension) {
        this(null, new IntegerLiteralExpr("" + dimension), new NodeList<AnnotationExpr>());
    }

    public ArrayCreationLevel(Expression dimension) {
        this(null, dimension, new NodeList<AnnotationExpr>());
    }

    @AllFieldsConstructor
    public ArrayCreationLevel(Expression dimension, NodeList<AnnotationExpr> annotations) {
        this(null, dimension, annotations);
    }

    public ArrayCreationLevel(TokenRange tokenRange, Expression dimension, NodeList<AnnotationExpr> annotations) {
        super(tokenRange);
        this.setDimension(dimension);
        this.setAnnotations((NodeList)annotations);
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

    public ArrayCreationLevel setDimension(Expression dimension) {
        if (dimension == this.dimension) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.DIMENSION, this.dimension, dimension);
        if (this.dimension != null) {
            this.dimension.setParentNode(null);
        }
        this.dimension = dimension;
        this.setAsParentNodeOf(dimension);
        return this;
    }

    public Optional<Expression> getDimension() {
        return Optional.ofNullable(this.dimension);
    }

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    @Override
    public ArrayCreationLevel setAnnotations(NodeList<AnnotationExpr> annotations) {
        Utils.assertNotNull(annotations);
        if (annotations == this.annotations) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
        if (this.annotations != null) {
            this.annotations.setParentNode(null);
        }
        this.annotations = annotations;
        this.setAsParentNodeOf(annotations);
        return this;
    }

    public ArrayCreationLevel removeDimension() {
        return this.setDimension(null);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.remove(i);
            return true;
        }
        if (this.dimension != null && node == this.dimension) {
            this.removeDimension();
            return true;
        }
        return super.remove(node);
    }

    @Override
    public ArrayCreationLevel clone() {
        return (ArrayCreationLevel)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ArrayCreationLevelMetaModel getMetaModel() {
        return JavaParserMetaModel.arrayCreationLevelMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.set(i, (AnnotationExpr)replacementNode);
            return true;
        }
        if (this.dimension != null && node == this.dimension) {
            this.setDimension((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }
}

