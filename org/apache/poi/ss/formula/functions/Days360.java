/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class Days360
extends Var2or3ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            double d0 = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
            double d1 = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
            return new NumberEval(Days360.evaluate(d0, d1, false));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            double d0 = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
            double d1 = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
            ValueEval ve = OperandResolver.getSingleValue(arg2, srcRowIndex, srcColumnIndex);
            Boolean method = OperandResolver.coerceValueToBoolean(ve, false);
            return new NumberEval(Days360.evaluate(d0, d1, method != null && method != false));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static double evaluate(double d0, double d1, boolean method) {
        Calendar realStart = Days360.getDate(d0);
        Calendar realEnd = Days360.getDate(d1);
        int[] startingDate = Days360.getStartingDate(realStart, method);
        int[] endingDate = Days360.getEndingDate(realEnd, startingDate, method);
        return (double)endingDate[0] * 360.0 + (double)endingDate[1] * 30.0 + (double)endingDate[2] - ((double)startingDate[0] * 360.0 + (double)startingDate[1] * 30.0 + (double)startingDate[2]);
    }

    private static Calendar getDate(double date) {
        Calendar processedDate = LocaleUtil.getLocaleCalendar();
        processedDate.setTime(DateUtil.getJavaDate(date, false));
        return processedDate;
    }

    private static int[] getStartingDate(Calendar realStart, boolean method) {
        int yyyy = realStart.get(1);
        int mm = realStart.get(2);
        int dd = Math.min(30, realStart.get(5));
        if (!method && Days360.isLastDayOfMonth(realStart)) {
            dd = 30;
        }
        return new int[]{yyyy, mm, dd};
    }

    private static int[] getEndingDate(Calendar realEnd, int[] startingDate, boolean method) {
        int yyyy = realEnd.get(1);
        int mm = realEnd.get(2);
        int dd = Math.min(30, realEnd.get(5));
        if (!method && realEnd.get(5) == 31) {
            if (startingDate[2] < 30) {
                realEnd.set(5, 1);
                realEnd.add(2, 1);
                yyyy = realEnd.get(1);
                mm = realEnd.get(2);
                dd = 1;
            } else {
                dd = 30;
            }
        }
        return new int[]{yyyy, mm, dd};
    }

    private static boolean isLastDayOfMonth(Calendar date) {
        int lastDayOfMonth;
        int dayOfMonth = date.get(5);
        return dayOfMonth == (lastDayOfMonth = date.getActualMaximum(5));
    }
}

