/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.atp;

import java.util.Calendar;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.atp.DateParser;
import org.apache.poi.ss.formula.atp.YearFracCalculator;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.usermodel.DateUtil;

final class YearFrac
implements FreeRefFunction {
    public static final FreeRefFunction instance = new YearFrac();

    private YearFrac() {
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        double result;
        int srcCellRow = ec.getRowIndex();
        int srcCellCol = ec.getColumnIndex();
        try {
            int basis = 0;
            switch (args.length) {
                case 3: {
                    basis = YearFrac.evaluateIntArg(args[2], srcCellRow, srcCellCol);
                }
                case 2: {
                    break;
                }
                default: {
                    return ErrorEval.VALUE_INVALID;
                }
            }
            double startDateVal = YearFrac.evaluateDateArg(args[0], srcCellRow, srcCellCol);
            double endDateVal = YearFrac.evaluateDateArg(args[1], srcCellRow, srcCellCol);
            result = YearFracCalculator.calculate(startDateVal, endDateVal, basis);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        return new NumberEval(result);
    }

    private static double evaluateDateArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, (short)srcCellCol);
        if (ve instanceof StringEval) {
            String strVal = ((StringEval)ve).getStringValue();
            Double dVal = OperandResolver.parseDouble(strVal);
            if (dVal != null) {
                return dVal;
            }
            Calendar date = DateParser.parseDate(strVal);
            return DateUtil.getExcelDate(date, false);
        }
        return OperandResolver.coerceValueToDouble(ve);
    }

    private static int evaluateIntArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, (short)srcCellCol);
        return OperandResolver.coerceValueToInt(ve);
    }
}

