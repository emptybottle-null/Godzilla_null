/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class RoundFunction
implements Function {
    public double of(double[] d, int numParam) {
        if (d[0] >= 9.223372036854776E18 || d[0] <= -9.223372036854776E18) {
            return d[0];
        }
        return Math.round(d[0]);
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "round(x)";
    }
}

