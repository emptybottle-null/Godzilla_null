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

public final class Delta
extends Fixed2ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Delta();
    private static final NumberEval ONE = new NumberEval(1.0);
    private static final NumberEval ZERO = new NumberEval(0.0);

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg1, ValueEval arg2) {
        try {
            Double number1 = Delta.evaluateValue(arg1, srcRowIndex, srcColumnIndex);
            if (number1 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            Double number2 = Delta.evaluateValue(arg2, srcRowIndex, srcColumnIndex);
            if (number2 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            return number1.compareTo(number2) == 0 ? ONE : ZERO;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 2) {
            return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}

