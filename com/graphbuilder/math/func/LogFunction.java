/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class LogFunction
implements Function {
    public double of(double[] d, int numParam) {
        if (numParam == 1) {
            return Math.log(d[0]) / Math.log(10.0);
        }
        return Math.log(d[0]) / Math.log(d[1]);
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1 || numParam == 2;
    }

    public String toString() {
        return "log(x):log(x, y)";
    }
}

