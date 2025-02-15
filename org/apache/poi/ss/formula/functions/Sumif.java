/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.formula.functions.Countif;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;

public final class Sumif
extends Var2or3ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        AreaEval aeRange;
        try {
            aeRange = Sumif.convertRangeArg(arg0);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return Sumif.eval(srcRowIndex, srcColumnIndex, arg1, aeRange, aeRange);
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        AreaEval aeSum;
        AreaEval aeRange;
        try {
            aeRange = Sumif.convertRangeArg(arg0);
            aeSum = Sumif.createSumRange(arg2, aeRange);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return Sumif.eval(srcRowIndex, srcColumnIndex, arg1, aeRange, aeSum);
    }

    private static ValueEval eval(int srcRowIndex, int srcColumnIndex, ValueEval arg1, AreaEval aeRange, AreaEval aeSum) {
        CountUtils.I_MatchPredicate mp = Countif.createCriteriaPredicate(arg1, srcRowIndex, srcColumnIndex);
        if (mp == null) {
            return NumberEval.ZERO;
        }
        double result = Sumif.sumMatchingCells(aeRange, mp, aeSum);
        return new NumberEval(result);
    }

    private static double sumMatchingCells(AreaEval aeRange, CountUtils.I_MatchPredicate mp, AreaEval aeSum) {
        int height = aeRange.getHeight();
        int width = aeRange.getWidth();
        double result = 0.0;
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                result += Sumif.accumulate(aeRange, mp, aeSum, r, c);
            }
        }
        return result;
    }

    private static double accumulate(AreaEval aeRange, CountUtils.I_MatchPredicate mp, AreaEval aeSum, int relRowIndex, int relColIndex) {
        if (!mp.matches(aeRange.getRelativeValue(relRowIndex, relColIndex))) {
            return 0.0;
        }
        ValueEval addend = aeSum.getRelativeValue(relRowIndex, relColIndex);
        if (addend instanceof NumberEval) {
            return ((NumberEval)addend).getNumberValue();
        }
        return 0.0;
    }

    private static AreaEval createSumRange(ValueEval eval, AreaEval aeRange) throws EvaluationException {
        if (eval instanceof AreaEval) {
            return ((AreaEval)eval).offset(0, aeRange.getHeight() - 1, 0, aeRange.getWidth() - 1);
        }
        if (eval instanceof RefEval) {
            return ((RefEval)eval).offset(0, aeRange.getHeight() - 1, 0, aeRange.getWidth() - 1);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }

    private static AreaEval convertRangeArg(ValueEval eval) throws EvaluationException {
        if (eval instanceof AreaEval) {
            return (AreaEval)eval;
        }
        if (eval instanceof RefEval) {
            return ((RefEval)eval).offset(0, 0, 0, 0);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }
}

