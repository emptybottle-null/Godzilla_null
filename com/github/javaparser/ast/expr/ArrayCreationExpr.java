/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ArrayCreationExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ArrayCreationExpr
extends Expression {
    @NonEmptyProperty
    private NodeList<ArrayCreationLevel> levels;
    private Type elementType;
    @OptionalProperty
    private ArrayInitializerExpr initializer;

    public ArrayCreationExpr() {
        this(null, new ClassOrInterfaceType(), new NodeList<ArrayCreationLevel>(), new ArrayInitializerExpr());
    }

    @AllFieldsConstructor
    public ArrayCreationExpr(Type elementType, NodeList<ArrayCreationLevel> levels, ArrayInitializerExpr initializer) {
        this(null, elementType, levels, initializer);
    }

    public ArrayCreationExpr(Type elementType) {
        this(null, elementType, new NodeList<ArrayCreationLevel>(), new ArrayInitializerExpr());
    }

    public ArrayCreationExpr(TokenRange tokenRange, Type elementType, NodeList<ArrayCreationLevel> levels, ArrayInitializerExpr initializer) {
        super(tokenRange);
        this.setElementType(elementType);
        this.setLevels(levels);
        this.setInitializer(initializer);
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

    public Optional<ArrayInitializerExpr> getInitializer() {
        return Optional.ofNullable(this.initializer);
    }

    public Type getElementType() {
        return this.elementType;
    }

    public ArrayCreationExpr setInitializer(ArrayInitializerExpr initializer) {
        if (initializer == this.initializer) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.INITIALIZER, this.initializer, initializer);
        if (this.initializer != null) {
            this.initializer.setParentNode(null);
        }
        this.initializer = initializer;
        this.setAsParentNodeOf(initializer);
        return this;
    }

    public ArrayCreationExpr setElementType(Type elementType) {
        Utils.assertNotNull(elementType);
        if (elementType == this.elementType) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ELEMENT_TYPE, this.elementType, elementType);
        if (this.elementType != null) {
            this.elementType.setParentNode(null);
        }
        this.elementType = elementType;
        this.setAsParentNodeOf(elementType);
        return this;
    }

    public NodeList<ArrayCreationLevel> getLevels() {
        return this.levels;
    }

    public ArrayCreationExpr setLevels(NodeList<ArrayCreationLevel> levels) {
        Utils.assertNotNull(levels);
        if (levels == this.levels) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.LEVELS, this.levels, levels);
        if (this.levels != null) {
            this.levels.setParentNode(null);
        }
        this.levels = levels;
        this.setAsParentNodeOf(levels);
        return this;
    }

    public Type createdType() {
        Type result = this.elementType;
        for (int i = 0; i < this.levels.size(); ++i) {
            result = new ArrayType(result, ArrayType.Origin.TYPE, new NodeList<AnnotationExpr>());
        }
        return result;
    }

    public ArrayCreationExpr setElementType(Class<?> typeClass) {
        this.tryAddImportToParentCompilationUnit(typeClass);
        return this.setElementType(StaticJavaParser.parseType(typeClass.getSimpleName()));
    }

    public ArrayCreationExpr setElementType(String type) {
        return this.setElementType(StaticJavaParser.parseType(type));
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.initializer != null && node == this.initializer) {
            this.removeInitializer();
            return true;
        }
        for (int i = 0; i < this.levels.size(); ++i) {
            if (this.levels.get(i) != node) continue;
            this.levels.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public ArrayCreationExpr removeInitializer() {
        return this.setInitializer(null);
    }

    @Override
    public ArrayCreationExpr clone() {
        return (ArrayCreationExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ArrayCreationExprMetaModel getMetaModel() {
        return JavaParserMetaModel.arrayCreationExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.elementType) {
            this.setElementType((Type)replacementNode);
            return true;
        }
        if (this.initializer != null && node == this.initializer) {
            this.setInitializer((ArrayInitializerExpr)replacementNode);
            return true;
        }
        for (int i = 0; i < this.levels.size(); ++i) {
            if (this.levels.get(i) != node) continue;
            this.levels.set(i, (ArrayCreationLevel)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isArrayCreationExpr() {
        return true;
    }

    @Override
    public ArrayCreationExpr asArrayCreationExpr() {
        return this;
    }

    @Override
    public void ifArrayCreationExpr(Consumer<ArrayCreationExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<ArrayCreationExpr> toArrayCreationExpr() {
        return Optional.of(this);
    }
}

