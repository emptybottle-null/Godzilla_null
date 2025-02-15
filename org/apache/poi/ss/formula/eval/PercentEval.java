/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class PercentEval
extends Fixed1ArgFunction {
    public static final Function instance = new PercentEval();

    private PercentEval() {
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        double d;
        try {
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            d = OperandResolver.coerceValueToDouble(ve);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (d == 0.0) {
            return NumberEval.ZERO;
        }
        return new NumberEval(d / 100.0);
    }
}

