/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.BinaryExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.printer.Stringable;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class BinaryExpr
extends Expression {
    private Expression left;
    private Expression right;
    private Operator operator;

    public BinaryExpr() {
        this(null, new BooleanLiteralExpr(), new BooleanLiteralExpr(), Operator.EQUALS);
    }

    @AllFieldsConstructor
    public BinaryExpr(Expression left, Expression right, Operator operator2) {
        this(null, left, right, operator2);
    }

    public BinaryExpr(TokenRange tokenRange, Expression left, Expression right, Operator operator2) {
        super(tokenRange);
        this.setLeft(left);
        this.setRight(right);
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

    public Expression getLeft() {
        return this.left;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public Expression getRight() {
        return this.right;
    }

    public BinaryExpr setLeft(Expression left) {
        Utils.assertNotNull(left);
        if (left == this.left) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.LEFT, this.left, left);
        if (this.left != null) {
            this.left.setParentNode(null);
        }
        this.left = left;
        this.setAsParentNodeOf(left);
        return this;
    }

    public BinaryExpr setOperator(Operator operator2) {
        Utils.assertNotNull(operator2);
        if (operator2 == this.operator) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.OPERATOR, this.operator, operator2);
        this.operator = operator2;
        return this;
    }

    public BinaryExpr setRight(Expression right) {
        Utils.assertNotNull(right);
        if (right == this.right) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.RIGHT, this.right, right);
        if (this.right != null) {
            this.right.setParentNode(null);
        }
        this.right = right;
        this.setAsParentNodeOf(right);
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
    public BinaryExpr clone() {
        return (BinaryExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public BinaryExprMetaModel getMetaModel() {
        return JavaParserMetaModel.binaryExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.left) {
            this.setLeft((Expression)replacementNode);
            return true;
        }
        if (node == this.right) {
            this.setRight((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isBinaryExpr() {
        return true;
    }

    @Override
    public BinaryExpr asBinaryExpr() {
        return this;
    }

    @Override
    public void ifBinaryExpr(Consumer<BinaryExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<BinaryExpr> toBinaryExpr() {
        return Optional.of(this);
    }

    public static enum Operator implements Stringable
    {
        OR("||"),
        AND("&&"),
        BINARY_OR("|"),
        BINARY_AND("&"),
        XOR("^"),
        EQUALS("=="),
        NOT_EQUALS("!="),
        LESS("<"),
        GREATER(">"),
        LESS_EQUALS("<="),
        GREATER_EQUALS(">="),
        LEFT_SHIFT("<<"),
        SIGNED_RIGHT_SHIFT(">>"),
        UNSIGNED_RIGHT_SHIFT(">>>"),
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        REMAINDER("%");

        private final String codeRepresentation;

        private Operator(String codeRepresentation) {
            this.codeRepresentation = codeRepresentation;
        }

        @Override
        public String asString() {
            return this.codeRepresentation;
        }

        public Optional<AssignExpr.Operator> toAssignOperator() {
            switch (this) {
                case BINARY_OR: {
                    return Optional.of(AssignExpr.Operator.BINARY_OR);
                }
                case BINARY_AND: {
                    return Optional.of(AssignExpr.Operator.BINARY_AND);
                }
                case XOR: {
                    return Optional.of(AssignExpr.Operator.XOR);
                }
                case LEFT_SHIFT: {
                    return Optional.of(AssignExpr.Operator.LEFT_SHIFT);
                }
                case SIGNED_RIGHT_SHIFT: {
                    return Optional.of(AssignExpr.Operator.SIGNED_RIGHT_SHIFT);
                }
                case UNSIGNED_RIGHT_SHIFT: {
                    return Optional.of(AssignExpr.Operator.UNSIGNED_RIGHT_SHIFT);
                }
                case PLUS: {
                    return Optional.of(AssignExpr.Operator.PLUS);
                }
                case MINUS: {
                    return Optional.of(AssignExpr.Operator.MINUS);
                }
                case MULTIPLY: {
                    return Optional.of(AssignExpr.Operator.MULTIPLY);
                }
                case DIVIDE: {
                    return Optional.of(AssignExpr.Operator.DIVIDE);
                }
                case REMAINDER: {
                    return Optional.of(AssignExpr.Operator.REMAINDER);
                }
            }
            return Optional.empty();
        }
    }
}

