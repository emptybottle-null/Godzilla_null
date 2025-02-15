/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.ParameterMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.utils.Utils;

public class Parameter
extends Node
implements NodeWithType<Parameter, Type>,
NodeWithAnnotations<Parameter>,
NodeWithSimpleName<Parameter>,
NodeWithFinalModifier<Parameter>,
Resolvable<ResolvedParameterDeclaration> {
    private Type type;
    private boolean isVarArgs;
    private NodeList<AnnotationExpr> varArgsAnnotations;
    private NodeList<Modifier> modifiers;
    private NodeList<AnnotationExpr> annotations;
    private SimpleName name;

    public Parameter() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new ClassOrInterfaceType(), false, new NodeList<AnnotationExpr>(), new SimpleName());
    }

    public Parameter(Type type, SimpleName name) {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), type, false, new NodeList<AnnotationExpr>(), name);
    }

    public Parameter(Type type, String name) {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), type, false, new NodeList<AnnotationExpr>(), new SimpleName(name));
    }

    public Parameter(NodeList<Modifier> modifiers, Type type, SimpleName name) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), type, false, new NodeList<AnnotationExpr>(), name);
    }

    @AllFieldsConstructor
    public Parameter(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, boolean isVarArgs, NodeList<AnnotationExpr> varArgsAnnotations, SimpleName name) {
        this(null, modifiers, annotations, type, isVarArgs, varArgsAnnotations, name);
    }

    public Parameter(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, boolean isVarArgs, NodeList<AnnotationExpr> varArgsAnnotations, SimpleName name) {
        super(tokenRange);
        this.setModifiers((NodeList)modifiers);
        this.setAnnotations((NodeList)annotations);
        this.setType(type);
        this.setVarArgs(isVarArgs);
        this.setVarArgsAnnotations(varArgsAnnotations);
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

    public boolean isVarArgs() {
        return this.isVarArgs;
    }

    @Override
    public Parameter setType(Type type) {
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

    public Parameter setVarArgs(boolean isVarArgs) {
        if (isVarArgs == this.isVarArgs) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VAR_ARGS, this.isVarArgs, isVarArgs);
        this.isVarArgs = isVarArgs;
        return this;
    }

    @Override
    public NodeList<AnnotationExpr> getAnnotations() {
        return this.annotations;
    }

    @Override
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public Parameter setAnnotations(NodeList<AnnotationExpr> annotations) {
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
    public Parameter setName(SimpleName name) {
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
    public Parameter setModifiers(NodeList<Modifier> modifiers) {
        Utils.assertNotNull(modifiers);
        if (modifiers == this.modifiers) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MODIFIERS, this.modifiers, modifiers);
        if (this.modifiers != null) {
            this.modifiers.setParentNode(null);
        }
        this.modifiers = modifiers;
        this.setAsParentNodeOf(modifiers);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.remove(i);
            return true;
        }
        for (i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.remove(i);
            return true;
        }
        for (i = 0; i < this.varArgsAnnotations.size(); ++i) {
            if (this.varArgsAnnotations.get(i) != node) continue;
            this.varArgsAnnotations.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public NodeList<AnnotationExpr> getVarArgsAnnotations() {
        return this.varArgsAnnotations;
    }

    public Parameter setVarArgsAnnotations(NodeList<AnnotationExpr> varArgsAnnotations) {
        Utils.assertNotNull(varArgsAnnotations);
        if (varArgsAnnotations == this.varArgsAnnotations) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VAR_ARGS_ANNOTATIONS, this.varArgsAnnotations, varArgsAnnotations);
        if (this.varArgsAnnotations != null) {
            this.varArgsAnnotations.setParentNode(null);
        }
        this.varArgsAnnotations = varArgsAnnotations;
        this.setAsParentNodeOf(varArgsAnnotations);
        return this;
    }

    @Override
    public Parameter clone() {
        return (Parameter)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ParameterMetaModel getMetaModel() {
        return JavaParserMetaModel.parameterMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.annotations.size(); ++i) {
            if (this.annotations.get(i) != node) continue;
            this.annotations.set(i, (AnnotationExpr)replacementNode);
            return true;
        }
        for (i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.set(i, (Modifier)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        if (node == this.type) {
            this.setType((Type)replacementNode);
            return true;
        }
        for (i = 0; i < this.varArgsAnnotations.size(); ++i) {
            if (this.varArgsAnnotations.get(i) != node) continue;
            this.varArgsAnnotations.set(i, (AnnotationExpr)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public ResolvedParameterDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedParameterDeclaration.class);
    }
}

