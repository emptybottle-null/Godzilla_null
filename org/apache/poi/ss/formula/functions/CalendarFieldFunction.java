/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.usermodel.DateUtil;

public final class CalendarFieldFunction
extends Fixed1ArgFunction {
    public static final Function YEAR = new CalendarFieldFunction(1);
    public static final Function MONTH = new CalendarFieldFunction(2);
    public static final Function DAY = new CalendarFieldFunction(5);
    public static final Function HOUR = new CalendarFieldFunction(11);
    public static final Function MINUTE = new CalendarFieldFunction(12);
    public static final Function SECOND = new CalendarFieldFunction(13);
    private final int _dateFieldId;

    private CalendarFieldFunction(int dateFieldId) {
        this._dateFieldId = dateFieldId;
    }

    @Override
    public final ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        double val;
        try {
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            val = OperandResolver.coerceValueToDouble(ve);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        if (val < 0.0) {
            return ErrorEval.NUM_ERROR;
        }
        return new NumberEval(this.getCalField(val));
    }

    private int getCalField(double serialDate) {
        if ((int)serialDate == 0) {
            switch (this._dateFieldId) {
                case 1: {
                    return 1900;
                }
                case 2: {
                    return 1;
                }
                case 5: {
                    return 0;
                }
            }
        }
        Calendar c = DateUtil.getJavaCalendarUTC(serialDate + 5.78125E-6, false);
        int result = c.get(this._dateFieldId);
        if (this._dateFieldId == 2) {
            ++result;
        }
        return result;
    }
}

