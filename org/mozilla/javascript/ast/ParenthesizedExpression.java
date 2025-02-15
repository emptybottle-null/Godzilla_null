/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class ParenthesizedExpression
extends AstNode {
    private AstNode expression;

    public ParenthesizedExpression() {
        this.type = 87;
    }

    public ParenthesizedExpression(int pos) {
        super(pos);
        this.type = 87;
    }

    public ParenthesizedExpression(int pos, int len) {
        super(pos, len);
        this.type = 87;
    }

    public ParenthesizedExpression(AstNode expr) {
        this(expr != null ? expr.getPosition() : 0, expr != null ? expr.getLength() : 1, expr);
    }

    public ParenthesizedExpression(int pos, int len, AstNode expr) {
        super(pos, len);
        this.type = 87;
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
        return this.makeIndent(depth) + "(" + this.expression.toSource(0) + ")";
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.expression.visit(v);
        }
    }
}

