/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.MemberValuePairMetaModel;
import com.github.javaparser.utils.Utils;

public class MemberValuePair
extends Node
implements NodeWithSimpleName<MemberValuePair> {
    private SimpleName name;
    private Expression value;

    public MemberValuePair() {
        this(null, new SimpleName(), new StringLiteralExpr());
    }

    public MemberValuePair(String name, Expression value) {
        this(null, new SimpleName(name), value);
    }

    @AllFieldsConstructor
    public MemberValuePair(SimpleName name, Expression value) {
        this(null, name, value);
    }

    public MemberValuePair(TokenRange tokenRange, SimpleName name, Expression value) {
        super(tokenRange);
        this.setName(name);
        this.setValue(value);
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
    public SimpleName getName() {
        return this.name;
    }

    public Expression getValue() {
        return this.value;
    }

    @Override
    public MemberValuePair setName(SimpleName name) {
        Utils.assertNotNull(name);
        if (name == this.name) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.NAME, this.name, name);
        if (this.name != null) {
            this.name.setParentNode(null);
        }
        this.name = name;
        this.setAsParentNodeOf(name);
        return this;
    }

    public MemberValuePair setValue(Expression value) {
        Utils.assertNotNull(value);
        if (value == this.value) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VALUE, this.value, value);
        if (this.value != null) {
            this.value.setParentNode(null);
        }
        this.value = value;
        this.setAsParentNodeOf(value);
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
    public MemberValuePair clone() {
        return (MemberValuePair)this.accept(new CloneVisitor(), null);
    }

    @Override
    public MemberValuePairMetaModel getMetaModel() {
        return JavaParserMetaModel.memberValuePairMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        if (node == this.value) {
            this.setValue((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }
}

