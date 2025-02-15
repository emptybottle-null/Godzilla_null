/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithCondition;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ConditionalExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ConditionalExpr
extends Expression
implements NodeWithCondition<ConditionalExpr> {
    private Expression condition;
    private Expression thenExpr;
    private Expression elseExpr;

    public ConditionalExpr() {
        this(null, new BooleanLiteralExpr(), new StringLiteralExpr(), new StringLiteralExpr());
    }

    @AllFieldsConstructor
    public ConditionalExpr(Expression condition, Expression thenExpr, Expression elseExpr) {
        this(null, condition, thenExpr, elseExpr);
    }

    public ConditionalExpr(TokenRange tokenRange, Expression condition, Expression thenExpr, Expression elseExpr) {
        super(tokenRange);
        this.setCondition(condition);
        this.setThenExpr(thenExpr);
        this.setElseExpr(elseExpr);
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
    public Expression getCondition() {
        return this.condition;
    }

    public Expression getElseExpr() {
        return this.elseExpr;
    }

    public Expression getThenExpr() {
        return this.thenExpr;
    }

    @Override
    public ConditionalExpr setCondition(Expression condition) {
        Utils.assertNotNull(condition);
        if (condition == this.condition) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CONDITION, this.condition, condition);
        if (this.condition != null) {
            this.condition.setParentNode(null);
        }
        this.condition = condition;
        this.setAsParentNodeOf(condition);
        return this;
    }

    public ConditionalExpr setElseExpr(Expression elseExpr) {
        Utils.assertNotNull(elseExpr);
        if (elseExpr == this.elseExpr) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ELSE_EXPR, this.elseExpr, elseExpr);
        if (this.elseExpr != null) {
            this.elseExpr.setParentNode(null);
        }
        this.elseExpr = elseExpr;
        this.setAsParentNodeOf(elseExpr);
        return this;
    }

    public ConditionalExpr setThenExpr(Expression thenExpr) {
        Utils.assertNotNull(thenExpr);
        if (thenExpr == this.thenExpr) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.THEN_EXPR, this.thenExpr, thenExpr);
        if (this.thenExpr != null) {
            this.thenExpr.setParentNode(null);
        }
        this.thenExpr = thenExpr;
        this.setAsParentNodeOf(thenExpr);
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
    public ConditionalExpr clone() {
        return (ConditionalExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ConditionalExprMetaModel getMetaModel() {
        return JavaParserMetaModel.conditionalExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.condition) {
            this.setCondition((Expression)replacementNode);
            return true;
        }
        if (node == this.elseExpr) {
            this.setElseExpr((Expression)replacementNode);
            return true;
        }
        if (node == this.thenExpr) {
            this.setThenExpr((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isConditionalExpr() {
        return true;
    }

    @Override
    public ConditionalExpr asConditionalExpr() {
        return this;
    }

    @Override
    public void ifConditionalExpr(Consumer<ConditionalExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<ConditionalExpr> toConditionalExpr() {
        return Optional.of(this);
    }

    @Override
    public boolean isPolyExpression() {
        return this.appearsInAssignmentContext() || this.appearsInInvocationContext();
    }
}

