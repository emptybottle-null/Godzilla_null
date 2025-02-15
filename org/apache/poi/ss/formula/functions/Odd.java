/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.NumericFunction;

public final class Odd
extends NumericFunction.OneArg {
    private static final long PARITY_MASK = -2L;

    @Override
    protected double evaluate(double d) {
        if (d == 0.0) {
            return 1.0;
        }
        return d > 0.0 ? (double)Odd.calcOdd(d) : (double)(-Odd.calcOdd(-d));
    }

    private static long calcOdd(double d) {
        double dpm1 = d + 1.0;
        long x = (long)dpm1 & 0xFFFFFFFFFFFFFFFEL;
        return Double.compare(x, dpm1) == 0 ? x - 1L : x + 1L;
    }
}

