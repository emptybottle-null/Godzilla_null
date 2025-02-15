/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.math.BigInteger;
import java.util.HashMap;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public class FactDouble
extends Fixed1ArgFunction
implements FreeRefFunction {
    public static final FreeRefFunction instance = new FactDouble();
    static HashMap<Integer, BigInteger> cache = new HashMap();

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval numberVE) {
        int number;
        try {
            number = OperandResolver.coerceValueToInt(numberVE);
        } catch (EvaluationException e) {
            return ErrorEval.VALUE_INVALID;
        }
        if (number < 0) {
            return ErrorEval.NUM_ERROR;
        }
        return new NumberEval(FactDouble.factorial(number).longValue());
    }

    public static BigInteger factorial(int n) {
        if (n == 0 || n < 0) {
            return BigInteger.ONE;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        BigInteger result = BigInteger.valueOf(n).multiply(FactDouble.factorial(n - 2));
        cache.put(n, result);
        return result;
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
    }
}

