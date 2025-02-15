/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ArrayAccessExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ArrayAccessExpr
extends Expression {
    private Expression name;
    private Expression index;

    public ArrayAccessExpr() {
        this(null, new NameExpr(), new IntegerLiteralExpr());
    }

    @AllFieldsConstructor
    public ArrayAccessExpr(Expression name, Expression index) {
        this(null, name, index);
    }

    public ArrayAccessExpr(TokenRange tokenRange, Expression name, Expression index) {
        super(tokenRange);
        this.setName(name);
        this.setIndex(index);
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

    public Expression getIndex() {
        return this.index;
    }

    public Expression getName() {
        return this.name;
    }

    public ArrayAccessExpr setIndex(Expression index) {
        Utils.assertNotNull(index);
        if (index == this.index) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.INDEX, this.index, index);
        if (this.index != null) {
            this.index.setParentNode(null);
        }
        this.index = index;
        this.setAsParentNodeOf(index);
        return this;
    }

    public ArrayAccessExpr setName(Expression name) {
        Utils.assertNotNull(name);
        if (name == this.name) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.NAME, this.name, name);
        if (this.name != null) {
            this.name.setParentNode(null);
        }
        this.name = name;
        this.setAsParentNodeOf(name);
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
    public ArrayAccessExpr clone() {
        return (ArrayAccessExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ArrayAccessExprMetaModel getMetaModel() {
        return JavaParserMetaModel.arrayAccessExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.index) {
            this.setIndex((Expression)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isArrayAccessExpr() {
        return true;
    }

    @Override
    public ArrayAccessExpr asArrayAccessExpr() {
        return this;
    }

    @Override
    public void ifArrayAccessExpr(Consumer<ArrayAccessExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<ArrayAccessExpr> toArrayAccessExpr() {
        return Optional.of(this);
    }
}

