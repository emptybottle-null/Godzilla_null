/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.OpNode;
import com.graphbuilder.math.VarMap;

public class MultNode
extends OpNode {
    public MultNode(Expression leftChild, Expression rightChild) {
        super(leftChild, rightChild);
    }

    public double eval(VarMap v, FuncMap f) {
        double a = this.leftChild.eval(v, f);
        double b = this.rightChild.eval(v, f);
        return a * b;
    }

    public String getSymbol() {
        return "*";
    }
}

