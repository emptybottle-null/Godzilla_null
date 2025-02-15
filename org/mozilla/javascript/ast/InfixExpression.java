/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class InfixExpression
extends AstNode {
    protected AstNode left;
    protected AstNode right;
    protected int operatorPosition = -1;

    public InfixExpression() {
    }

    public InfixExpression(int pos) {
        super(pos);
    }

    public InfixExpression(int pos, int len) {
        super(pos, len);
    }

    public InfixExpression(int pos, int len, AstNode left, AstNode right) {
        super(pos, len);
        this.setLeft(left);
        this.setRight(right);
    }

    public InfixExpression(AstNode left, AstNode right) {
        this.setLeftAndRight(left, right);
    }

    public InfixExpression(int operator2, AstNode left, AstNode right, int operatorPos) {
        this.setType(operator2);
        this.setOperatorPosition(operatorPos - left.getPosition());
        this.setLeftAndRight(left, right);
    }

    public void setLeftAndRight(AstNode left, AstNode right) {
        this.assertNotNull(left);
        this.assertNotNull(right);
        int beg = left.getPosition();
        int end = right.getPosition() + right.getLength();
        this.setBounds(beg, end);
        this.setLeft(left);
        this.setRight(right);
    }

    public int getOperator() {
        return this.getType();
    }

    public void setOperator(int operator2) {
        if (!Token.isValidToken(operator2)) {
            throw new IllegalArgumentException("Invalid token: " + operator2);
        }
        this.setType(operator2);
    }

    public AstNode getLeft() {
        return this.left;
    }

    public void setLeft(AstNode left) {
        this.assertNotNull(left);
        this.left = left;
        this.setLineno(left.getLineno());
        left.setParent(this);
    }

    public AstNode getRight() {
        return this.right;
    }

    public void setRight(AstNode right) {
        this.assertNotNull(right);
        this.right = right;
        right.setParent(this);
    }

    public int getOperatorPosition() {
        return this.operatorPosition;
    }

    public void setOperatorPosition(int operatorPosition) {
        this.operatorPosition = operatorPosition;
    }

    @Override
    public boolean hasSideEffects() {
        switch (this.getType()) {
            case 89: {
                return this.right != null && this.right.hasSideEffects();
            }
            case 104: 
            case 105: {
                return this.left != null && this.left.hasSideEffects() || this.right != null && this.right.hasSideEffects();
            }
        }
        return super.hasSideEffects();
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append(this.left.toSource());
        sb.append(" ");
        sb.append(InfixExpression.operatorToString(this.getType()));
        sb.append(" ");
        sb.append(this.right.toSource());
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.left.visit(v);
            this.right.visit(v);
        }
    }
}

