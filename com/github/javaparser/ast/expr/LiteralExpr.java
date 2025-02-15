/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LiteralExprMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class LiteralExpr
extends Expression {
    @AllFieldsConstructor
    public LiteralExpr() {
        this(null);
    }

    public LiteralExpr(TokenRange tokenRange) {
        super(tokenRange);
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
    public LiteralExpr clone() {
        return (LiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.literalExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isLiteralExpr() {
        return true;
    }

    @Override
    public LiteralExpr asLiteralExpr() {
        return this;
    }

    @Override
    public void ifLiteralExpr(Consumer<LiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<LiteralExpr> toLiteralExpr() {
        return Optional.of(this);
    }
}

