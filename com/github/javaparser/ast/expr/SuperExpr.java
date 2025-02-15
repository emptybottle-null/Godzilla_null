/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.metamodel.SuperExprMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class SuperExpr
extends Expression {
    @OptionalProperty
    private Name typeName;

    public SuperExpr() {
        this(null, null);
    }

    @AllFieldsConstructor
    public SuperExpr(Name typeName) {
        this(null, typeName);
    }

    public SuperExpr(TokenRange tokenRange, Name typeName) {
        super(tokenRange);
        this.setTypeName(typeName);
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

    public Optional<Name> getTypeName() {
        return Optional.ofNullable(this.typeName);
    }

    public SuperExpr setTypeName(Name typeName) {
        if (typeName == this.typeName) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_NAME, this.typeName, typeName);
        if (this.typeName != null) {
            this.typeName.setParentNode(null);
        }
        this.typeName = typeName;
        this.setAsParentNodeOf(typeName);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.typeName != null && node == this.typeName) {
            this.removeTypeName();
            return true;
        }
        return super.remove(node);
    }

    @Override
    public SuperExpr clone() {
        return (SuperExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public SuperExprMetaModel getMetaModel() {
        return JavaParserMetaModel.superExprMetaModel;
    }

    @Override
    public boolean isSuperExpr() {
        return true;
    }

    @Override
    public SuperExpr asSuperExpr() {
        return this;
    }

    @Override
    public void ifSuperExpr(Consumer<SuperExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<SuperExpr> toSuperExpr() {
        return Optional.of(this);
    }

    public SuperExpr removeClassName() {
        return this.setTypeName(null);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.typeName != null && node == this.typeName) {
            this.setTypeName((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public SuperExpr removeTypeName() {
        return this.setTypeName(null);
    }
}

