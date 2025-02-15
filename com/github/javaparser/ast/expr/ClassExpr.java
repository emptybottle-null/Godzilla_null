/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ClassExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ClassExpr
extends Expression
implements NodeWithType<ClassExpr, Type> {
    private Type type;

    public ClassExpr() {
        this(null, new ClassOrInterfaceType());
    }

    @AllFieldsConstructor
    public ClassExpr(Type type) {
        this(null, type);
    }

    public ClassExpr(TokenRange tokenRange, Type type) {
        super(tokenRange);
        this.setType(type);
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
    public Type getType() {
        return this.type;
    }

    @Override
    public ClassExpr setType(Type type) {
        Utils.assertNotNull(type);
        if (type == this.type) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
        if (this.type != null) {
            this.type.setParentNode(null);
        }
        this.type = type;
        this.setAsParentNodeOf(type);
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
    public ClassExpr clone() {
        return (ClassExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ClassExprMetaModel getMetaModel() {
        return JavaParserMetaModel.classExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.type) {
            this.setType((Type)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isClassExpr() {
        return true;
    }

    @Override
    public ClassExpr asClassExpr() {
        return this;
    }

    @Override
    public void ifClassExpr(Consumer<ClassExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<ClassExpr> toClassExpr() {
        return Optional.of(this);
    }
}

