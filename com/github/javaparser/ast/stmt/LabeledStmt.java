/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.LabeledStmtMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class LabeledStmt
extends Statement {
    private SimpleName label;
    private Statement statement;

    public LabeledStmt() {
        this(null, new SimpleName(), new ReturnStmt());
    }

    public LabeledStmt(String label, Statement statement) {
        this(null, new SimpleName(label), statement);
    }

    @AllFieldsConstructor
    public LabeledStmt(SimpleName label, Statement statement) {
        this(null, label, statement);
    }

    public LabeledStmt(TokenRange tokenRange, SimpleName label, Statement statement) {
        super(tokenRange);
        this.setLabel(label);
        this.setStatement(statement);
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

    public Statement getStatement() {
        return this.statement;
    }

    public LabeledStmt setStatement(Statement statement) {
        Utils.assertNotNull(statement);
        if (statement == this.statement) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.STATEMENT, this.statement, statement);
        if (this.statement != null) {
            this.statement.setParentNode(null);
        }
        this.statement = statement;
        this.setAsParentNodeOf(statement);
        return this;
    }

    public SimpleName getLabel() {
        return this.label;
    }

    public LabeledStmt setLabel(SimpleName label) {
        Utils.assertNotNull(label);
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
        return super.remove(node);
    }

    @Override
    public LabeledStmt clone() {
        return (LabeledStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public LabeledStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.labeledStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.label) {
            this.setLabel((SimpleName)replacementNode);
            return true;
        }
        if (node == this.statement) {
            this.setStatement((Statement)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isLabeledStmt() {
        return true;
    }

    @Override
    public LabeledStmt asLabeledStmt() {
        return this;
    }

    @Override
    public void ifLabeledStmt(Consumer<LabeledStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<LabeledStmt> toLabeledStmt() {
        return Optional.of(this);
    }
}

