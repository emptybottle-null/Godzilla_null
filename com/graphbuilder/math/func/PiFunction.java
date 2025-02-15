/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class PiFunction
implements Function {
    public double of(double[] d, int numParam) {
        return Math.PI;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 0;
    }

    public String toString() {
        return "pi()";
    }
}

