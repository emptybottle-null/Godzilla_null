/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

public class ConditionalExpression
extends AstNode {
    private AstNode testExpression;
    private AstNode trueExpression;
    private AstNode falseExpression;
    private int questionMarkPosition = -1;
    private int colonPosition = -1;

    public ConditionalExpression() {
        this.type = 102;
    }

    public ConditionalExpression(int pos) {
        super(pos);
        this.type = 102;
    }

    public ConditionalExpression(int pos, int len) {
        super(pos, len);
        this.type = 102;
    }

    public AstNode getTestExpression() {
        return this.testExpression;
    }

    public void setTestExpression(AstNode testExpression) {
        this.assertNotNull(testExpression);
        this.testExpression = testExpression;
        testExpression.setParent(this);
    }

    public AstNode getTrueExpression() {
        return this.trueExpression;
    }

    public void setTrueExpression(AstNode trueExpression) {
        this.assertNotNull(trueExpression);
        this.trueExpression = trueExpression;
        trueExpression.setParent(this);
    }

    public AstNode getFalseExpression() {
        return this.falseExpression;
    }

    public void setFalseExpression(AstNode falseExpression) {
        this.assertNotNull(falseExpression);
        this.falseExpression = falseExpression;
        falseExpression.setParent(this);
    }

    public int getQuestionMarkPosition() {
        return this.questionMarkPosition;
    }

    public void setQuestionMarkPosition(int questionMarkPosition) {
        this.questionMarkPosition = questionMarkPosition;
    }

    public int getColonPosition() {
        return this.colonPosition;
    }

    public void setColonPosition(int colonPosition) {
        this.colonPosition = colonPosition;
    }

    @Override
    public boolean hasSideEffects() {
        if (this.testExpression == null || this.trueExpression == null || this.falseExpression == null) {
            ConditionalExpression.codeBug();
        }
        return this.trueExpression.hasSideEffects() && this.falseExpression.hasSideEffects();
    }

    @Override
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.makeIndent(depth));
        sb.append(this.testExpression.toSource(depth));
        sb.append(" ? ");
        sb.append(this.trueExpression.toSource(0));
        sb.append(" : ");
        sb.append(this.falseExpression.toSource(0));
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            this.testExpression.visit(v);
            this.trueExpression.visit(v);
            this.falseExpression.visit(v);
        }
    }
}

