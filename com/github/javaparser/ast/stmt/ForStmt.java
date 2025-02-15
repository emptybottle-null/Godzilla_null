/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ForStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ForStmt
extends Statement
implements NodeWithBody<ForStmt> {
    private NodeList<Expression> initialization;
    @OptionalProperty
    private Expression compare;
    private NodeList<Expression> update;
    private Statement body;

    public ForStmt() {
        this(null, new NodeList<Expression>(), new BooleanLiteralExpr(), new NodeList<Expression>(), new ReturnStmt());
    }

    @AllFieldsConstructor
    public ForStmt(NodeList<Expression> initialization, Expression compare, NodeList<Expression> update, Statement body) {
        this(null, initialization, compare, update, body);
    }

    public ForStmt(TokenRange tokenRange, NodeList<Expression> initialization, Expression compare, NodeList<Expression> update, Statement body) {
        super(tokenRange);
        this.setInitialization(initialization);
        this.setCompare(compare);
        this.setUpdate(update);
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

    public Optional<Expression> getCompare() {
        return Optional.ofNullable(this.compare);
    }

    public NodeList<Expression> getInitialization() {
        return this.initialization;
    }

    public NodeList<Expression> getUpdate() {
        return this.update;
    }

    @Override
    public ForStmt setBody(Statement body) {
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

    public ForStmt setCompare(Expression compare) {
        if (compare == this.compare) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.COMPARE, this.compare, compare);
        if (this.compare != null) {
            this.compare.setParentNode(null);
        }
        this.compare = compare;
        this.setAsParentNodeOf(compare);
        return this;
    }

    public ForStmt setInitialization(NodeList<Expression> initialization) {
        Utils.assertNotNull(initialization);
        if (initialization == this.initialization) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.INITIALIZATION, this.initialization, initialization);
        if (this.initialization != null) {
            this.initialization.setParentNode(null);
        }
        this.initialization = initialization;
        this.setAsParentNodeOf(initialization);
        return this;
    }

    public ForStmt setUpdate(NodeList<Expression> update) {
        Utils.assertNotNull(update);
        if (update == this.update) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.UPDATE, this.update, update);
        if (this.update != null) {
            this.update.setParentNode(null);
        }
        this.update = update;
        this.setAsParentNodeOf(update);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        int i;
        if (node == null) {
            return false;
        }
        if (this.compare != null && node == this.compare) {
            this.removeCompare();
            return true;
        }
        for (i = 0; i < this.initialization.size(); ++i) {
            if (this.initialization.get(i) != node) continue;
            this.initialization.remove(i);
            return true;
        }
        for (i = 0; i < this.update.size(); ++i) {
            if (this.update.get(i) != node) continue;
            this.update.remove(i);
            return true;
        }
        return super.remove(node);
    }

    public ForStmt removeCompare() {
        return this.setCompare(null);
    }

    @Override
    public ForStmt clone() {
        return (ForStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public ForStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.forStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        int i;
        if (node == null) {
            return false;
        }
        if (node == this.body) {
            this.setBody((Statement)replacementNode);
            return true;
        }
        if (this.compare != null && node == this.compare) {
            this.setCompare((Expression)replacementNode);
            return true;
        }
        for (i = 0; i < this.initialization.size(); ++i) {
            if (this.initialization.get(i) != node) continue;
            this.initialization.set(i, (Expression)replacementNode);
            return true;
        }
        for (i = 0; i < this.update.size(); ++i) {
            if (this.update.get(i) != node) continue;
            this.update.set(i, (Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isForStmt() {
        return true;
    }

    @Override
    public ForStmt asForStmt() {
        return this;
    }

    @Override
    public void ifForStmt(Consumer<ForStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<ForStmt> toForStmt() {
        return Optional.of(this);
    }
}

