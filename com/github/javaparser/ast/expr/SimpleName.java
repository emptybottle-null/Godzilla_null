/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithIdentifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.SimpleNameMetaModel;
import com.github.javaparser.utils.Utils;

public class SimpleName
extends Node
implements NodeWithIdentifier<SimpleName> {
    @NonEmptyProperty
    private String identifier;

    public SimpleName() {
        this(null, "empty");
    }

    @AllFieldsConstructor
    public SimpleName(String identifier) {
        this(null, identifier);
    }

    public SimpleName(TokenRange tokenRange, String identifier) {
        super(tokenRange);
        this.setIdentifier(identifier);
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
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public SimpleName setIdentifier(String identifier) {
        Utils.assertNonEmpty(identifier);
        if (identifier == this.identifier) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.IDENTIFIER, this.identifier, identifier);
        this.identifier = identifier;
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    public String asString() {
        return this.identifier;
    }

    @Override
    public SimpleName clone() {
        return (SimpleName)this.accept(new CloneVisitor(), null);
    }

    @Override
    public SimpleNameMetaModel getMetaModel() {
        return JavaParserMetaModel.simpleNameMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }
}

