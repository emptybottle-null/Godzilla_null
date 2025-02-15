/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.nodeTypes.NodeWithStatements;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.BlockStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class BlockStmt
extends Statement
implements NodeWithStatements<BlockStmt> {
    private NodeList<Statement> statements;

    public BlockStmt() {
        this(null, new NodeList<Statement>());
    }

    @AllFieldsConstructor
    public BlockStmt(NodeList<Statement> statements) {
        this(null, statements);
    }

    public BlockStmt(TokenRange tokenRange, NodeList<Statement> statements) {
        super(tokenRange);
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

    @Override
    public NodeList<Statement> getStatements() {
        return this.statements;
    }

    @Override
    public BlockStmt setStatements(NodeList<Statement> statements) {
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
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.statements.size(); ++i) {
            if (this.statements.get(i) != node) continue;
            this.statements.remove(i);
            return true;
        }
        return super.remove(node);
    }

    @Override
    public BlockStmt clone() {
        return (BlockStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public BlockStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.blockStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < this.statements.size(); ++i) {
            if (this.statements.get(i) != node) continue;
            this.statements.set(i, (Statement)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isBlockStmt() {
        return true;
    }

    @Override
    public BlockStmt asBlockStmt() {
        return this;
    }

    @Override
    public void ifBlockStmt(Consumer<BlockStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<BlockStmt> toBlockStmt() {
        return Optional.of(this);
    }
}

