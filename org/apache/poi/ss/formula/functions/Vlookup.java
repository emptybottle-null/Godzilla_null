/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils;
import org.apache.poi.ss.formula.functions.Var3or4ArgFunction;

public final class Vlookup
extends Var3or4ArgFunction {
    private static final ValueEval DEFAULT_ARG3 = BoolEval.TRUE;

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        return this.evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval lookup_value, ValueEval table_array, ValueEval col_index, ValueEval range_lookup) {
        try {
            boolean isRangeLookup;
            ValueEval lookupValue = OperandResolver.getSingleValue(lookup_value, srcRowIndex, srcColumnIndex);
            TwoDEval tableArray = LookupUtils.resolveTableArrayArg(table_array);
            try {
                isRangeLookup = LookupUtils.resolveRangeLookupArg(range_lookup, srcRowIndex, srcColumnIndex);
            } catch (RuntimeException e) {
                isRangeLookup = true;
            }
            int rowIndex = LookupUtils.lookupIndexOfValue(lookupValue, LookupUtils.createColumnVector(tableArray, 0), isRangeLookup);
            int colIndex = LookupUtils.resolveRowOrColIndexArg(col_index, srcRowIndex, srcColumnIndex);
            LookupUtils.ValueVector resultCol = this.createResultColumnVector(tableArray, colIndex);
            return resultCol.getItem(rowIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private LookupUtils.ValueVector createResultColumnVector(TwoDEval tableArray, int colIndex) throws EvaluationException {
        if (colIndex >= tableArray.getWidth()) {
            throw EvaluationException.invalidRef();
        }
        return LookupUtils.createColumnVector(tableArray, colIndex);
    }
}

