/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NullLiteralExprMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class NullLiteralExpr
extends LiteralExpr {
    @AllFieldsConstructor
    public NullLiteralExpr() {
        this(null);
    }

    public NullLiteralExpr(TokenRange tokenRange) {
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public NullLiteralExpr clone() {
        return (NullLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public NullLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.nullLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isNullLiteralExpr() {
        return true;
    }

    @Override
    public NullLiteralExpr asNullLiteralExpr() {
        return this;
    }

    @Override
    public void ifNullLiteralExpr(Consumer<NullLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<NullLiteralExpr> toNullLiteralExpr() {
        return Optional.of(this);
    }
}

