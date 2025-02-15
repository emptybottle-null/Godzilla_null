/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.AssignExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.printer.Stringable;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class AssignExpr
extends Expression {
    private Expression target;
    private Expression value;
    private Operator operator;

    public AssignExpr() {
        this(null, new NameExpr(), new StringLiteralExpr(), Operator.ASSIGN);
    }

    @AllFieldsConstructor
    public AssignExpr(Expression target, Expression value, Operator operator2) {
        this(null, target, value, operator2);
    }

    public AssignExpr(TokenRange tokenRange, Expression target, Expression value, Operator operator2) {
        super(tokenRange);
        this.setTarget(target);
        this.setValue(value);
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

    public Operator getOperator() {
        return this.operator;
    }

    public Expression getTarget() {
        return this.target;
    }

    public Expression getValue() {
        return this.value;
    }

    public AssignExpr setOperator(Operator operator2) {
        Utils.assertNotNull(operator2);
        if (operator2 == this.operator) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.OPERATOR, this.operator, operator2);
        this.operator = operator2;
        return this;
    }

    public AssignExpr setTarget(Expression target) {
        Utils.assertNotNull(target);
        if (target == this.target) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TARGET, this.target, target);
        if (this.target != null) {
            this.target.setParentNode(null);
        }
        this.target = target;
        this.setAsParentNodeOf(target);
        return this;
    }

    public AssignExpr setValue(Expression value) {
        Utils.assertNotNull(value);
        if (value == this.value) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VALUE, this.value, value);
        if (this.value != null) {
            this.value.setParentNode(null);
        }
        this.value = value;
        this.setAsParentNodeOf(value);
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
    public AssignExpr clone() {
        return (AssignExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public AssignExprMetaModel getMetaModel() {
        return JavaParserMetaModel.assignExprMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.target) {
            this.setTarget((Expression)replacementNode);
            return true;
        }
        if (node == this.value) {
            this.setValue((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isAssignExpr() {
        return true;
    }

    @Override
    public AssignExpr asAssignExpr() {
        return this;
    }

    @Override
    public void ifAssignExpr(Consumer<AssignExpr> action) {
        action.accept(this);
    }

    @Override
    public Optional<AssignExpr> toAssignExpr() {
        return Optional.of(this);
    }

    @Override
    protected boolean isAssignmentContext() {
        return true;
    }

    public static enum Operator implements Stringable
    {
        ASSIGN("="),
        PLUS("+="),
        MINUS("-="),
        MULTIPLY("*="),
        DIVIDE("/="),
        BINARY_AND("&="),
        BINARY_OR("|="),
        XOR("^="),
        REMAINDER("%="),
        LEFT_SHIFT("<<="),
        SIGNED_RIGHT_SHIFT(">>="),
        UNSIGNED_RIGHT_SHIFT(">>>=");

        private final String codeRepresentation;

        private Operator(String codeRepresentation) {
            this.codeRepresentation = codeRepresentation;
        }

        @Override
        public String asString() {
            return this.codeRepresentation;
        }

        public Optional<BinaryExpr.Operator> toBinaryOperator() {
            switch (this) {
                case PLUS: {
                    return Optional.of(BinaryExpr.Operator.PLUS);
                }
                case MINUS: {
                    return Optional.of(BinaryExpr.Operator.MINUS);
                }
                case MULTIPLY: {
                    return Optional.of(BinaryExpr.Operator.MULTIPLY);
                }
                case DIVIDE: {
                    return Optional.of(BinaryExpr.Operator.DIVIDE);
                }
                case BINARY_AND: {
                    return Optional.of(BinaryExpr.Operator.BINARY_AND);
                }
                case BINARY_OR: {
                    return Optional.of(BinaryExpr.Operator.BINARY_OR);
                }
                case XOR: {
                    return Optional.of(BinaryExpr.Operator.XOR);
                }
                case REMAINDER: {
                    return Optional.of(BinaryExpr.Operator.REMAINDER);
                }
                case LEFT_SHIFT: {
                    return Optional.of(BinaryExpr.Operator.LEFT_SHIFT);
                }
                case SIGNED_RIGHT_SHIFT: {
                    return Optional.of(BinaryExpr.Operator.SIGNED_RIGHT_SHIFT);
                }
                case UNSIGNED_RIGHT_SHIFT: {
                    return Optional.of(BinaryExpr.Operator.UNSIGNED_RIGHT_SHIFT);
                }
            }
            return Optional.empty();
        }
    }
}

