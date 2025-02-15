/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class UnaryExpression
extends AstNode {
    private AstNode operand;
    private boolean isPostfix;

    public UnaryExpression() {
    }

    public UnaryExpression(int pos) {
        super(pos);
    }

    public UnaryExpression(int pos, int len) {
        super(pos, len);
    }

    public UnaryExpression(int operator2, int operatorPosition, AstNode operand) {
        this(operator2, operatorPosition, operand, false);
    }

    public UnaryExpression(int operator2, int operatorPosition, AstNode operand, boolean postFix) {
        this.assertNotNull(operand);
        int beg = postFix ? operand.getPosition() : operatorPosition;
        int end = postFix ? operatorPosition + 2 : operand.getPosition() + operand.getLength();
        this.setBounds(beg, end);
        this.setOperator(operator2);
        this.setOperand(operand);
        this.isPostfix = postFix;
    }

    public int getOperator() {
        return this.type;
    }

    public void setOperator(int operator2) {
        if (!Token.isValidToken(operator2)) {
            throw new IllegalArgumentException("Invalid token: " + operator2);
        }
        this.setType(operator2);
    }

    public AstNode getOperand() {
        return this.operand;
    }

    public void setOperand(AstNode operand) {
        this.assertNotNull(operand);
        this.operand = operand;
        operand.setParent(this);
    }

    public boolean isPostfix() {
        return this.isPostfix;
    }

    public boolean isPrefix() {
        return !this.isPostfix;
    }

    public void setIsPostfix(boolean isPostfix) {
        this.isPostfix = isPostfix;
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        int type = this.getType();
        if (!this.isPostfix) {
            sb.append(UnaryExpression.operatorToString(type));
            if (type == 32 || type == 31 || type == 126) {
                sb.append(" ");
            }
        }
        sb.append(this.operand.toSource());
        if (this.isPostfix) {
            sb.append(UnaryExpression.operatorToString(type));
        }
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.operand.visit(v);
        }
    }
}

