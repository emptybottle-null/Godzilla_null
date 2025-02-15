/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.DoubleLiteralExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class DoubleLiteralExpr
extends LiteralStringValueExpr {
    public DoubleLiteralExpr() {
        this(null, "0");
    }

    @AllFieldsConstructor
    public DoubleLiteralExpr(String value) {
        this(null, value);
    }

    public DoubleLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
        this.customInitialization();
    }

    public DoubleLiteralExpr(double value) {
        this(null, String.valueOf(value));
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

    public double asDouble() {
        String noUnderscoreValue = this.value.replaceAll("_", "");
        return Double.parseDouble(noUnderscoreValue);
    }

    public DoubleLiteralExpr setDouble(double value) {
        this.value = String.valueOf(value);
        return this;
    }

    @Override
    public DoubleLiteralExpr clone() {
        return (DoubleLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public DoubleLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.doubleLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isDoubleLiteralExpr() {
        return true;
    }

    @Override
    public DoubleLiteralExpr asDoubleLiteralExpr() {
        return this;
    }

    @Override
    public void ifDoubleLiteralExpr(Consumer<DoubleLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<DoubleLiteralExpr> toDoubleLiteralExpr() {
        return Optional.of(this);
    }
}

