/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithExpression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ExpressionStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ExpressionStmt
extends Statement
implements NodeWithExpression<ExpressionStmt> {
    private Expression expression;

    public ExpressionStmt() {
        this(null, new BooleanLiteralExpr());
    }

    @AllFieldsConstructor
    public ExpressionStmt(Expression expression) {
        this(null, expression);
    }

    public ExpressionStmt(TokenRange tokenRange, Expression expression) {
        super(tokenRange);
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
    public ExpressionStmt setExpression(Expression expression) {
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public ExpressionStmt clone() {
        return (ExpressionStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ExpressionStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.expressionStmtMetaModel;
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
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isExpressionStmt() {
        return true;
    }

    @Override
    public ExpressionStmt asExpressionStmt() {
        return this;
    }

    @Override
    public void ifExpressionStmt(Consumer<ExpressionStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<ExpressionStmt> toExpressionStmt() {
        return Optional.of(this);
    }
}

