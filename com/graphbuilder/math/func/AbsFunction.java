/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class AbsFunction
implements Function {
    public double of(double[] d, int numParam) {
        return Math.abs(d[0]);
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "abs(x)";
    }
}

