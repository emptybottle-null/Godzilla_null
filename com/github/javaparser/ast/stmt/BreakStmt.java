/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.BreakStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import java.util.Optional;
import java.util.function.Consumer;

public class BreakStmt
extends Statement {
    @OptionalProperty
    private SimpleName label;

    public BreakStmt() {
        this(null, new SimpleName());
    }

    public BreakStmt(String label) {
        this(null, new SimpleName(label));
    }

    @AllFieldsConstructor
    public BreakStmt(SimpleName label) {
        this(null, label);
    }

    public BreakStmt(TokenRange tokenRange, SimpleName label) {
        super(tokenRange);
        this.setLabel(label);
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

    public Optional<SimpleName> getLabel() {
        return Optional.ofNullable(this.label);
    }

    public BreakStmt setLabel(SimpleName label) {
        if (label == this.label) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.LABEL, this.label, label);
        if (this.label != null) {
            this.label.setParentNode(null);
        }
        this.label = label;
        this.setAsParentNodeOf(label);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.label != null && node == this.label) {
            this.removeLabel();
            return true;
        }
        return super.remove(node);
    }

    public BreakStmt removeLabel() {
        return this.setLabel(null);
    }

    @Override
    public BreakStmt clone() {
        return (BreakStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public BreakStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.breakStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.label != null && node == this.label) {
            this.setLabel((SimpleName)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isBreakStmt() {
        return true;
    }

    @Override
    public BreakStmt asBreakStmt() {
        return this;
    }

    @Override
    public void ifBreakStmt(Consumer<BreakStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<BreakStmt> toBreakStmt() {
        return Optional.of(this);
    }
}

