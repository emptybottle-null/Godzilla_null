/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;

public class Rept
extends Fixed2ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval text, ValueEval number_times) {
        ValueEval veText1;
        try {
            veText1 = OperandResolver.getSingleValue(text, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        String strText1 = OperandResolver.coerceValueToString(veText1);
        double numberOfTime = 0.0;
        try {
            numberOfTime = OperandResolver.coerceValueToDouble(number_times);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        int numberOfTimeInt = (int)numberOfTime;
        StringBuilder strb = new StringBuilder(strText1.length() * numberOfTimeInt);
        for (int i = 0; i < numberOfTimeInt; ++i) {
            strb.append(strText1);
        }
        if (strb.toString().length() > Short.MAX_VALUE) {
            return ErrorEval.VALUE_INVALID;
        }
        return new StringEval(strb.toString());
    }
}

