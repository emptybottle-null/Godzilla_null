/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.PascalsTriangle;
import com.graphbuilder.math.func.Function;

public class CombinFunction
implements Function {
    private final PascalsTriangle pascalsTriangle = new PascalsTriangle();

    public double of(double[] d, int numParam) {
        int n = (int)d[0];
        int r = (int)d[1];
        return this.pascalsTriangle.nCr(n, r);
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 2;
    }

    public String toString() {
        return "combin(n, r)";
    }
}

