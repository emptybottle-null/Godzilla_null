/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public class Bin2Dec
extends Fixed1ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Bin2Dec();

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval numberVE) {
        String value;
        boolean isPositive;
        String unsigned;
        String number;
        if (numberVE instanceof RefEval) {
            RefEval re = (RefEval)numberVE;
            number = OperandResolver.coerceValueToString(re.getInnerValueEval(re.getFirstSheetIndex()));
        } else {
            number = OperandResolver.coerceValueToString(numberVE);
        }
        if (number.length() > 10) {
            return ErrorEval.NUM_ERROR;
        }
        if (number.length() < 10) {
            unsigned = number;
            isPositive = true;
        } else {
            unsigned = number.substring(1);
            isPositive = number.startsWith("0");
        }
        try {
            if (isPositive) {
                int sum = this.getDecimalValue(unsigned);
                value = String.valueOf(sum);
            } else {
                String inverted = Bin2Dec.toggleBits(unsigned);
                int sum = this.getDecimalValue(inverted);
                value = "-" + ++sum;
            }
        } catch (NumberFormatException e) {
            return ErrorEval.NUM_ERROR;
        }
        return new NumberEval(Long.parseLong(value));
    }

    private int getDecimalValue(String unsigned) {
        int sum = 0;
        int numBits = unsigned.length();
        int power = numBits - 1;
        for (int i = 0; i < numBits; ++i) {
            int bit = Integer.parseInt(unsigned.substring(i, i + 1));
            int term = (int)((double)bit * Math.pow(2.0, power));
            sum += term;
            --power;
        }
        return sum;
    }

    private static String toggleBits(String s) {
        long i = Long.parseLong(s, 2);
        long i2 = i ^ (1L << s.length()) - 1L;
        String s2 = Long.toBinaryString(i2);
        while (s2.length() < s.length()) {
            s2 = '0' + s2;
        }
        return s2;
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
    }
}

