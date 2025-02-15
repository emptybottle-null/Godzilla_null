/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.PackageDeclarationMetaModel;
import com.github.javaparser.utils.Utils;

public class PackageDeclaration
extends Node
implements NodeWithAnnotations<PackageDeclaration>,
NodeWithName<PackageDeclaration> {
    private NodeList<AnnotationExpr> annotations = new NodeList();
    private Name name;

    public PackageDeclaration() {
        this(null, new NodeList<AnnotationExpr>(), new Name());
    }

    public PackageDeclaration(Name name) {
        this(null, new NodeList<AnnotationExpr>(), name);
    }

    @AllFieldsConstructor
    public PackageDeclaration(NodeList<AnnotationExpr> annotations, Name name) {
        this(null, annotations, name);
    }

    public PackageDeclaration(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, Name name) {
        super(tokenRange);
        this.setAnnotations((NodeList)annotations);
        this.setName(name);
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
    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public PackageDeclaration setAnnotations(NodeList<AnnotationExpr> annotations) {
        Utils.assertNotNull(annotations);
        if (annotations == this.annotations) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.annotations, annotations);
        if (this.annotations != null) {
            this.annotations.setParentNode(null);
        }
        this.annotations = annotations;
        this.setAsParentNodeOf(annotations);
        return this;
    }

    @Override
    public PackageDeclaration setName(Name name) {
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

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public PackageDeclaration clone() {
        return (PackageDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public PackageDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.packageDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.set(i, (AnnotationExpr)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((Name)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }
}

