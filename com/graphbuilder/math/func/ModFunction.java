/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class ModFunction
implements Function {
    public double of(double[] d, int numParam) {
        return d[0] % d[1];
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 2;
    }

    public String toString() {
        return "mod(x, y)";
    }
}

