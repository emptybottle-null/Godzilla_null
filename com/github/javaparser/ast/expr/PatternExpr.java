/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.expr;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.PatternExprMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class PatternExpr
extends Expression
implements NodeWithSimpleName<PatternExpr>,
NodeWithType<PatternExpr, ReferenceType> {
    private SimpleName name;
    private ReferenceType type;

    public PatternExpr() {
        this(null, new ClassOrInterfaceType(), new SimpleName());
    }

    @AllFieldsConstructor
    public PatternExpr(ReferenceType type, SimpleName name) {
        this(null, type, name);
    }

    public PatternExpr(TokenRange tokenRange, ReferenceType type, SimpleName name) {
        super(tokenRange);
        this.setType(type);
        this.setName(name);
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
    public SimpleName getName() {
        return this.name;
    }

    @Override
    public ReferenceType getType() {
        return this.type;
    }

    @Override
    public PatternExpr setName(SimpleName name) {
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
    public PatternExpr setType(ReferenceType type) {
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
    public boolean isPatternExpr() {
        return true;
    }

    @Override
    public PatternExpr asPatternExpr() {
        return this;
    }

    @Override
    public Optional<PatternExpr> toPatternExpr() {
        return Optional.of(this);
    }

    @Override
    public void ifPatternExpr(Consumer<PatternExpr> action) {
        action.accept(this);
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.name) {
            this.setName((SimpleName)replacementNode);
            return true;
        }
        if (node == this.type) {
            this.setType((ReferenceType)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public PatternExpr clone() {
        return (PatternExpr)this.accept(new CloneVisitor(), null);
    }

    @Override
    public PatternExprMetaModel getMetaModel() {
        return JavaParserMetaModel.patternExprMetaModel;
    }
}

