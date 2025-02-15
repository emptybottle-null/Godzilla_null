/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.EnclosedExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class EnclosedExpr
extends Expression {
    private Expression inner;

    public EnclosedExpr() {
        this(null, new StringLiteralExpr());
    }

    @AllFieldsConstructor
    public EnclosedExpr(Expression inner) {
        this(null, inner);
    }

    public EnclosedExpr(TokenRange tokenRange, Expression inner) {
        super(tokenRange);
        this.setInner(inner);
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

    public Expression getInner() {
        return this.inner;
    }

    public EnclosedExpr setInner(Expression inner) {
        Utils.assertNotNull(inner);
        if (inner == this.inner) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.INNER, this.inner, inner);
        if (this.inner != null) {
            this.inner.setParentNode(null);
        }
        this.inner = inner;
        this.setAsParentNodeOf(inner);
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
    public EnclosedExpr clone() {
        return (EnclosedExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public EnclosedExprMetaModel getMetaModel() {
        return JavaParserMetaModel.enclosedExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.inner) {
            this.setInner((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isEnclosedExpr() {
        return true;
    }

    @Override
    public EnclosedExpr asEnclosedExpr() {
        return this;
    }

    @Override
    public void ifEnclosedExpr(Consumer<EnclosedExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<EnclosedExpr> toEnclosedExpr() {
        return Optional.of(this);
    }

    @Override
    public boolean isPolyExpression() {
        return this.getInner().isPolyExpression();
    }
}

