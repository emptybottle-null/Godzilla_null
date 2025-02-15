/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class FactFunction
implements Function {
    public double of(double[] d, int numParam) {
        int n = (int)d[0];
        double result = 1.0;
        for (int i = n; i > 1; --i) {
            result *= (double)i;
        }
        return result;
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "fact(n)";
    }
}

