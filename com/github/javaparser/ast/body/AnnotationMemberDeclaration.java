/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAbstractModifier;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithPublicModifier;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.AnnotationMemberDeclarationMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationMemberDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class AnnotationMemberDeclaration
extends BodyDeclaration<AnnotationMemberDeclaration>
implements NodeWithJavadoc<AnnotationMemberDeclaration>,
NodeWithSimpleName<AnnotationMemberDeclaration>,
NodeWithType<AnnotationMemberDeclaration, Type>,
NodeWithPublicModifier<AnnotationMemberDeclaration>,
NodeWithAbstractModifier<AnnotationMemberDeclaration>,
Resolvable<ResolvedAnnotationMemberDeclaration> {
    private NodeList<Modifier> modifiers;
    private Type type;
    private SimpleName name;
    @OptionalProperty
    private Expression defaultValue;

    public AnnotationMemberDeclaration() {
        this(null, new NodeList<Modifier>(), new NodeList<AnnotationExpr>(), new ClassOrInterfaceType(), new SimpleName(), null);
    }

    public AnnotationMemberDeclaration(NodeList<Modifier> modifiers, Type type, String name, Expression defaultValue) {
        this(null, modifiers, new NodeList<AnnotationExpr>(), type, new SimpleName(name), defaultValue);
    }

    @AllFieldsConstructor
    public AnnotationMemberDeclaration(NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, SimpleName name, Expression defaultValue) {
        this(null, modifiers, annotations, type, name, defaultValue);
    }

    public AnnotationMemberDeclaration(TokenRange tokenRange, NodeList<Modifier> modifiers, NodeList<AnnotationExpr> annotations, Type type, SimpleName name, Expression defaultValue) {
        super(tokenRange, annotations);
        this.setModifiers((NodeList)modifiers);
        this.setType(type);
        this.setName(name);
        this.setDefaultValue(defaultValue);
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

    public Optional<Expression> getDefaultValue() {
        return Optional.ofNullable(this.defaultValue);
    }

    @Override
    public NodeList<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    public AnnotationMemberDeclaration removeDefaultValue() {
        return this.setDefaultValue(null);
    }

    public AnnotationMemberDeclaration setDefaultValue(Expression defaultValue) {
        if (defaultValue == this.defaultValue) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.DEFAULT_VALUE, this.defaultValue, defaultValue);
        if (this.defaultValue != null) {
            this.defaultValue.setParentNode(null);
        }
        this.defaultValue = defaultValue;
        this.setAsParentNodeOf(defaultValue);
        return this;
    }

    @Override
    public AnnotationMemberDeclaration setModifiers(NodeList<Modifier> modifiers) {
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
    public AnnotationMemberDeclaration setName(SimpleName name) {
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
    public AnnotationMemberDeclaration setType(Type type) {
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
        if (this.defaultValue != null && node == this.defaultValue) {
            this.removeDefaultValue();
            return true;
        }
        for (int i = 0; i < this.modifiers.size(); ++i) {
            if (this.modifiers.get(i) != node) continue;
            this.modifiers.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public AnnotationMemberDeclaration clone() {
        return (AnnotationMemberDeclaration)this.accept(new CloneVisitor(), null);
    }

    @Override
    public AnnotationMemberDeclarationMetaModel getMetaModel() {
        return JavaParserMetaModel.annotationMemberDeclarationMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.defaultValue != null && node == this.defaultValue) {
            this.setDefaultValue((Expression)replacementNode);
            return true;
        }
        for (int i = 0; i < this.modifiers.size(); ++i) {
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
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isAnnotationMemberDeclaration() {
        return true;
    }

    @Override
    public AnnotationMemberDeclaration asAnnotationMemberDeclaration() {
        return this;
    }

    @Override
    public void ifAnnotationMemberDeclaration(Consumer<AnnotationMemberDeclaration> action) {
        action.accept(this);
    }

    @Override
    public ResolvedAnnotationMemberDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedAnnotationMemberDeclaration.class);
    }

    @Override
    public Optional<AnnotationMemberDeclaration> toAnnotationMemberDeclaration() {
        return Optional.of(this);
    }
}

