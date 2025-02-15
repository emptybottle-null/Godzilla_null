/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.BooleanLiteralExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class BooleanLiteralExpr
extends LiteralExpr {
    private boolean value;

    public BooleanLiteralExpr() {
        this(null, false);
    }

    @AllFieldsConstructor
    public BooleanLiteralExpr(boolean value) {
        this(null, value);
    }

    public BooleanLiteralExpr(TokenRange tokenRange, boolean value) {
        super(tokenRange);
        this.setValue(value);
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

    public boolean isValue() {
        return this.value;
    }

    public boolean getValue() {
        return this.isValue();
    }

    public BooleanLiteralExpr setValue(boolean value) {
        if (value == this.value) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VALUE, this.value, value);
        this.value = value;
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public BooleanLiteralExpr clone() {
        return (BooleanLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public BooleanLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.booleanLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isBooleanLiteralExpr() {
        return true;
    }

    @Override
    public BooleanLiteralExpr asBooleanLiteralExpr() {
        return this;
    }

    @Override
    public void ifBooleanLiteralExpr(Consumer<BooleanLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<BooleanLiteralExpr> toBooleanLiteralExpr() {
        return Optional.of(this);
    }
}

