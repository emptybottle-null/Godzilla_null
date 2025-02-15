/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.MarkerAnnotationExprMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class MarkerAnnotationExpr
extends AnnotationExpr {
    public MarkerAnnotationExpr() {
        this(null, new Name());
    }

    public MarkerAnnotationExpr(String name) {
        this(null, StaticJavaParser.parseName(name));
    }

    @AllFieldsConstructor
    public MarkerAnnotationExpr(Name name) {
        this(null, name);
    }

    public MarkerAnnotationExpr(TokenRange tokenRange, Name name) {
        super(tokenRange, name);
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public MarkerAnnotationExpr clone() {
        return (MarkerAnnotationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public MarkerAnnotationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.markerAnnotationExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isMarkerAnnotationExpr() {
        return true;
    }

    @Override
    public MarkerAnnotationExpr asMarkerAnnotationExpr() {
        return this;
    }

    @Override
    public void ifMarkerAnnotationExpr(Consumer<MarkerAnnotationExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<MarkerAnnotationExpr> toMarkerAnnotationExpr() {
        return Optional.of(this);
    }
}

