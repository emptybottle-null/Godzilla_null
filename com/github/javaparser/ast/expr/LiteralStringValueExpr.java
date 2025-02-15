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
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LiteralStringValueExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class LiteralStringValueExpr
extends LiteralExpr {
    protected String value;

    @AllFieldsConstructor
    public LiteralStringValueExpr(String value) {
        this(null, value);
    }

    public LiteralStringValueExpr(TokenRange tokenRange, String value) {
        super(tokenRange);
        this.setValue(value);
        this.customInitialization();
    }

    public String getValue() {
        return this.value;
    }

    public LiteralStringValueExpr setValue(String value) {
        Utils.assertNotNull(value);
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
    public LiteralStringValueExpr clone() {
        return (LiteralStringValueExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LiteralStringValueExprMetaModel getMetaModel() {
        return JavaParserMetaModel.literalStringValueExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isLiteralStringValueExpr() {
        return true;
    }

    @Override
    public LiteralStringValueExpr asLiteralStringValueExpr() {
        return this;
    }

    @Override
    public void ifLiteralStringValueExpr(Consumer<LiteralStringValueExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<LiteralStringValueExpr> toLiteralStringValueExpr() {
        return Optional.of(this);
    }
}

