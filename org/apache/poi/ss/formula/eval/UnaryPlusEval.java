/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class UnaryPlusEval
extends Fixed1ArgFunction
implements ArrayFunction {
    public static final Function instance = new UnaryPlusEval();

    private UnaryPlusEval() {
    }

    @Override
    public ValueEval evaluate(int srcCellRow, int srcCellCol, ValueEval arg0) {
        double d;
        try {
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcCellRow, srcCellCol);
            if (ve instanceof StringEval) {
                return ve;
            }
            d = OperandResolver.coerceValueToDouble(ve);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return new NumberEval(d);
    }

    @Override
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, valA -> this.evaluate(srcRowIndex, srcColumnIndex, (ValueEval)valA));
    }
}

