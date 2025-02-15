/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.metamodel.TryStmtMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class TryStmt
extends Statement {
    private NodeList<Expression> resources;
    private BlockStmt tryBlock;
    private NodeList<CatchClause> catchClauses;
    @OptionalProperty
    private BlockStmt finallyBlock;

    public TryStmt() {
        this(null, new NodeList<Expression>(), new BlockStmt(), new NodeList<CatchClause>(), null);
    }

    public TryStmt(BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock) {
        this(null, new NodeList<Expression>(), tryBlock, catchClauses, finallyBlock);
    }

    @AllFieldsConstructor
    public TryStmt(NodeList<Expression> resources, BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock) {
        this(null, resources, tryBlock, catchClauses, finallyBlock);
    }

    public TryStmt(TokenRange tokenRange, NodeList<Expression> resources, BlockStmt tryBlock, NodeList<CatchClause> catchClauses, BlockStmt finallyBlock) {
        super(tokenRange);
        this.setResources(resources);
        this.setTryBlock(tryBlock);
        this.setCatchClauses(catchClauses);
        this.setFinallyBlock(finallyBlock);
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

    public NodeList<CatchClause> getCatchClauses() {
        return this.catchClauses;
    }

    public Optional<BlockStmt> getFinallyBlock() {
        return Optional.ofNullable(this.finallyBlock);
    }

    public BlockStmt getTryBlock() {
        return this.tryBlock;
    }

    public NodeList<Expression> getResources() {
        return this.resources;
    }

    public TryStmt setCatchClauses(NodeList<CatchClause> catchClauses) {
        Utils.assertNotNull(catchClauses);
        if (catchClauses == this.catchClauses) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CATCH_CLAUSES, this.catchClauses, catchClauses);
        if (this.catchClauses != null) {
            this.catchClauses.setParentNode(null);
        }
        this.catchClauses = catchClauses;
        this.setAsParentNodeOf(catchClauses);
        return this;
    }

    public TryStmt setFinallyBlock(BlockStmt finallyBlock) {
        if (finallyBlock == this.finallyBlock) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.FINALLY_BLOCK, this.finallyBlock, finallyBlock);
        if (this.finallyBlock != null) {
            this.finallyBlock.setParentNode(null);
        }
        this.finallyBlock = finallyBlock;
        this.setAsParentNodeOf(finallyBlock);
        return this;
    }

    public TryStmt setTryBlock(BlockStmt tryBlock) {
        Utils.assertNotNull(tryBlock);
        if (tryBlock == this.tryBlock) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.TRY_BLOCK, this.tryBlock, tryBlock);
        if (this.tryBlock != null) {
            this.tryBlock.setParentNode(null);
        }
        this.tryBlock = tryBlock;
        this.setAsParentNodeOf(tryBlock);
        return this;
    }

    public TryStmt setResources(NodeList<Expression> resources) {
        Utils.assertNotNull(resources);
        if (resources == this.resources) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.RESOURCES, this.resources, resources);
        if (this.resources != null) {
            this.resources.setParentNode(null);
        }
        this.resources = resources;
        this.setAsParentNodeOf(resources);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.catchClauses.size(); ++i) {
            if (this.catchClauses.get(i) != node) continue;
            this.catchClauses.remove(i);
            return true;
        }
        if (this.finallyBlock != null && node == this.finallyBlock) {
            this.removeFinallyBlock();
            return true;
        }
        for (i = 0; i < this.resources.size(); ++i) {
            if (this.resources.get(i) != node) continue;
            this.resources.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public TryStmt removeFinallyBlock() {
        return this.setFinallyBlock(null);
    }

    @Override
    public TryStmt clone() {
        return (TryStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public TryStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.tryStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        for (i = 0; i < this.catchClauses.size(); ++i) {
            if (this.catchClauses.get(i) != node) continue;
            this.catchClauses.set(i, (CatchClause)replacementNode);
            return true;
        }
        if (this.finallyBlock != null && node == this.finallyBlock) {
            this.setFinallyBlock((BlockStmt)replacementNode);
            return true;
        }
        for (i = 0; i < this.resources.size(); ++i) {
            if (this.resources.get(i) != node) continue;
            this.resources.set(i, (Expression)replacementNode);
            return true;
        }
        if (node == this.tryBlock) {
            this.setTryBlock((BlockStmt)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isTryStmt() {
        return true;
    }

    @Override
    public TryStmt asTryStmt() {
        return this;
    }

    @Override
    public void ifTryStmt(Consumer<TryStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<TryStmt> toTryStmt() {
        return Optional.of(this);
    }
}

