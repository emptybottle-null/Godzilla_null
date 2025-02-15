/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.VarMap;

public class ValNode
extends Expression {
    protected double val = 0.0;

    public ValNode(double d) {
        this.val = d;
    }

    public double eval(VarMap v, FuncMap f) {
        return this.val;
    }

    public double getValue() {
        return this.val;
    }

    public void setValue(double d) {
        this.val = d;
    }
}

