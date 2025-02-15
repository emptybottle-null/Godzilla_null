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
import com.github.javaparser.metamodel.NameMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;

public class Name
extends Node
implements NodeWithIdentifier<Name> {
    @NonEmptyProperty
    private String identifier;
    @OptionalProperty
    private Name qualifier;

    public Name() {
        this(null, null, "empty");
    }

    public Name(String identifier) {
        this(null, null, identifier);
    }

    @AllFieldsConstructor
    public Name(Name qualifier, String identifier) {
        this(null, qualifier, identifier);
    }

    public Name(TokenRange tokenRange, Name qualifier, String identifier) {
        super(tokenRange);
        this.setQualifier(qualifier);
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
    public Name setIdentifier(String identifier) {
        Utils.assertNonEmpty(identifier);
        if (identifier == this.identifier) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.IDENTIFIER, this.identifier, identifier);
        this.identifier = identifier;
        return this;
    }

    public String asString() {
        if (this.qualifier != null) {
            return this.qualifier.asString() + "." + this.identifier;
        }
        return this.identifier;
    }

    public Optional<Name> getQualifier() {
        return Optional.ofNullable(this.qualifier);
    }

    public Name setQualifier(Name qualifier) {
        if (qualifier == this.qualifier) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.QUALIFIER, this.qualifier, qualifier);
        if (this.qualifier != null) {
            this.qualifier.setParentNode(null);
        }
        this.qualifier = qualifier;
        this.setAsParentNodeOf(qualifier);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.qualifier != null && node == this.qualifier) {
            this.removeQualifier();
            return true;
        }
        return super.remove(node);
    }

    public Name removeQualifier() {
        return this.setQualifier(null);
    }

    @Override
    public Name clone() {
        return (Name)this.accept(new CloneVisitor(), null);
    }

    @Override
    public NameMetaModel getMetaModel() {
        return JavaParserMetaModel.nameMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.qualifier != null && node == this.qualifier) {
            this.setQualifier((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public boolean isTopLevel() {
        return !this.isInternal();
    }

    public boolean isInternal() {
        return this.getParentNode().filter(parent -> parent instanceof Name).isPresent();
    }
}

