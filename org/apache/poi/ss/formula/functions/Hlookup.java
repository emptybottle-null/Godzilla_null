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

public final class Hlookup
extends Var3or4ArgFunction {
    private static final ValueEval DEFAULT_ARG3 = BoolEval.TRUE;

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        return this.evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        try {
            ValueEval lookupValue = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            TwoDEval tableArray = LookupUtils.resolveTableArrayArg(arg1);
            boolean isRangeLookup = LookupUtils.resolveRangeLookupArg(arg3, srcRowIndex, srcColumnIndex);
            int colIndex = LookupUtils.lookupIndexOfValue(lookupValue, LookupUtils.createRowVector(tableArray, 0), isRangeLookup);
            int rowIndex = LookupUtils.resolveRowOrColIndexArg(arg2, srcRowIndex, srcColumnIndex);
            LookupUtils.ValueVector resultCol = this.createResultColumnVector(tableArray, rowIndex);
            return resultCol.getItem(colIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private LookupUtils.ValueVector createResultColumnVector(TwoDEval tableArray, int rowIndex) throws EvaluationException {
        if (rowIndex >= tableArray.getHeight()) {
            throw EvaluationException.invalidRef();
        }
        return LookupUtils.createRowVector(tableArray, rowIndex);
    }
}

