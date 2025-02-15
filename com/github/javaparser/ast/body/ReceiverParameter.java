/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithName;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ReceiverParameterMetaModel;
import com.github.javaparser.utils.Utils;

public class ReceiverParameter
extends Node
implements NodeWithType<ReceiverParameter, Type>,
NodeWithAnnotations<ReceiverParameter>,
NodeWithName<ReceiverParameter> {
    private Type type;
    private NodeList<AnnotationExpr> annotations;
    private Name name;

    public ReceiverParameter() {
        this(null, new NodeList<AnnotationExpr>(), new ClassOrInterfaceType(), new Name());
    }

    public ReceiverParameter(Type type, Name name) {
        this(null, new NodeList<AnnotationExpr>(), type, name);
    }

    public ReceiverParameter(Type type, String name) {
        this(null, new NodeList<AnnotationExpr>(), type, new Name(name));
    }

    @AllFieldsConstructor
    public ReceiverParameter(NodeList<AnnotationExpr> annotations, Type type, Name name) {
        this(null, annotations, type, name);
    }

    public ReceiverParameter(TokenRange tokenRange, NodeList<AnnotationExpr> annotations, Type type, Name name) {
        super(tokenRange);
        this.setAnnotations((NodeList)annotations);
        this.setType(type);
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
    public Type getType() {
        return this.type;
    }

    @Override
    public ReceiverParameter setType(Type type) {
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
    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    @Override
    public ReceiverParameter setAnnotations(NodeList<AnnotationExpr> annotations) {
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
    public ReceiverParameter clone() {
        return (ReceiverParameter)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ReceiverParameterMetaModel getMetaModel() {
        return JavaParserMetaModel.receiverParameterMetaModel;
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public ReceiverParameter setName(Name name) {
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
        if (node == this.type) {
            this.setType((Type)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }
}

