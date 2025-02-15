/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class TanhFunction
implements Function {
    public double of(double[] d, int numParam) {
        double e = Math.pow(Math.E, 2.0 * d[0]);
        return (e - 1.0) / (e + 1.0);
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "tanh(x)";
    }
}

