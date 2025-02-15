/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.UnparsableStmtMetaModel;
import java.util.Optional;
import java.util.function.Consumer;

public class UnparsableStmt
extends Statement {
    @AllFieldsConstructor
    public UnparsableStmt() {
        this(null);
    }

    public UnparsableStmt(TokenRange tokenRange) {
        super(tokenRange);
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
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public UnparsableStmt clone() {
        return (UnparsableStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public UnparsableStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.unparsableStmtMetaModel;
    }

    @Override
    public Node.Parsedness getParsed() {
        return Node.Parsedness.UNPARSABLE;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isUnparsableStmt() {
        return true;
    }

    @Override
    public UnparsableStmt asUnparsableStmt() {
        return this;
    }

    @Override
    public void ifUnparsableStmt(Consumer<UnparsableStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<UnparsableStmt> toUnparsableStmt() {
        return Optional.of(this);
    }
}

