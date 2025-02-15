/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class MinFunction
implements Function {
    public double of(double[] d, int numParam) {
        if (numParam == 0) {
            return Double.MIN_VALUE;
        }
        double min = Double.MAX_VALUE;
        for (int i = 0; i < numParam; ++i) {
            if (!(d[i] < min)) continue;
            min = d[i];
        }
        return min;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam >= 0;
    }

    public String toString() {
        return "min(x1, x2, ..., xn)";
    }
}

