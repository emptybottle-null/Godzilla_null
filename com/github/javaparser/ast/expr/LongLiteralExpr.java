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
import com.github.javaparser.metamodel.LongLiteralExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class LongLiteralExpr
extends LiteralStringValueExpr {
    public static final String MAX_63_BIT_UNSIGNED_VALUE_AS_STRING = "9223372036854775808L";
    public static final BigInteger MAX_63_BIT_UNSIGNED_VALUE_AS_BIG_INTEGER = new BigInteger("9223372036854775808");

    public LongLiteralExpr() {
        this(null, "0");
    }

    @AllFieldsConstructor
    public LongLiteralExpr(String value) {
        this(null, value);
    }

    public LongLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
        this.customInitialization();
    }

    @Deprecated
    public LongLiteralExpr(long value) {
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
    public long asLong() {
        String result = this.value.replaceAll("_", "");
        char lastChar = result.charAt(result.length() - 1);
        if (lastChar == 'l' || lastChar == 'L') {
            result = result.substring(0, result.length() - 1);
        }
        if (result.startsWith("0x") || result.startsWith("0X")) {
            return Long.parseUnsignedLong(result.substring(2), 16);
        }
        if (result.startsWith("0b") || result.startsWith("0B")) {
            return Long.parseUnsignedLong(result.substring(2), 2);
        }
        if (result.length() > 1 && result.startsWith("0")) {
            return Long.parseUnsignedLong(result.substring(1), 8);
        }
        return Long.parseLong(result);
    }

    public Number asNumber() {
        if (Objects.equals(this.value, MAX_63_BIT_UNSIGNED_VALUE_AS_STRING) && Utils.hasUnaryMinusAsParent(this)) {
            return MAX_63_BIT_UNSIGNED_VALUE_AS_BIG_INTEGER;
        }
        return this.asLong();
    }

    @Deprecated
    public LongLiteralExpr setLong(long value) {
        this.value = String.valueOf(value);
        return this;
    }

    @Override
    public LongLiteralExpr clone() {
        return (LongLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LongLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.longLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isLongLiteralExpr() {
        return true;
    }

    @Override
    public LongLiteralExpr asLongLiteralExpr() {
        return this;
    }

    @Override
    public void ifLongLiteralExpr(Consumer<LongLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<LongLiteralExpr> toLongLiteralExpr() {
        return Optional.of(this);
    }
}

