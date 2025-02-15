/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ArrayInitializerExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ArrayInitializerExpr
extends Expression {
    private NodeList<Expression> values;

    public ArrayInitializerExpr() {
        this(null, new NodeList<Expression>());
    }

    @AllFieldsConstructor
    public ArrayInitializerExpr(NodeList<Expression> values) {
        this(null, values);
    }

    public ArrayInitializerExpr(TokenRange tokenRange, NodeList<Expression> values) {
        super(tokenRange);
        this.setValues(values);
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

    public NodeList<Expression> getValues() {
        return this.values;
    }

    public ArrayInitializerExpr setValues(NodeList<Expression> values) {
        Utils.assertNotNull(values);
        if (values == this.values) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VALUES, this.values, values);
        if (this.values != null) {
            this.values.setParentNode(null);
        }
        this.values = values;
        this.setAsParentNodeOf(values);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.values.size(); ++i) {
            if (this.values.get(i) != node) continue;
            this.values.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public ArrayInitializerExpr clone() {
        return (ArrayInitializerExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ArrayInitializerExprMetaModel getMetaModel() {
        return JavaParserMetaModel.arrayInitializerExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.values.size(); ++i) {
            if (this.values.get(i) != node) continue;
            this.values.set(i, (Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isArrayInitializerExpr() {
        return true;
    }

    @Override
    public ArrayInitializerExpr asArrayInitializerExpr() {
        return this;
    }

    @Override
    public void ifArrayInitializerExpr(Consumer<ArrayInitializerExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<ArrayInitializerExpr> toArrayInitializerExpr() {
        return Optional.of(this);
    }
}

