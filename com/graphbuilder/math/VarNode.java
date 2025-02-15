/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.TermNode;
import com.graphbuilder.math.VarMap;

public class VarNode
extends TermNode {
    public VarNode(String name, boolean negate) {
        super(name, negate);
    }

    public double eval(VarMap v, FuncMap f) {
        double val = v.getValue(this.name);
        if (this.negate) {
            val = -val;
        }
        return val;
    }
}

