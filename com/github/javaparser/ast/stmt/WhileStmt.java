/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.nodeTypes.NodeWithCondition;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.WhileStmtMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class WhileStmt
extends Statement
implements NodeWithBody<WhileStmt>,
NodeWithCondition<WhileStmt> {
    private Expression condition;
    private Statement body;

    public WhileStmt() {
        this(null, new BooleanLiteralExpr(), new ReturnStmt());
    }

    @AllFieldsConstructor
    public WhileStmt(Expression condition, Statement body) {
        this(null, condition, body);
    }

    public WhileStmt(TokenRange tokenRange, Expression condition, Statement body) {
        super(tokenRange);
        this.setCondition(condition);
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
    public Statement getBody() {
        return this.body;
    }

    @Override
    public Expression getCondition() {
        return this.condition;
    }

    @Override
    public WhileStmt setBody(Statement body) {
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
    public WhileStmt setCondition(Expression condition) {
        Utils.assertNotNull(condition);
        if (condition == this.condition) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CONDITION, this.condition, condition);
        if (this.condition != null) {
            this.condition.setParentNode(null);
        }
        this.condition = condition;
        this.setAsParentNodeOf(condition);
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
    public WhileStmt clone() {
        return (WhileStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public WhileStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.whileStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.body) {
            this.setBody((Statement)replacementNode);
            return true;
        }
        if (node == this.condition) {
            this.setCondition((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isWhileStmt() {
        return true;
    }

    @Override
    public WhileStmt asWhileStmt() {
        return this;
    }

    @Override
    public void ifWhileStmt(Consumer<WhileStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<WhileStmt> toWhileStmt() {
        return Optional.of(this);
    }
}

