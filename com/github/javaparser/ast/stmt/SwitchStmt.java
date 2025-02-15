/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.SwitchNode;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.SwitchStmtMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class SwitchStmt
extends Statement
implements SwitchNode {
    private Expression selector;
    private NodeList<SwitchEntry> entries;

    public SwitchStmt() {
        this(null, new NameExpr(), new NodeList<SwitchEntry>());
    }

    @AllFieldsConstructor
    public SwitchStmt(Expression selector, NodeList<SwitchEntry> entries) {
        this(null, selector, entries);
    }

    public SwitchStmt(TokenRange tokenRange, Expression selector, NodeList<SwitchEntry> entries) {
        super(tokenRange);
        this.setSelector(selector);
        this.setEntries((NodeList)entries);
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
    public NodeList<SwitchEntry> getEntries() {
        return this.entries;
    }

    @Override
    public SwitchEntry getEntry(int i) {
        return (SwitchEntry)this.getEntries().get(i);
    }

    @Override
    public Expression getSelector() {
        return this.selector;
    }

    @Override
    public SwitchStmt setEntries(NodeList<SwitchEntry> entries) {
        Utils.assertNotNull(entries);
        if (entries == this.entries) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ENTRIES, this.entries, entries);
        if (this.entries != null) {
            this.entries.setParentNode(null);
        }
        this.entries = entries;
        this.setAsParentNodeOf(entries);
        return this;
    }

    @Override
    public SwitchStmt setSelector(Expression selector) {
        Utils.assertNotNull(selector);
        if (selector == this.selector) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.SELECTOR, this.selector, selector);
        if (this.selector != null) {
            this.selector.setParentNode(null);
        }
        this.selector = selector;
        this.setAsParentNodeOf(selector);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.entries.size(); ++i) {
            if (this.entries.get(i) != node) continue;
            this.entries.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public SwitchStmt clone() {
        return (SwitchStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public SwitchStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.switchStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.entries.size(); ++i) {
            if (this.entries.get(i) != node) continue;
            this.entries.set(i, (SwitchEntry)replacementNode);
            return true;
        }
        if (node == this.selector) {
            this.setSelector((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isSwitchStmt() {
        return true;
    }

    @Override
    public SwitchStmt asSwitchStmt() {
        return this;
    }

    @Override
    public void ifSwitchStmt(Consumer<SwitchStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<SwitchStmt> toSwitchStmt() {
        return Optional.of(this);
    }
}

