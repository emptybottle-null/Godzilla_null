/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.nodeTypes.NodeWithCondition;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.DerivedProperty;
import com.github.javaparser.metamodel.IfStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class IfStmt
extends Statement
implements NodeWithCondition<IfStmt> {
    private Expression condition;
    private Statement thenStmt;
    @OptionalProperty
    private Statement elseStmt;

    public IfStmt() {
        this(null, new BooleanLiteralExpr(), new ReturnStmt(), null);
    }

    @AllFieldsConstructor
    public IfStmt(Expression condition, Statement thenStmt, Statement elseStmt) {
        this(null, condition, thenStmt, elseStmt);
    }

    public IfStmt(TokenRange tokenRange, Expression condition, Statement thenStmt, Statement elseStmt) {
        super(tokenRange);
        this.setCondition(condition);
        this.setThenStmt(thenStmt);
        this.setElseStmt(elseStmt);
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
    public Expression getCondition() {
        return this.condition;
    }

    public Optional<Statement> getElseStmt() {
        return Optional.ofNullable(this.elseStmt);
    }

    public Statement getThenStmt() {
        return this.thenStmt;
    }

    @Override
    public IfStmt setCondition(Expression condition) {
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

    public IfStmt setElseStmt(Statement elseStmt) {
        if (elseStmt == this.elseStmt) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ELSE_STMT, this.elseStmt, elseStmt);
        if (this.elseStmt != null) {
            this.elseStmt.setParentNode(null);
        }
        this.elseStmt = elseStmt;
        this.setAsParentNodeOf(elseStmt);
        return this;
    }

    public IfStmt setThenStmt(Statement thenStmt) {
        Utils.assertNotNull(thenStmt);
        if (thenStmt == this.thenStmt) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.THEN_STMT, this.thenStmt, thenStmt);
        if (this.thenStmt != null) {
            this.thenStmt.setParentNode(null);
        }
        this.thenStmt = thenStmt;
        this.setAsParentNodeOf(thenStmt);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.elseStmt != null && node == this.elseStmt) {
            this.removeElseStmt();
            return true;
        }
        return super.remove(node);
    }

    public IfStmt removeElseStmt() {
        return this.setElseStmt(null);
    }

    @DerivedProperty
    public boolean hasThenBlock() {
        return this.thenStmt instanceof BlockStmt;
    }

    @DerivedProperty
    public boolean hasElseBlock() {
        return this.elseStmt instanceof BlockStmt;
    }

    @DerivedProperty
    public boolean hasCascadingIfStmt() {
        return this.elseStmt instanceof IfStmt;
    }

    @DerivedProperty
    public boolean hasElseBranch() {
        return this.elseStmt != null;
    }

    @Override
    public IfStmt clone() {
        return (IfStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public IfStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.ifStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.condition) {
            this.setCondition((Expression)replacementNode);
            return true;
        }
        if (this.elseStmt != null && node == this.elseStmt) {
            this.setElseStmt((Statement)replacementNode);
            return true;
        }
        if (node == this.thenStmt) {
            this.setThenStmt((Statement)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isIfStmt() {
        return true;
    }

    @Override
    public IfStmt asIfStmt() {
        return this;
    }

    @Override
    public void ifIfStmt(Consumer<IfStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<IfStmt> toIfStmt() {
        return Optional.of(this);
    }
}

