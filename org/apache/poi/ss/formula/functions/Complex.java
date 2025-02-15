/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.Locale;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;

public class Complex
extends Var2or3ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Complex();
    public static final String DEFAULT_SUFFIX = "i";
    public static final String SUPPORTED_SUFFIX = "j";

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval real_num, ValueEval i_num) {
        return this.evaluate(srcRowIndex, srcColumnIndex, real_num, i_num, new StringEval(DEFAULT_SUFFIX));
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval real_num, ValueEval i_num, ValueEval suffix) {
        double realINum;
        ValueEval veINum;
        double realNum;
        ValueEval veText1;
        try {
            veText1 = OperandResolver.getSingleValue(real_num, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        try {
            realNum = OperandResolver.coerceValueToDouble(veText1);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            veINum = OperandResolver.getSingleValue(i_num, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        try {
            realINum = OperandResolver.coerceValueToDouble(veINum);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        String suffixValue = OperandResolver.coerceValueToString(suffix);
        if (suffixValue.length() == 0) {
            suffixValue = DEFAULT_SUFFIX;
        }
        if (suffixValue.equals(DEFAULT_SUFFIX.toUpperCase(Locale.ROOT)) || suffixValue.equals(SUPPORTED_SUFFIX.toUpperCase(Locale.ROOT))) {
            return ErrorEval.VALUE_INVALID;
        }
        if (!suffixValue.equals(DEFAULT_SUFFIX) && !suffixValue.equals(SUPPORTED_SUFFIX)) {
            return ErrorEval.VALUE_INVALID;
        }
        StringBuilder strb = new StringBuilder();
        if (realNum != 0.0) {
            if (this.isDoubleAnInt(realNum)) {
                strb.append((int)realNum);
            } else {
                strb.append(realNum);
            }
        }
        if (realINum != 0.0) {
            if (strb.length() != 0 && realINum > 0.0) {
                strb.append("+");
            }
            if (realINum != 1.0 && realINum != -1.0) {
                if (this.isDoubleAnInt(realINum)) {
                    strb.append((int)realINum);
                } else {
                    strb.append(realINum);
                }
            }
            strb.append(suffixValue);
        }
        return new StringEval(strb.toString());
    }

    private boolean isDoubleAnInt(double number) {
        return number == Math.floor(number) && !Double.isInfinite(number);
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 2) {
            return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
        }
        if (args.length == 3) {
            return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1], args[2]);
        }
        return ErrorEval.VALUE_INVALID;
    }
}

