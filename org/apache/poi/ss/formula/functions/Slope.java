/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.LinearRegressionFunction;

public final class Slope
extends Fixed2ArgFunction {
    private final LinearRegressionFunction func = new LinearRegressionFunction(LinearRegressionFunction.FUNCTION.SLOPE);

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        return this.func.evaluate(srcRowIndex, srcColumnIndex, arg0, arg1);
    }
}

