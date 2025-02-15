/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithExpression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.DerivedProperty;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.UnaryExprMetaModel;
import com.github.javaparser.printer.Stringable;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class UnaryExpr
extends Expression
implements NodeWithExpression<UnaryExpr> {
    private Expression expression;
    private Operator operator;

    public UnaryExpr() {
        this(null, new IntegerLiteralExpr(), Operator.POSTFIX_INCREMENT);
    }

    @AllFieldsConstructor
    public UnaryExpr(Expression expression, Operator operator2) {
        this(null, expression, operator2);
    }

    public UnaryExpr(TokenRange tokenRange, Expression expression, Operator operator2) {
        super(tokenRange);
        this.setExpression(expression);
        this.setOperator(operator2);
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

    public Operator getOperator() {
        return this.operator;
    }

    @Override
    public UnaryExpr setExpression(Expression expression) {
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

    public UnaryExpr setOperator(Operator operator2) {
        Utils.assertNotNull(operator2);
        if (operator2 == this.operator) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.OPERATOR, this.operator, operator2);
        this.operator = operator2;
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @DerivedProperty
    public boolean isPostfix() {
        return this.operator.isPostfix();
    }

    @DerivedProperty
    public boolean isPrefix() {
        return !this.isPostfix();
    }

    @Override
    public UnaryExpr clone() {
        return (UnaryExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public UnaryExprMetaModel getMetaModel() {
        return JavaParserMetaModel.unaryExprMetaModel;
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
    public boolean isUnaryExpr() {
        return true;
    }

    @Override
    public UnaryExpr asUnaryExpr() {
        return this;
    }

    @Override
    public void ifUnaryExpr(Consumer<UnaryExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<UnaryExpr> toUnaryExpr() {
        return Optional.of(this);
    }

    public static enum Operator implements Stringable
    {
        PLUS("+", false),
        MINUS("-", false),
        PREFIX_INCREMENT("++", false),
        PREFIX_DECREMENT("--", false),
        LOGICAL_COMPLEMENT("!", false),
        BITWISE_COMPLEMENT("~", false),
        POSTFIX_INCREMENT("++", true),
        POSTFIX_DECREMENT("--", true);

        private final String codeRepresentation;
        private final boolean isPostfix;

        private Operator(String codeRepresentation, boolean isPostfix) {
            this.codeRepresentation = codeRepresentation;
            this.isPostfix = isPostfix;
        }

        @Override
        public String asString() {
            return this.codeRepresentation;
        }

        public boolean isPostfix() {
            return this.isPostfix;
        }

        public boolean isPrefix() {
            return !this.isPostfix();
        }
    }
}

