/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalLabel;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ContinueStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import java.util.Optional;
import java.util.function.Consumer;

public class ContinueStmt
extends Statement
implements NodeWithOptionalLabel<ContinueStmt> {
    @OptionalProperty
    private SimpleName label;

    public ContinueStmt() {
        this(null, null);
    }

    public ContinueStmt(String label) {
        this(null, new SimpleName(label));
    }

    @AllFieldsConstructor
    public ContinueStmt(SimpleName label) {
        this(null, label);
    }

    public ContinueStmt(TokenRange tokenRange, SimpleName label) {
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

    @Override
    public Optional<SimpleName> getLabel() {
        return Optional.ofNullable(this.label);
    }

    @Override
    public ContinueStmt setLabel(SimpleName label) {
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

    @Override
    public ContinueStmt removeLabel() {
        return this.setLabel((SimpleName)null);
    }

    @Override
    public ContinueStmt clone() {
        return (ContinueStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ContinueStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.continueStmtMetaModel;
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
    public boolean isContinueStmt() {
        return true;
    }

    @Override
    public ContinueStmt asContinueStmt() {
        return this;
    }

    @Override
    public void ifContinueStmt(Consumer<ContinueStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<ContinueStmt> toContinueStmt() {
        return Optional.of(this);
    }
}

