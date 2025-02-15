/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.AssertStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class AssertStmt
extends Statement {
    private Expression check;
    @OptionalProperty
    private Expression message;

    public AssertStmt() {
        this(null, new BooleanLiteralExpr(), null);
    }

    public AssertStmt(Expression check) {
        this(null, check, null);
    }

    @AllFieldsConstructor
    public AssertStmt(Expression check, Expression message) {
        this(null, check, message);
    }

    public AssertStmt(TokenRange tokenRange, Expression check, Expression message) {
        super(tokenRange);
        this.setCheck(check);
        this.setMessage(message);
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

    public Expression getCheck() {
        return this.check;
    }

    public Optional<Expression> getMessage() {
        return Optional.ofNullable(this.message);
    }

    public AssertStmt setCheck(Expression check) {
        Utils.assertNotNull(check);
        if (check == this.check) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.CHECK, this.check, check);
        if (this.check != null) {
            this.check.setParentNode(null);
        }
        this.check = check;
        this.setAsParentNodeOf(check);
        return this;
    }

    public AssertStmt setMessage(Expression message) {
        if (message == this.message) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.MESSAGE, this.message, message);
        if (this.message != null) {
            this.message.setParentNode(null);
        }
        this.message = message;
        this.setAsParentNodeOf(message);
        return this;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.message != null && node == this.message) {
            this.removeMessage();
            return true;
        }
        return super.remove(node);
    }

    public AssertStmt removeMessage() {
        return this.setMessage(null);
    }

    @Override
    public AssertStmt clone() {
        return (AssertStmt)this.accept(new CloneVisitor(), null);
    }

    @Override
    public AssertStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.assertStmtMetaModel;
    }

    @Override
    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (node == this.check) {
            this.setCheck((Expression)replacementNode);
            return true;
        }
        if (this.message != null && node == this.message) {
            this.setMessage((Expression)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isAssertStmt() {
        return true;
    }

    @Override
    public AssertStmt asAssertStmt() {
        return this;
    }

    @Override
    public void ifAssertStmt(Consumer<AssertStmt> action) {
        action.accept(this);
    }

    @Override
    public Optional<AssertStmt> toAssertStmt() {
        return Optional.of(this);
    }
}

