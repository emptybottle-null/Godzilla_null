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
import com.github.javaparser.metamodel.IntegerLiteralExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class IntegerLiteralExpr
extends LiteralStringValueExpr {
    public static final String MAX_31_BIT_UNSIGNED_VALUE_AS_STRING = "2147483648";
    public static final long MAX_31_BIT_UNSIGNED_VALUE_AS_LONG = 0x80000000L;

    public IntegerLiteralExpr() {
        this(null, "0");
    }

    @AllFieldsConstructor
    public IntegerLiteralExpr(String value) {
        this(null, value);
    }

    public IntegerLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
        this.customInitialization();
    }

    @Deprecated
    public IntegerLiteralExpr(int value) {
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

    @Deprecated
    public int asInt() {
        String result = this.value.replaceAll("_", "");
        if (result.startsWith("0x") || result.startsWith("0X")) {
            return Integer.parseUnsignedInt(result.substring(2), 16);
        }
        if (result.startsWith("0b") || result.startsWith("0B")) {
            return Integer.parseUnsignedInt(result.substring(2), 2);
        }
        if (result.length() > 1 && result.startsWith("0")) {
            return Integer.parseUnsignedInt(result.substring(1), 8);
        }
        return Integer.parseInt(result);
    }

    public Number asNumber() {
        if (Objects.equals(this.value, MAX_31_BIT_UNSIGNED_VALUE_AS_STRING) && Utils.hasUnaryMinusAsParent(this)) {
            return 0x80000000L;
        }
        return this.asInt();
    }

    @Deprecated
    public IntegerLiteralExpr setInt(int value) {
        this.value = String.valueOf(value);
        return this;
    }

    @Override
    public IntegerLiteralExpr clone() {
        return (IntegerLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public IntegerLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.integerLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isIntegerLiteralExpr() {
        return true;
    }

    @Override
    public IntegerLiteralExpr asIntegerLiteralExpr() {
        return this;
    }

    @Override
    public void ifIntegerLiteralExpr(Consumer<IntegerLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<IntegerLiteralExpr> toIntegerLiteralExpr() {
        return Optional.of(this);
    }
}

