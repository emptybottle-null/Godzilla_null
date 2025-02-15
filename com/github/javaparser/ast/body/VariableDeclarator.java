/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.body;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.nodeTypes.NodeWithVariables;
import com.github.javaparser.ast.observer.AstObserverAdapter;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NonEmptyProperty;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.metamodel.VariableDeclaratorMetaModel;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.LinkedList;
import java.util.Optional;

public class VariableDeclarator
extends Node
implements NodeWithType<VariableDeclarator, Type>,
NodeWithSimpleName<VariableDeclarator>,
Resolvable<ResolvedValueDeclaration> {
    private SimpleName name;
    @OptionalProperty
    @NonEmptyProperty
    private Expression initializer;
    private Type type;

    public VariableDeclarator() {
        this(null, new ClassOrInterfaceType(), new SimpleName(), null);
    }

    public VariableDeclarator(Type type, String variableName) {
        this(null, type, new SimpleName(variableName), null);
    }

    public VariableDeclarator(Type type, SimpleName name) {
        this(null, type, name, null);
    }

    public VariableDeclarator(Type type, String variableName, Expression initializer) {
        this(null, type, new SimpleName(variableName), initializer);
    }

    @AllFieldsConstructor
    public VariableDeclarator(Type type, SimpleName name, Expression initializer) {
        this(null, type, name, initializer);
    }

    public VariableDeclarator(TokenRange tokenRange, Type type, SimpleName name, Expression initializer) {
        super(tokenRange);
        this.setType(type);
        this.setName(name);
        this.setInitializer(initializer);
        this.customInitialization();
    }

    @Override
    protected void customInitialization() {
        this.register(new AstObserverAdapter(){

            @Override
            public void propertyChange(Node observedNode, ObservableProperty property, Object oldValue, Object newValue) {
                VariableDeclarator vd;
                if (property == ObservableProperty.TYPE && (vd = VariableDeclarator.this).getParentNode().isPresent() && vd.getParentNode().get() instanceof NodeWithVariables) {
                    NodeWithVariables nodeWithVariables = (NodeWithVariables)((Object)vd.getParentNode().get());
                    Optional<Type> currentMaxCommonType = nodeWithVariables.getMaximumCommonType();
                    LinkedList<Type> types = new LinkedList<Type>();
                    int index = nodeWithVariables.getVariables().indexOf(vd);
                    for (int i = 0; i < nodeWithVariables.getVariables().size(); ++i) {
                        if (i == index) {
                            types.add((Type)newValue);
                            continue;
                        }
                        types.add(nodeWithVariables.getVariable(i).getType());
                    }
                    Optional<Type> newMaxCommonType = NodeWithVariables.calculateMaximumCommonType(types);
                    ((Node)((Object)nodeWithVariables)).notifyPropertyChange(ObservableProperty.MAXIMUM_COMMON_TYPE, currentMaxCommonType.orElse(null), newMaxCommonType.orElse(null));
                }
            }
        });
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Optional<Expression> getInitializer() {
        return Optional.ofNullable(this.initializer);
    }

    @Override
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public VariableDeclarator setName(SimpleName name) {
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

    public VariableDeclarator setInitializer(Expression initializer) {
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

    public VariableDeclarator setInitializer(String init) {
        return this.setInitializer(new NameExpr(Utils.assertNonEmpty(init)));
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public VariableDeclarator setType(Type type) {
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
        if (this.initializer != null && node == this.initializer) {
            this.removeInitializer();
            return true;
        }
        return super.remove(node);
    }

    public VariableDeclarator removeInitializer() {
        return this.setInitializer((Expression)null);
    }

    @Override
    public VariableDeclarator clone() {
        return (VariableDeclarator)this.accept(new CloneVisitor(), null);
    }

    @Override
    public VariableDeclaratorMetaModel getMetaModel() {
        return JavaParserMetaModel.variableDeclaratorMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.initializer != null && node == this.initializer) {
            this.setInitializer((Expression)replacementNode);
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
    public ResolvedValueDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedValueDeclaration.class);
    }
}

