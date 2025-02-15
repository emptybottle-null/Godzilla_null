/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithStatements;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.SwitchEntryMetaModel;
import com.github.javaparser.utils.Utils;

public class SwitchEntry
extends Node
implements NodeWithStatements<SwitchEntry> {
    private NodeList<Expression> labels;
    private NodeList<Statement> statements;
    private Type type;

    public SwitchEntry() {
        this(null, new NodeList<Expression>(), Type.STATEMENT_GROUP, new NodeList<Statement>());
    }

    @AllFieldsConstructor
    public SwitchEntry(NodeList<Expression> labels, Type type, NodeList<Statement> statements) {
        this(null, labels, type, statements);
    }

    public SwitchEntry(TokenRange tokenRange, NodeList<Expression> labels, Type type, NodeList<Statement> statements) {
        super(tokenRange);
        this.setLabels(labels);
        this.setType(type);
        this.setStatements((NodeList)statements);
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

    public NodeList<Expression> getLabels() {
        return this.labels;
    }

    @Override
    public NodeList<Statement> getStatements() {
        return this.statements;
    }

    public SwitchEntry setLabels(NodeList<Expression> labels) {
        Utils.assertNotNull(labels);
        if (labels == this.labels) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.LABELS, this.labels, labels);
        if (this.labels != null) {
            this.labels.setParentNode(null);
        }
        this.labels = labels;
        this.setAsParentNodeOf(labels);
        return this;
    }

    @Override
    public SwitchEntry setStatements(NodeList<Statement> statements) {
        Utils.assertNotNull(statements);
        if (statements == this.statements) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.STATEMENTS, this.statements, statements);
        if (this.statements != null) {
            this.statements.setParentNode(null);
        }
        this.statements = statements;
        this.setAsParentNodeOf(statements);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.labels.size(); ++i) {
            if (this.labels.get(i) != node) continue;
            this.labels.remove(i);
            return true;
        }
        for (i = 0; i < this.statements.size(); ++i) {
            if (this.statements.get(i) != node) continue;
            this.statements.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public SwitchEntry clone() {
        return (SwitchEntry)this.accept(new CloneVisitor(), null);
    }

    @Override
    public SwitchEntryMetaModel getMetaModel() {
        return JavaParserMetaModel.switchEntryMetaModel;
    }

    public Type getType() {
        return this.type;
    }

    public SwitchEntry setType(Type type) {
        Utils.assertNotNull(type);
        if (type == this.type) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
        this.type = type;
        return this;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.labels.size(); ++i) {
            if (this.labels.get(i) != node) continue;
            this.labels.set(i, (Expression)replacementNode);
            return true;
        }
        for (i = 0; i < this.statements.size(); ++i) {
            if (this.statements.get(i) != node) continue;
            this.statements.set(i, (Statement)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    public static enum Type {
        STATEMENT_GROUP,
        EXPRESSION,
        BLOCK,
        THROWS_STATEMENT;

    }
}

