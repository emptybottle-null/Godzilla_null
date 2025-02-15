/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.metamodel.ReturnStmtMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class ReturnStmt
extends Statement {
    @OptionalProperty
    private Expression expression;

    public ReturnStmt() {
        this(null, null);
    }

    @AllFieldsConstructor
    public ReturnStmt(Expression expression) {
        this(null, expression);
    }

    public ReturnStmt(TokenRange tokenRange, Expression expression) {
        super(tokenRange);
        this.setExpression(expression);
        this.customInitialization();
    }

    public ReturnStmt(String expression) {
        this(null, new NameExpr(expression));
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Optional<Expression> getExpression() {
        return Optional.ofNullable(this.expression);
    }

    public ReturnStmt setExpression(Expression expression) {
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
        if (this.expression != null && node == this.expression) {
            this.removeExpression();
            return true;
        }
        return super.remove(node);
    }

    public ReturnStmt removeExpression() {
        return this.setExpression(null);
    }

    @Override
    public ReturnStmt clone() {
        return (ReturnStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ReturnStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.returnStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.expression != null && node == this.expression) {
            this.setExpression((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isReturnStmt() {
        return true;
    }

    @Override
    public ReturnStmt asReturnStmt() {
        return this;
    }

    @Override
    public void ifReturnStmt(Consumer<ReturnStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<ReturnStmt> toReturnStmt() {
        return Optional.of(this);
    }
}

