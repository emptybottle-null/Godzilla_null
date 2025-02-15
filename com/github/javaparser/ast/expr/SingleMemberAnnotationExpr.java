/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.SingleMemberAnnotationExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class SingleMemberAnnotationExpr
extends AnnotationExpr {
    private Expression memberValue;

    public SingleMemberAnnotationExpr() {
        this(null, new Name(), new StringLiteralExpr());
    }

    @AllFieldsConstructor
    public SingleMemberAnnotationExpr(Name name, Expression memberValue) {
        this(null, name, memberValue);
    }

    public SingleMemberAnnotationExpr(TokenRange tokenRange, Name name, Expression memberValue) {
        super(tokenRange, name);
        this.setMemberValue(memberValue);
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

    public Expression getMemberValue() {
        return this.memberValue;
    }

    public SingleMemberAnnotationExpr setMemberValue(Expression memberValue) {
        Utils.assertNotNull(memberValue);
        if (memberValue == this.memberValue) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MEMBER_VALUE, this.memberValue, memberValue);
        if (this.memberValue != null) {
            this.memberValue.setParentNode(null);
        }
        this.memberValue = memberValue;
        this.setAsParentNodeOf(memberValue);
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
    public SingleMemberAnnotationExpr clone() {
        return (SingleMemberAnnotationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public SingleMemberAnnotationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.singleMemberAnnotationExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.memberValue) {
            this.setMemberValue((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isSingleMemberAnnotationExpr() {
        return true;
    }

    @Override
    public SingleMemberAnnotationExpr asSingleMemberAnnotationExpr() {
        return this;
    }

    @Override
    public void ifSingleMemberAnnotationExpr(Consumer<SingleMemberAnnotationExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<SingleMemberAnnotationExpr> toSingleMemberAnnotationExpr() {
        return Optional.of(this);
    }
}

