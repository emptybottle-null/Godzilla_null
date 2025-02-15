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
import com.github.javaparser.metamodel.CharLiteralExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.StringEscapeUtils;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class CharLiteralExpr
extends LiteralStringValueExpr {
    public CharLiteralExpr() {
        this(null, "?");
    }

    @AllFieldsConstructor
    public CharLiteralExpr(String value) {
        this(null, value);
    }

    public CharLiteralExpr(char value) {
        this(null, StringEscapeUtils.escapeJava(String.valueOf(value)));
    }

    public CharLiteralExpr(TokenRange tokenRange, String value) {
        super(tokenRange, value);
        this.customInitialization();
    }

    public static CharLiteralExpr escape(String string) {
        return new CharLiteralExpr(Utils.escapeEndOfLines(string));
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

    public char asChar() {
        return StringEscapeUtils.unescapeJava(this.value).charAt(0);
    }

    public CharLiteralExpr setChar(char value) {
        this.value = String.valueOf(value);
        return this;
    }

    @Override
    public CharLiteralExpr clone() {
        return (CharLiteralExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public CharLiteralExprMetaModel getMetaModel() {
        return JavaParserMetaModel.charLiteralExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isCharLiteralExpr() {
        return true;
    }

    @Override
    public CharLiteralExpr asCharLiteralExpr() {
        return this;
    }

    @Override
    public void ifCharLiteralExpr(Consumer<CharLiteralExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<CharLiteralExpr> toCharLiteralExpr() {
        return Optional.of(this);
    }
}

