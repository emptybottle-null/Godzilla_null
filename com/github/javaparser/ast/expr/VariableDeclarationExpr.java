/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithVariables;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithFinalModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.VariableDeclarationExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class VariableDeclarationExpr
extends Expression
implements NodeWithFinalModifier<VariableDeclarationExpr>,
NodeWithAnnotations<VariableDeclarationExpr>,
NodeWithVariables<VariableDeclarationExpr> {
    private NodeList<Modifier> modifiers;
    private NodeList<AnnotationExpr> annotations;
    @NonEmptyProperty
    private NodeList<VariableDeclarator> variables;

    public VariableDeclarationExpr() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new NodeList<VariableDeclarator>());
    }

    public VariableDeclarationExpr(Type type, String variableName) {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), NodeList.nodeList((Node[])new VariableDeclarator[]{new VariableDeclarator(type, variableName)}));
    }

    public VariableDeclarationExpr(VariableDeclarator var) {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), NodeList.nodeList((Node[])new VariableDeclarator[]{var}));
    }

    public VariableDeclarationExpr(Type type, String variableName, Modifier ... modifiers) {
        this(null, Arrays.stream(modifiers).collect(Collectors.toCollection(() -> new NodeList())), new NodeList<AnnotationExpr>(), NodeList.nodeList((Node[])new VariableDeclarator[]{new VariableDeclarator(type, variableName)}));
    }

    public VariableDeclarationExpr(VariableDeclarator var, Modifier ... modifiers) {
        this(null, Arrays.stream(modifiers).collect(Collectors.toCollection(() -> new NodeList())), new NodeList<AnnotationExpr>(), NodeList.nodeList((Node[])new VariableDeclarator[]{var}));
    }

    public VariableDeclarationExpr(NodeList<VariableDeclarator> variables) {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), variables);
    }

    public VariableDeclarationExpr(NodeList<Modifier> modifiers, NodeList<VariableDeclarator> variables) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), variables);
    }

    @AllFieldsConstructor
    public VariableDeclarationExpr(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
        this(null, modifiers, annotations, variables);
    }

    public VariableDeclarationExpr(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, NodeList<VariableDeclarator> variables) {
        super(tokenRange);
        this.setModifiers((NodeList)modifiers);
        this.setAnnotations((NodeList)annotations);
        this.setVariables((NodeList)variables);
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
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public NodeList<VariableDeclarator> getVariables() {
        return this.variables;
    }

    @Override
    public VariableDeclarationExpr setAnnotations(NodeList<AnnotationExpr> annotations) {
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
    public VariableDeclarationExpr setModifiers(NodeList<Modifier> modifiers) {
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
    public VariableDeclarationExpr setVariables(NodeList<VariableDeclarator> variables) {
        Utils.assertNotNull(variables);
        if (variables == this.variables) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VARIABLES, this.variables, variables);
        if (this.variables != null) {
            this.variables.setParentNode(null);
        }
        this.variables = variables;
        this.setAsParentNodeOf(variables);
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
        for (i = 0; i < this.variables.size(); ++i) {
            if (this.variables.get(i) != node) continue;
            this.variables.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public VariableDeclarationExpr clone() {
        return (VariableDeclarationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public VariableDeclarationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.variableDeclarationExprMetaModel;
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
        for (i = 0; i < this.variables.size(); ++i) {
            if (this.variables.get(i) != node) continue;
            this.variables.set(i, (VariableDeclarator)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isVariableDeclarationExpr() {
        return true;
    }

    @Override
    public VariableDeclarationExpr asVariableDeclarationExpr() {
        return this;
    }

    @Override
    public void ifVariableDeclarationExpr(Consumer<VariableDeclarationExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<VariableDeclarationExpr> toVariableDeclarationExpr() {
        return Optional.of(this);
    }
}

