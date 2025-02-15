/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.Expression;

public abstract class OpNode
extends Expression {
    protected Expression leftChild = null;
    protected Expression rightChild = null;

    public OpNode(Expression leftChild, Expression rightChild) {
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }

    public void setLeftChild(Expression x) {
        this.checkBeforeAccept(x);
        if (this.leftChild != null) {
            this.leftChild.parent = null;
        }
        x.parent = this;
        this.leftChild = x;
    }

    public void setRightChild(Expression x) {
        this.checkBeforeAccept(x);
        if (this.rightChild != null) {
            this.rightChild.parent = null;
        }
        x.parent = this;
        this.rightChild = x;
    }

    public Expression getLeftChild() {
        return this.leftChild;
    }

    public Expression getRightChild() {
        return this.rightChild;
    }

    public abstract String getSymbol();
}

