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
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.TypeExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class TypeExpr
extends Expression
implements NodeWithType<TypeExpr, Type> {
    private Type type;

    public TypeExpr() {
        this(null, new ClassOrInterfaceType());
    }

    @AllFieldsConstructor
    public TypeExpr(Type type) {
        this(null, type);
    }

    public TypeExpr(TokenRange tokenRange, Type type) {
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
    public TypeExpr setType(Type type) {
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
    public TypeExpr clone() {
        return (TypeExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public TypeExprMetaModel getMetaModel() {
        return JavaParserMetaModel.typeExprMetaModel;
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
    public boolean isTypeExpr() {
        return true;
    }

    @Override
    public TypeExpr asTypeExpr() {
        return this;
    }

    @Override
    public void ifTypeExpr(Consumer<TypeExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<TypeExpr> toTypeExpr() {
        return Optional.of(this);
    }
}

