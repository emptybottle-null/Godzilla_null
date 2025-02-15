/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function;

public final class Choose
implements Function {
    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            int ix = Choose.evaluateFirstArg(args[0], srcRowIndex, srcColumnIndex);
            if (ix < 1 || ix >= args.length) {
                return ErrorEval.VALUE_INVALID;
            }
            ValueEval result = OperandResolver.getSingleValue(args[ix], srcRowIndex, srcColumnIndex);
            if (result == MissingArgEval.instance) {
                return BlankEval.instance;
            }
            return result;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public static int evaluateFirstArg(ValueEval arg0, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval ev = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
        return OperandResolver.coerceValueToInt(ev);
    }
}

