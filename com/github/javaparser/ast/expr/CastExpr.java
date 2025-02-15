/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithExpression;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.CastExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class CastExpr
extends Expression
implements NodeWithType<CastExpr, Type>,
NodeWithExpression<CastExpr> {
    private Type type;
    private Expression expression;

    public CastExpr() {
        this(null, new ClassOrInterfaceType(), new NameExpr());
    }

    @AllFieldsConstructor
    public CastExpr(Type type, Expression expression) {
        this(null, type, expression);
    }

    public CastExpr(TokenRange tokenRange, Type type, Expression expression) {
        super(tokenRange);
        this.setType(type);
        this.setExpression(expression);
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
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public CastExpr setExpression(Expression expression) {
        Utils.assertNotNull(expression);
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

    @Override
    public CastExpr setType(Type type) {
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
        return super.remove(node);
    }

    @Override
    public CastExpr clone() {
        return (CastExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public CastExprMetaModel getMetaModel() {
        return JavaParserMetaModel.castExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.expression) {
            this.setExpression((Expression)replacementNode);
            return true;
        }
        if (node == this.type) {
            this.setType((Type)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isCastExpr() {
        return true;
    }

    @Override
    public CastExpr asCastExpr() {
        return this;
    }

    @Override
    public void ifCastExpr(Consumer<CastExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<CastExpr> toCastExpr() {
        return Optional.of(this);
    }
}

