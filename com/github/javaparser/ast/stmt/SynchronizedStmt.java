/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithBlockStmt;
import com.github.javaparser.ast.nodeTypes.NodeWithExpression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.SynchronizedStmtMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class SynchronizedStmt
extends Statement
implements NodeWithBlockStmt<SynchronizedStmt>,
NodeWithExpression<SynchronizedStmt> {
    private Expression expression;
    private BlockStmt body;

    public SynchronizedStmt() {
        this(null, new NameExpr(), new BlockStmt());
    }

    @AllFieldsConstructor
    public SynchronizedStmt(Expression expression, BlockStmt body) {
        this(null, expression, body);
    }

    public SynchronizedStmt(TokenRange tokenRange, Expression expression, BlockStmt body) {
        super(tokenRange);
        this.setExpression(expression);
        this.setBody(body);
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
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public SynchronizedStmt setExpression(Expression expression) {
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

    @Override
    public BlockStmt getBody() {
        return this.body;
    }

    @Override
    public SynchronizedStmt setBody(BlockStmt body) {
        Utils.assertNotNull(body);
        if (body == this.body) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.BODY, this.body, body);
        if (this.body != null) {
            this.body.setParentNode(null);
        }
        this.body = body;
        this.setAsParentNodeOf(body);
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
    public SynchronizedStmt clone() {
        return (SynchronizedStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public SynchronizedStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.synchronizedStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.body) {
            this.setBody((BlockStmt)replacementNode);
            return true;
        }
        if (node == this.expression) {
            this.setExpression((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isSynchronizedStmt() {
        return true;
    }

    @Override
    public SynchronizedStmt asSynchronizedStmt() {
        return this;
    }

    @Override
    public void ifSynchronizedStmt(Consumer<SynchronizedStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<SynchronizedStmt> toSynchronizedStmt() {
        return Optional.of(this);
    }
}

