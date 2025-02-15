/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class SignFunction
implements Function {
    public double of(double[] d, int numParam) {
        if (d[0] > 0.0) {
            return 1.0;
        }
        if (d[0] < 0.0) {
            return -1.0;
        }
        return 0.0;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "sign(x)";
    }
}

