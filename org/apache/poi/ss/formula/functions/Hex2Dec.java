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
import org.apache.poi.ss.formula.functions.BaseNumberUtils;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public class Hex2Dec
extends Fixed1ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Hex2Dec();
    static final int HEXADECIMAL_BASE = 16;
    static final int MAX_NUMBER_OF_PLACES = 10;

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval numberVE) {
        String hex;
        if (numberVE instanceof RefEval) {
            RefEval re = (RefEval)numberVE;
            hex = OperandResolver.coerceValueToString(re.getInnerValueEval(re.getFirstSheetIndex()));
        } else {
            hex = OperandResolver.coerceValueToString(numberVE);
        }
        try {
            return new NumberEval(BaseNumberUtils.convertToDecimal(hex, 16, 10));
        } catch (IllegalArgumentException e) {
            return ErrorEval.NUM_ERROR;
        }
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
    }
}

