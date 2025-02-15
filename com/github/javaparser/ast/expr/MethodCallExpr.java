/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalScope;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.MethodCallExprMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class MethodCallExpr
extends Expression
implements NodeWithTypeArguments<MethodCallExpr>,
NodeWithArguments<MethodCallExpr>,
NodeWithSimpleName<MethodCallExpr>,
NodeWithOptionalScope<MethodCallExpr>,
Resolvable<ResolvedMethodDeclaration> {
    @OptionalProperty
    private Expression scope;
    @OptionalProperty
    private NodeList<Type> typeArguments;
    private SimpleName name;
    private NodeList<Expression> arguments;

    public MethodCallExpr() {
        this(null, null, null, new SimpleName(), new NodeList<Expression>());
    }

    public MethodCallExpr(String name, Expression ... arguments) {
        this(null, null, null, new SimpleName(name), new NodeList((Node[])arguments));
    }

    public MethodCallExpr(Expression scope, String name) {
        this(null, scope, null, new SimpleName(name), new NodeList<Expression>());
    }

    public MethodCallExpr(Expression scope, SimpleName name) {
        this(null, scope, null, name, new NodeList<Expression>());
    }

    public MethodCallExpr(Expression scope, String name, NodeList<Expression> arguments) {
        this(null, scope, null, new SimpleName(name), arguments);
    }

    public MethodCallExpr(Expression scope, NodeList<Type> typeArguments, String name, NodeList<Expression> arguments) {
        this(null, scope, typeArguments, new SimpleName(name), arguments);
    }

    public MethodCallExpr(Expression scope, SimpleName name, NodeList<Expression> arguments) {
        this(null, scope, null, name, arguments);
    }

    @AllFieldsConstructor
    public MethodCallExpr(Expression scope, NodeList<Type> typeArguments, SimpleName name, NodeList<Expression> arguments) {
        this(null, scope, typeArguments, name, arguments);
    }

    public MethodCallExpr(TokenRange tokenRange, Expression scope, NodeList<Type> typeArguments, SimpleName name, NodeList<Expression> arguments) {
        super(tokenRange);
        this.setScope(scope);
        this.setTypeArguments((NodeList)typeArguments);
        this.setName(name);
        this.setArguments((NodeList)arguments);
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
    public NodeList<Expression> getArguments() {
        return this.arguments;
    }

    @Override
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public Optional<Expression> getScope() {
        return Optional.ofNullable(this.scope);
    }

    @Override
    public MethodCallExpr setArguments(NodeList<Expression> arguments) {
        Utils.assertNotNull(arguments);
        if (arguments == this.arguments) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ARGUMENTS, this.arguments, arguments);
        if (this.arguments != null) {
            this.arguments.setParentNode(null);
        }
        this.arguments = arguments;
        this.setAsParentNodeOf(arguments);
        return this;
    }

    @Override
    public MethodCallExpr setName(SimpleName name) {
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
    public MethodCallExpr setScope(Expression scope) {
        if (scope == this.scope) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.SCOPE, this.scope, scope);
        if (this.scope != null) {
            this.scope.setParentNode(null);
        }
        this.scope = scope;
        this.setAsParentNodeOf(scope);
        return this;
    }

    @Override
    public Optional<NodeList<Type>> getTypeArguments() {
        return Optional.ofNullable(this.typeArguments);
    }

    @Override
    public MethodCallExpr setTypeArguments(NodeList<Type> typeArguments) {
        if (typeArguments == this.typeArguments) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE_ARGUMENTS, this.typeArguments, typeArguments);
        if (this.typeArguments != null) {
            this.typeArguments.setParentNode(null);
        }
        this.typeArguments = typeArguments;
        this.setAsParentNodeOf(typeArguments);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.arguments.size(); ++i) {
            if (this.arguments.get(i) != node) continue;
            this.arguments.remove(i);
            return true;
        }
        if (this.scope != null && node == this.scope) {
            this.removeScope();
            return true;
        }
        if (this.typeArguments != null) {
            for (i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.remove(i);
                return true;
            }
        }
        return super.remove(node);
    }

    @Override
    public MethodCallExpr removeScope() {
        return this.setScope(null);
    }

    @Override
    public MethodCallExpr clone() {
        return (MethodCallExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public MethodCallExprMetaModel getMetaModel() {
        return JavaParserMetaModel.methodCallExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.arguments.size(); ++i) {
            if (this.arguments.get(i) != node) continue;
            this.arguments.set(i, (Expression)replacementNode);
            return true;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        if (this.scope != null && node == this.scope) {
            this.setScope((Expression)replacementNode);
            return true;
        }
        if (this.typeArguments != null) {
            for (i = 0; i < this.typeArguments.size(); ++i) {
                if (this.typeArguments.get(i) != node) continue;
                this.typeArguments.set(i, (Type)replacementNode);
                return true;
            }
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isMethodCallExpr() {
        return true;
    }

    @Override
    public MethodCallExpr asMethodCallExpr() {
        return this;
    }

    @Override
    public void ifMethodCallExpr(Consumer<MethodCallExpr> action) {
        action.accept(this);
    }

    @Override
    public ResolvedMethodDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedMethodDeclaration.class);
    }

    @Override
    public Optional<MethodCallExpr> toMethodCallExpr() {
        return Optional.of(this);
    }

    @Override
    public boolean isPolyExpression() {
        if (!this.appearsInAssignmentContext() && !this.appearsInInvocationContext()) {
            return false;
        }
        if (this.isQualified() && !this.elidesTypeArguments()) {
            return false;
        }
        return this.isGenericMethod() && this.hasParameterwithSameTypeThanResultType(this.resolve().getReturnType());
    }

    private boolean isGenericMethod() {
        return this.getTypeArguments().isPresent() && !this.getTypeArguments().get().isEmpty();
    }

    private boolean hasParameterwithSameTypeThanResultType(ResolvedType resolvedReturnType) {
        return this.getTypeArguments().isPresent() && this.getTypeArguments().get().stream().anyMatch(argType -> argType.resolve().isAssignableBy(resolvedReturnType));
    }

    @Override
    protected boolean isInvocationContext() {
        return true;
    }
}

