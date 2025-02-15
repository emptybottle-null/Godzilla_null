/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class AvgFunction
implements Function {
    public double of(double[] d, int numParam) {
        double sum = 0.0;
        for (int i = 0; i < numParam; ++i) {
            sum += d[i];
        }
        return sum / (double)numParam;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam > 0;
    }

    public String toString() {
        return "avg(x1, x2, ..., xn)";
    }
}

