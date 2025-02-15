/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.XYNumericFunction;

public final class Sumx2my2
extends XYNumericFunction {
    private static final XYNumericFunction.Accumulator XSquaredMinusYSquaredAccumulator = new XYNumericFunction.Accumulator(){

        @Override
        public double accumulate(double x, double y) {
            return x * x - y * y;
        }
    };

    @Override
    protected XYNumericFunction.Accumulator createAccumulator() {
        return XSquaredMinusYSquaredAccumulator;
    }
}

