/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class ConcatEval
extends Fixed2ArgFunction {
    public static final Function instance = new ConcatEval();

    private ConcatEval() {
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        ValueEval ve1;
        ValueEval ve0;
        try {
            ve0 = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            ve1 = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.getText(ve0));
        sb.append(this.getText(ve1));
        return new StringEval(sb.toString());
    }

    private Object getText(ValueEval ve) {
        if (ve instanceof StringValueEval) {
            StringValueEval sve = (StringValueEval)ve;
            return sve.getStringValue();
        }
        if (ve == BlankEval.instance) {
            return "";
        }
        throw new IllegalAccessError("Unexpected value type (" + ve.getClass().getName() + ")");
    }
}

