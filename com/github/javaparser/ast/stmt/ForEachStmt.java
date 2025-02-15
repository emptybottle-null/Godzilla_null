/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.stmt;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.ForEachStmtMetaModel;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.utils.Utils;
import java.util.Optional;
import java.util.function.Consumer;

public class ForEachStmt
extends Statement
implements NodeWithBody<ForEachStmt> {
    private VariableDeclarationExpr variable;
    private Expression iterable;
    private Statement body;

    public ForEachStmt() {
        this(null, new VariableDeclarationExpr(), new NameExpr(), new ReturnStmt());
    }

    @AllFieldsConstructor
    public ForEachStmt(VariableDeclarationExpr variable, Expression iterable, Statement body) {
        this(null, variable, iterable, body);
    }

    public ForEachStmt(TokenRange tokenRange, VariableDeclarationExpr variable, Expression iterable, Statement body) {
        super(tokenRange);
        this.setVariable(variable);
        this.setIterable(iterable);
        this.setBody(body);
        this.customInitialization();
    }

    public ForEachStmt(VariableDeclarationExpr variable, String iterable, BlockStmt body) {
        this(null, variable, new NameExpr(iterable), body);
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

    public Expression getIterable() {
        return this.iterable;
    }

    public VariableDeclarationExpr getVariable() {
        return this.variable;
    }

    @Override
    public ForEachStmt setBody(Statement body) {
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

    public ForEachStmt setIterable(Expression iterable) {
        Utils.assertNotNull(iterable);
        if (iterable == this.iterable) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.ITERABLE, this.iterable, iterable);
        if (this.iterable != null) {
            this.iterable.setParentNode(null);
        }
        this.iterable = iterable;
        this.setAsParentNodeOf(iterable);
        return this;
    }

    public ForEachStmt setVariable(VariableDeclarationExpr variable) {
        Utils.assertNotNull(variable);
        if (variable == this.variable) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.VARIABLE, this.variable, variable);
        if (this.variable != null) {
            this.variable.setParentNode(null);
        }
        this.variable = variable;
        this.setAsParentNodeOf(variable);
        return this;
    }

    public VariableDeclarator getVariableDeclarator() {
        return this.getVariable().getVariable(0);
    }

    public boolean hasFinalVariable() {
        return this.getVariable().getModifiers().isNonEmpty() && ((Modifier)this.getVariable().getModifiers().get(0)).getKeyword() == Modifier.Keyword.FINAL;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        return super.remove(node);
    }

    @Override
    public ForEachStmt clone() {
        return (ForEachStmt)this.accept(new CloneVisitor(), null);
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
        if (node == this.iterable) {
            this.setIterable((Expression)replacementNode);
            return true;
        }
        if (node == this.variable) {
            this.setVariable((VariableDeclarationExpr)replacementNode);
            return true;
        }
        return super.replace(node, replacementNode);
    }

    @Override
    public boolean isForEachStmt() {
        return true;
    }

    @Override
    public ForEachStmt asForEachStmt() {
        return this;
    }

    @Override
    public Optional<ForEachStmt> toForEachStmt() {
        return Optional.of(this);
    }

    @Override
    public void ifForEachStmt(Consumer<ForEachStmt> action) {
        action.accept(this);
    }

    @Override
    public ForEachStmtMetaModel getMetaModel() {
        return JavaParserMetaModel.forEachStmtMetaModel;
    }
}

