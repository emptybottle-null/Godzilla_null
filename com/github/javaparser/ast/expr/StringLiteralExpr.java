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
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.StringLiteralExprMetaModel;
import com.github.javaparser.utils.StringEscapeUtils;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class StringLiteralExpr
extends LiteralStringValueExpr {
    public StringLiteralExpr() {
        this(null, "empty");
    }

    @AllFieldsConstructor
    public StringLiteralExpr(String value) {
        this(null, Utils.escapeEndOfLines(value));
    }

    public StringLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
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

    public StringLiteralExpr setEscapedValue(String value) {
        this.value = Utils.escapeEndOfLines(value);
        return this;
    }

    public String asString() {
        return StringEscapeUtils.unescapeJava(this.value);
    }

    public StringLiteralExpr setString(String value) {
        this.value = StringEscapeUtils.escapeJava(value);
        return this;
    }

    @Override
    public StringLiteralExpr clone() {
        return (StringLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public StringLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.stringLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isStringLiteralExpr() {
        return true;
    }

    @Override
    public StringLiteralExpr asStringLiteralExpr() {
        return this;
    }

    @Override
    public void ifStringLiteralExpr(Consumer<StringLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<StringLiteralExpr> toStringLiteralExpr() {
        return Optional.of(this);
    }
}

