/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

final class ParityFunction
implements FreeRefFunction {
    public static final FreeRefFunction IS_EVEN = new ParityFunction(0);
    public static final FreeRefFunction IS_ODD = new ParityFunction(1);
    private final int _desiredParity;

    private ParityFunction(int desiredParity) {
        this._desiredParity = desiredParity;
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        int val;
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            val = ParityFunction.evaluateArgParity(args[0], ec.getRowIndex(), ec.getColumnIndex());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return BoolEval.valueOf(val == this._desiredParity);
    }

    private static int evaluateArgParity(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, (short)srcCellCol);
        double d = OperandResolver.coerceValueToDouble(ve);
        if (d < 0.0) {
            d = -d;
        }
        long v = (long)Math.floor(d);
        return Math.toIntExact(v & 1L);
    }
}

