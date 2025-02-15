/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;

public interface NodeWithStatements<N extends Node> {
    public NodeList<Statement> getStatements();

    default public Statement getStatement(int i) {
        return (Statement)this.getStatements().get(i);
    }

    default public N setStatement(int i, Statement statement) {
        this.getStatements().set(i, statement);
        return (N)((Node)((Object)this));
    }

    public N setStatements(NodeList<Statement> var1);

    default public N addStatement(Statement statement) {
        this.getStatements().add(statement);
        return (N)((Node)((Object)this));
    }

    default public N addStatement(int index, Statement statement) {
        this.getStatements().add(index, statement);
        return (N)((Node)((Object)this));
    }

    default public N addStatement(Expression expr) {
        return this.addStatement(new ExpressionStmt(expr));
    }

    default public N addStatement(String statement) {
        return this.addStatement(StaticJavaParser.parseStatement(statement));
    }

    default public N addStatement(int index, Expression expr) {
        ExpressionStmt stmt = new ExpressionStmt(expr);
        return this.addStatement(index, stmt);
    }

    default public <A extends Statement> A addAndGetStatement(A statement) {
        this.getStatements().add(statement);
        return statement;
    }

    default public Statement addAndGetStatement(int index, Statement statement) {
        this.getStatements().add(index, statement);
        return statement;
    }

    default public ExpressionStmt addAndGetStatement(Expression expr) {
        ExpressionStmt statement = new ExpressionStmt(expr);
        return this.addAndGetStatement(statement);
    }

    default public ExpressionStmt addAndGetStatement(String statement) {
        return this.addAndGetStatement(new NameExpr(statement));
    }

    default public boolean isEmpty() {
        return this.getStatements().isEmpty();
    }

    default public N copyStatements(NodeList<Statement> nodeList) {
        for (Statement n : nodeList) {
            this.addStatement(n.clone());
        }
        return (N)((Node)((Object)this));
    }

    default public N copyStatements(NodeWithStatements<?> other) {
        return this.copyStatements(other.getStatements());
    }
}

