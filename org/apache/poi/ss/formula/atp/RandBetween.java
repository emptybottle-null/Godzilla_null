/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

final class RandBetween
implements FreeRefFunction {
    public static final FreeRefFunction instance = new RandBetween();

    private RandBetween() {
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        double top;
        double bottom;
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            bottom = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(args[0], ec.getRowIndex(), ec.getColumnIndex()));
            top = OperandResolver.coerceValueToDouble(OperandResolver.getSingleValue(args[1], ec.getRowIndex(), ec.getColumnIndex()));
            if (bottom > top) {
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        bottom = Math.ceil(bottom);
        top = Math.floor(top);
        if (bottom > top) {
            top = bottom;
        }
        return new NumberEval(bottom + (double)((long)(Math.random() * (top - bottom + 1.0))));
    }
}

