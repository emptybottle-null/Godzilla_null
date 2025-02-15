/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public class Quotient
extends Fixed2ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Quotient();

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval venumerator, ValueEval vedenominator) {
        double enumerator = 0.0;
        try {
            enumerator = OperandResolver.coerceValueToDouble(venumerator);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        double denominator = 0.0;
        try {
            denominator = OperandResolver.coerceValueToDouble(vedenominator);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        if (denominator == 0.0) {
            return ErrorEval.DIV_ZERO;
        }
        return new NumberEval((int)(enumerator / denominator));
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
    }
}

