/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class EFunction
implements Function {
    public double of(double[] d, int numParam) {
        return Math.E;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 0;
    }

    public String toString() {
        return "e()";
    }
}

