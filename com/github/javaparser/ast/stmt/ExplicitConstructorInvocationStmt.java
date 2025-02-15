/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeArguments;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ExplicitConstructorInvocationStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.resolution.Resolvable;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ExplicitConstructorInvocationStmt
extends Statement
implements NodeWithTypeArguments<ExplicitConstructorInvocationStmt>,
NodeWithArguments<ExplicitConstructorInvocationStmt>,
Resolvable<ResolvedConstructorDeclaration> {
    @OptionalProperty
    private NodeList<Type> typeArguments;
    private boolean isThis;
    @OptionalProperty
    private Expression expression;
    private NodeList<Expression> arguments;

    public ExplicitConstructorInvocationStmt() {
        this(null, null, true, null, new NodeList<Expression>());
    }

    public ExplicitConstructorInvocationStmt(boolean isThis, Expression expression, NodeList<Expression> arguments) {
        this(null, null, isThis, expression, arguments);
    }

    @AllFieldsConstructor
    public ExplicitConstructorInvocationStmt(NodeList<Type> typeArguments, boolean isThis, Expression expression, NodeList<Expression> arguments) {
        this(null, typeArguments, isThis, expression, arguments);
    }

    public ExplicitConstructorInvocationStmt(TokenRange tokenRange, NodeList<Type> typeArguments, boolean isThis, Expression expression, NodeList<Expression> arguments) {
        super(tokenRange);
        this.setTypeArguments((NodeList)typeArguments);
        this.setThis(isThis);
        this.setExpression(expression);
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

    public Optional<Expression> getExpression() {
        return Optional.ofNullable(this.expression);
    }

    public boolean isThis() {
        return this.isThis;
    }

    @Override
    public ExplicitConstructorInvocationStmt setArguments(NodeList<Expression> arguments) {
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

    public ExplicitConstructorInvocationStmt setExpression(Expression expression) {
        if (expression == this.expression) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.EXPRESSION, this.expression, expression);
        if (this.expression != null) {
            this.expression.setParentNode(null);
        }
        this.expression = expression;
        this.setAsParentNodeOf(expression);
        return this;
    }

    public ExplicitConstructorInvocationStmt setThis(boolean isThis) {
        if (isThis == this.isThis) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.THIS, this.isThis, isThis);
        this.isThis = isThis;
        return this;
    }

    @Override
    public Optional<NodeList<Type>> getTypeArguments() {
        return Optional.ofNullable(this.typeArguments);
    }

    @Override
    public ExplicitConstructorInvocationStmt setTypeArguments(NodeList<Type> typeArguments) {
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
        if (this.expression != null && node == this.expression) {
            this.removeExpression();
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

    public ExplicitConstructorInvocationStmt removeExpression() {
        return this.setExpression(null);
    }

    @Override
    public ExplicitConstructorInvocationStmt clone() {
        return (ExplicitConstructorInvocationStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ExplicitConstructorInvocationStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.explicitConstructorInvocationStmtMetaModel;
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
        if (this.expression != null && node == this.expression) {
            this.setExpression((Expression)replacementNode);
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
    public boolean isExplicitConstructorInvocationStmt() {
        return true;
    }

    @Override
    public ExplicitConstructorInvocationStmt asExplicitConstructorInvocationStmt() {
        return this;
    }

    @Override
    public void ifExplicitConstructorInvocationStmt(Consumer<ExplicitConstructorInvocationStmt> action) {
        action.accept(this);
    }

    @Override
    public ResolvedConstructorDeclaration resolve() {
        return this.getSymbolResolver().resolveDeclaration(this, ResolvedConstructorDeclaration.class);
    }

    @Override
    public Optional<ExplicitConstructorInvocationStmt> toExplicitConstructorInvocationStmt() {
        return Optional.of(this);
    }
}

