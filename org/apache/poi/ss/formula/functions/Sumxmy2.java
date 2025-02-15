/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.XYNumericFunction;

public final class Sumxmy2
extends XYNumericFunction {
    private static final XYNumericFunction.Accumulator XMinusYSquaredAccumulator = new XYNumericFunction.Accumulator(){

        @Override
        public double accumulate(double x, double y) {
            double xmy = x - y;
            return xmy * xmy;
        }
    };

    @Override
    protected XYNumericFunction.Accumulator createAccumulator() {
        return XMinusYSquaredAccumulator;
    }
}

