/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.regex.Matcher;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Imaginary;

public class ImReal
extends Fixed1ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new ImReal();

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval inumberVE) {
        ValueEval veText1;
        try {
            veText1 = OperandResolver.getSingleValue(inumberVE, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        String iNumber = OperandResolver.coerceValueToString(veText1);
        Matcher m = Imaginary.COMPLEX_NUMBER_PATTERN.matcher(iNumber);
        boolean result = m.matches();
        String real = "";
        if (result) {
            boolean hasRealPart;
            String realGroup = m.group(2);
            boolean bl = hasRealPart = realGroup.length() != 0;
            if (realGroup.length() == 0) {
                return new StringEval(String.valueOf(0));
            }
            if (hasRealPart) {
                String groupRealNumber;
                String sign = "";
                String realSign = m.group(1);
                if (realSign.length() != 0 && !realSign.equals("+")) {
                    sign = realSign;
                }
                real = (groupRealNumber = m.group(2)).length() != 0 ? sign + groupRealNumber : sign + "1";
            }
        } else {
            return ErrorEval.NUM_ERROR;
        }
        return new StringEval(real);
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
    }
}

