/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.PatternExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithExpression;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.InstanceOfExprMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class InstanceOfExpr
extends Expression
implements NodeWithType<InstanceOfExpr, ReferenceType>,
NodeWithExpression<InstanceOfExpr> {
    private Expression expression;
    @OptionalProperty
    private PatternExpr pattern;
    private ReferenceType type;

    public InstanceOfExpr() {
        this(null, new NameExpr(), new ClassOrInterfaceType(), null);
    }

    public InstanceOfExpr(Expression expression, ReferenceType type) {
        this(null, expression, type, null);
    }

    @AllFieldsConstructor
    public InstanceOfExpr(Expression expression, ReferenceType type, PatternExpr pattern) {
        this(null, expression, type, pattern);
    }

    public InstanceOfExpr(TokenRange tokenRange, Expression expression, ReferenceType type, PatternExpr pattern) {
        super(tokenRange);
        this.setExpression(expression);
        this.setType(type);
        this.setPattern(pattern);
        this.customInitialization();
    }

    public Optional<SimpleName> getName() {
        if (this.pattern == null) {
            return Optional.empty();
        }
        return Optional.of(this.pattern.getName());
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
    public InstanceOfExpr asInstanceOfExpr() {
        return this;
    }

    @Override
    public InstanceOfExpr clone() {
        return (InstanceOfExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public InstanceOfExprMetaModel getMetaModel() {
        return JavaParserMetaModel.instanceOfExprMetaModel;
    }

    public Optional<PatternExpr> getPattern() {
        return Optional.ofNullable(this.pattern);
    }

    @Override
    public ReferenceType getType() {
        return this.type;
    }

    @Override
    public void ifInstanceOfExpr(Consumer<InstanceOfExpr> action) {
        action.accept(this);
    }

    @Override
    public boolean isInstanceOfExpr() {
        return true;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.pattern != null && node == this.pattern) {
            this.removePattern();
            return true;
        }
        return super.remove(node);
    }

    public InstanceOfExpr removePattern() {
        return this.setPattern(null);
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
        if (this.pattern != null && node == this.pattern) {
            this.setPattern((PatternExpr)replacementNode);
            return true;
        }
        if (node == this.type) {
            this.setType((ReferenceType)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public InstanceOfExpr setExpression(Expression expression) {
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

    public InstanceOfExpr setPattern(PatternExpr pattern) {
        if (pattern == this.pattern) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.PATTERN, this.pattern, pattern);
        if (this.pattern != null) {
            this.pattern.setParentNode(null);
        }
        this.pattern = pattern;
        this.setAsParentNodeOf(pattern);
        return this;
    }

    @Override
    public InstanceOfExpr setType(ReferenceType type) {
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
    public Optional<InstanceOfExpr> toInstanceOfExpr() {
        return Optional.of(this);
    }
}

