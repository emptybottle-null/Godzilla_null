/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class ThrowStatement
extends AstNode {
    private AstNode expression;

    public ThrowStatement() {
        this.type = 50;
    }

    public ThrowStatement(int pos) {
        super(pos);
        this.type = 50;
    }

    public ThrowStatement(int pos, int len) {
        super(pos, len);
        this.type = 50;
    }

    public ThrowStatement(AstNode expr) {
        this.type = 50;
        this.setExpression(expr);
    }

    public ThrowStatement(int pos, AstNode expr) {
        super(pos, expr.getLength());
        this.type = 50;
        this.setExpression(expr);
    }

    public ThrowStatement(int pos, int len, AstNode expr) {
        super(pos, len);
        this.type = 50;
        this.setExpression(expr);
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode expression) {
        this.assertNotNull(expression);
        this.expression = expression;
        expression.setParent(this);
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append("throw");
        sb.append(" ");
        sb.append(this.expression.toSource(0));
        sb.append(";\n");
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.expression.visit(v);
        }
    }
}

