/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

public class EDate
implements FreeRefFunction {
    public static final FreeRefFunction instance = new EDate();

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double startDateAsNumber = this.getValue(args[0]);
            int offsetInMonthAsNumber = (int)this.getValue(args[1]);
            Date startDate = DateUtil.getJavaDate(startDateAsNumber);
            if (startDate == null) {
                return ErrorEval.VALUE_INVALID;
            }
            Calendar calendar = LocaleUtil.getLocaleCalendar();
            calendar.setTime(startDate);
            calendar.add(2, offsetInMonthAsNumber);
            return new NumberEval(DateUtil.getExcelDate(calendar.getTime()));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private double getValue(ValueEval arg) throws EvaluationException {
        if (arg instanceof NumberEval) {
            return ((NumberEval)arg).getNumberValue();
        }
        if (arg instanceof BlankEval) {
            return 0.0;
        }
        if (arg instanceof RefEval) {
            RefEval refEval = (RefEval)arg;
            if (refEval.getNumberOfSheets() > 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            ValueEval innerValueEval = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
            if (innerValueEval instanceof NumberEval) {
                return ((NumberEval)innerValueEval).getNumberValue();
            }
            if (innerValueEval instanceof BlankEval) {
                return 0.0;
            }
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }
}

