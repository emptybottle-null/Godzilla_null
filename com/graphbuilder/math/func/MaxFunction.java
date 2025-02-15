/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class MaxFunction
implements Function {
    public double of(double[] d, int numParam) {
        if (numParam == 0) {
            return Double.MAX_VALUE;
        }
        double max = -1.7976931348623157E308;
        for (int i = 0; i < numParam; ++i) {
            if (!(d[i] > max)) continue;
            max = d[i];
        }
        return max;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam >= 0;
    }

    public String toString() {
        return "max(x1, x2, ..., xn)";
    }
}

