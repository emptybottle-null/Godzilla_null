/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.NumericFunction;

public final class Even
extends NumericFunction.OneArg {
    private static final long PARITY_MASK = -2L;

    @Override
    protected double evaluate(double d) {
        if (d == 0.0) {
            return 0.0;
        }
        long result = d > 0.0 ? Even.calcEven(d) : -Even.calcEven(-d);
        return result;
    }

    private static long calcEven(double d) {
        long x = (long)d & 0xFFFFFFFFFFFFFFFEL;
        if ((double)x == d) {
            return x;
        }
        return x + 2L;
    }
}

