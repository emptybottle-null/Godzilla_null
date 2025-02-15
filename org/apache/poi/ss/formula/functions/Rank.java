/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;

public class Rank
extends Var2or3ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            double result = OperandResolver.coerceValueToDouble(ve);
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
            if (arg1 instanceof RefListEval) {
                return Rank.eval(result, (RefListEval)arg1, true);
            }
            AreaEval aeRange = Rank.convertRangeArg(arg1);
            return Rank.eval(result, aeRange, true);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            boolean order;
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            double result = OperandResolver.coerceValueToDouble(ve);
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
            ve = OperandResolver.getSingleValue(arg2, srcRowIndex, srcColumnIndex);
            int order_value = OperandResolver.coerceValueToInt(ve);
            if (order_value == 0) {
                order = true;
            } else if (order_value == 1) {
                order = false;
            } else {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
            if (arg1 instanceof RefListEval) {
                return Rank.eval(result, (RefListEval)arg1, order);
            }
            AreaEval aeRange = Rank.convertRangeArg(arg1);
            return Rank.eval(result, aeRange, order);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static ValueEval eval(double arg0, AreaEval aeRange, boolean descending_order) {
        int rank = 1;
        int height = aeRange.getHeight();
        int width = aeRange.getWidth();
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                Double value = Rank.getValue(aeRange, r, c);
                if (value == null || !(descending_order && value > arg0) && (descending_order || !(value < arg0))) continue;
                ++rank;
            }
        }
        return new NumberEval(rank);
    }

    private static ValueEval eval(double arg0, RefListEval aeRange, boolean descending_order) {
        int rank = 1;
        for (ValueEval ve : aeRange.getList()) {
            if (ve instanceof RefEval) {
                ve = ((RefEval)ve).getInnerValueEval(((RefEval)ve).getFirstSheetIndex());
            }
            if (!(ve instanceof NumberEval)) continue;
            double value = ((NumberEval)ve).getNumberValue();
            if (!(descending_order && value > arg0) && (descending_order || !(value < arg0))) continue;
            ++rank;
        }
        return new NumberEval(rank);
    }

    private static Double getValue(AreaEval aeRange, int relRowIndex, int relColIndex) {
        ValueEval addend = aeRange.getRelativeValue(relRowIndex, relColIndex);
        if (addend instanceof NumberEval) {
            return ((NumberEval)addend).getNumberValue();
        }
        return null;
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

