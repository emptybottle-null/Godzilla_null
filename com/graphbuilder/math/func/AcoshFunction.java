/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math.func;

import com.graphbuilder.math.func.Function;

public class AcoshFunction
implements Function {
    public double of(double[] d, int numParam) {
        double a = Math.sqrt((d[0] + 1.0) / 2.0);
        double b = Math.sqrt((d[0] - 1.0) / 2.0);
        return 2.0 * Math.log(a + b);
    }

    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "acosh(x)";
    }
}

