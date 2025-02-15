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
import org.apache.poi.ss.formula.functions.Var1or2ArgFunction;

public final class Dec2Hex
extends Var1or2ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new Dec2Hex();
    private static final long MIN_VALUE = Long.parseLong("-549755813888");
    private static final long MAX_VALUE = Long.parseLong("549755813887");
    private static final int DEFAULT_PLACES_VALUE = 10;

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval number, ValueEval places) {
        ValueEval veText1;
        try {
            veText1 = OperandResolver.getSingleValue(number, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        String strText1 = OperandResolver.coerceValueToString(veText1);
        Double number1 = OperandResolver.parseDouble(strText1);
        if (number1 == null) {
            return ErrorEval.VALUE_INVALID;
        }
        if (number1.longValue() < MIN_VALUE || number1.longValue() > MAX_VALUE) {
            return ErrorEval.NUM_ERROR;
        }
        int placesNumber = 0;
        if (number1 < 0.0) {
            placesNumber = 10;
        } else if (places != null) {
            ValueEval placesValueEval;
            try {
                placesValueEval = OperandResolver.getSingleValue(places, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
            String placesStr = OperandResolver.coerceValueToString(placesValueEval);
            Double placesNumberDouble = OperandResolver.parseDouble(placesStr);
            if (placesNumberDouble == null) {
                return ErrorEval.VALUE_INVALID;
            }
            placesNumber = placesNumberDouble.intValue();
            if (placesNumber < 0) {
                return ErrorEval.NUM_ERROR;
            }
        }
        String hex = placesNumber != 0 ? String.format(Locale.ROOT, "%0" + placesNumber + "X", number1.intValue()) : Long.toHexString(number1.longValue());
        if (number1 < 0.0) {
            hex = "FF" + hex.substring(2);
        }
        return new StringEval(hex.toUpperCase(Locale.ROOT));
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        return this.evaluate(srcRowIndex, srcColumnIndex, arg0, null);
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 1) {
            return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
        }
        if (args.length == 2) {
            return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }
}

