/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class WeekNum
extends Fixed2ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new WeekNum();

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval serialNumVE, ValueEval returnTypeVE) {
        int returnType;
        double serialNum;
        try {
            serialNum = NumericFunction.singleOperandEvaluate(serialNumVE, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        Calendar serialNumCalendar = LocaleUtil.getLocaleCalendar();
        serialNumCalendar.setTime(DateUtil.getJavaDate(serialNum, false));
        try {
            ValueEval ve = OperandResolver.getSingleValue(returnTypeVE, srcRowIndex, srcColumnIndex);
            returnType = OperandResolver.coerceValueToInt(ve);
        } catch (EvaluationException e) {
            return ErrorEval.NUM_ERROR;
        }
        if (returnType != 1 && returnType != 2) {
            return ErrorEval.NUM_ERROR;
        }
        return new NumberEval(this.getWeekNo(serialNumCalendar, returnType));
    }

    public int getWeekNo(Calendar cal, int weekStartOn) {
        if (weekStartOn == 1) {
            cal.setFirstDayOfWeek(1);
        } else {
            cal.setFirstDayOfWeek(2);
        }
        return cal.get(3);
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 2) {
            return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }
}

