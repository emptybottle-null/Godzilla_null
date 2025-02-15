/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

public final class IntersectionEval
extends Fixed2ArgFunction {
    public static final Function instance = new IntersectionEval();

    private IntersectionEval() {
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            AreaEval reA = IntersectionEval.evaluateRef(arg0);
            AreaEval reB = IntersectionEval.evaluateRef(arg1);
            AreaEval result = IntersectionEval.resolveRange(reA, reB);
            if (result == null) {
                return ErrorEval.NULL_INTERSECTION;
            }
            return result;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static AreaEval resolveRange(AreaEval aeA, AreaEval aeB) {
        int aeAlr;
        int aeBlc;
        int aeAfr = aeA.getFirstRow();
        int aeAfc = aeA.getFirstColumn();
        if (aeAfc > (aeBlc = aeB.getLastColumn())) {
            return null;
        }
        int aeBfc = aeB.getFirstColumn();
        if (aeBfc > aeA.getLastColumn()) {
            return null;
        }
        int aeBlr = aeB.getLastRow();
        if (aeAfr > aeBlr) {
            return null;
        }
        int aeBfr = aeB.getFirstRow();
        if (aeBfr > (aeAlr = aeA.getLastRow())) {
            return null;
        }
        int top = Math.max(aeAfr, aeBfr);
        int bottom = Math.min(aeAlr, aeBlr);
        int left = Math.max(aeAfc, aeBfc);
        int right = Math.min(aeA.getLastColumn(), aeBlc);
        return aeA.offset(top - aeAfr, bottom - aeAfr, left - aeAfc, right - aeAfc);
    }

    private static AreaEval evaluateRef(ValueEval arg) throws EvaluationException {
        if (arg instanceof AreaEval) {
            return (AreaEval)arg;
        }
        if (arg instanceof RefEval) {
            return ((RefEval)arg).offset(0, 0, 0, 0);
        }
        if (arg instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval)arg);
        }
        throw new IllegalArgumentException("Unexpected ref arg class (" + arg.getClass().getName() + ")");
    }
}

