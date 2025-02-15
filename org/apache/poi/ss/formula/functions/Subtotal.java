/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.poi.ss.formula.LazyRefEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.AggregateFunction;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.formula.functions.Counta;
import org.apache.poi.ss.formula.functions.Function;

public class Subtotal
implements Function {
    private static Function findFunction(int functionCode) throws EvaluationException {
        switch (functionCode) {
            case 1: {
                return AggregateFunction.subtotalInstance(AggregateFunction.AVERAGE, true);
            }
            case 2: {
                return Count.subtotalInstance(true);
            }
            case 3: {
                return Counta.subtotalInstance(true);
            }
            case 4: {
                return AggregateFunction.subtotalInstance(AggregateFunction.MAX, true);
            }
            case 5: {
                return AggregateFunction.subtotalInstance(AggregateFunction.MIN, true);
            }
            case 6: {
                return AggregateFunction.subtotalInstance(AggregateFunction.PRODUCT, true);
            }
            case 7: {
                return AggregateFunction.subtotalInstance(AggregateFunction.STDEV, true);
            }
            case 8: {
                throw new NotImplementedFunctionException("STDEVP");
            }
            case 9: {
                return AggregateFunction.subtotalInstance(AggregateFunction.SUM, true);
            }
            case 10: {
                throw new NotImplementedFunctionException("VAR");
            }
            case 11: {
                throw new NotImplementedFunctionException("VARP");
            }
            case 101: {
                return AggregateFunction.subtotalInstance(AggregateFunction.AVERAGE, false);
            }
            case 102: {
                return Count.subtotalInstance(false);
            }
            case 103: {
                return Counta.subtotalInstance(false);
            }
            case 104: {
                return AggregateFunction.subtotalInstance(AggregateFunction.MAX, false);
            }
            case 105: {
                return AggregateFunction.subtotalInstance(AggregateFunction.MIN, false);
            }
            case 106: {
                return AggregateFunction.subtotalInstance(AggregateFunction.PRODUCT, false);
            }
            case 107: {
                return AggregateFunction.subtotalInstance(AggregateFunction.STDEV, false);
            }
            case 108: {
                throw new NotImplementedFunctionException("STDEVP SUBTOTAL with 'exclude hidden values' option");
            }
            case 109: {
                return AggregateFunction.subtotalInstance(AggregateFunction.SUM, false);
            }
            case 110: {
                throw new NotImplementedFunctionException("VAR SUBTOTAL with 'exclude hidden values' option");
            }
            case 111: {
                throw new NotImplementedFunctionException("VARP SUBTOTAL with 'exclude hidden values' option");
            }
        }
        throw EvaluationException.invalidValue();
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        Function innerFunc;
        int nInnerArgs = args.length - 1;
        if (nInnerArgs < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        int functionCode = 0;
        try {
            ValueEval ve = OperandResolver.getSingleValue(args[0], srcRowIndex, srcColumnIndex);
            functionCode = OperandResolver.coerceValueToInt(ve);
            innerFunc = Subtotal.findFunction(functionCode);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
        ArrayList<ValueEval> list = new ArrayList<ValueEval>(Arrays.asList(args).subList(1, args.length));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ValueEval eval = (ValueEval)it.next();
            if (!(eval instanceof LazyRefEval)) continue;
            LazyRefEval lazyRefEval = (LazyRefEval)eval;
            if (lazyRefEval.isSubTotal()) {
                it.remove();
            }
            if (functionCode <= 100 || !lazyRefEval.isRowHidden()) continue;
            it.remove();
        }
        return innerFunc.evaluate(list.toArray(new ValueEval[0]), srcRowIndex, srcColumnIndex);
    }
}

