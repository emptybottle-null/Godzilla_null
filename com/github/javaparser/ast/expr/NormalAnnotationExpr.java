/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NormalAnnotationExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class NormalAnnotationExpr
extends AnnotationExpr {
    private NodeList<MemberValuePair> pairs;

    public NormalAnnotationExpr() {
        this(null, new Name(), new NodeList<MemberValuePair>());
    }

    @AllFieldsConstructor
    public NormalAnnotationExpr(Name name, NodeList<MemberValuePair> pairs) {
        this(null, name, pairs);
    }

    public NormalAnnotationExpr(TokenRange tokenRange, Name name, NodeList<MemberValuePair> pairs) {
        super(tokenRange, name);
        this.setPairs(pairs);
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

    public NodeList<MemberValuePair> getPairs() {
        return this.pairs;
    }

    public NormalAnnotationExpr setPairs(NodeList<MemberValuePair> pairs) {
        Utils.assertNotNull(pairs);
        if (pairs == this.pairs) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.PAIRS, this.pairs, pairs);
        if (this.pairs != null) {
            this.pairs.setParentNode(null);
        }
        this.pairs = pairs;
        this.setAsParentNodeOf(pairs);
        return this;
    }

    public NormalAnnotationExpr addPair(String key, String value) {
        return this.addPair(key, new NameExpr(value));
    }

    public NormalAnnotationExpr addPair(String key, Expression value) {
        MemberValuePair memberValuePair = new MemberValuePair(key, value);
        this.getPairs().add(memberValuePair);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.pairs.size(); ++i) {
            if (this.pairs.get(i) != node) continue;
            this.pairs.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public NormalAnnotationExpr clone() {
        return (NormalAnnotationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public NormalAnnotationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.normalAnnotationExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.pairs.size(); ++i) {
            if (this.pairs.get(i) != node) continue;
            this.pairs.set(i, (MemberValuePair)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isNormalAnnotationExpr() {
        return true;
    }

    @Override
    public NormalAnnotationExpr asNormalAnnotationExpr() {
        return this;
    }

    @Override
    public void ifNormalAnnotationExpr(Consumer<NormalAnnotationExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<NormalAnnotationExpr> toNormalAnnotationExpr() {
        return Optional.of(this);
    }
}

