/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils;
import org.apache.poi.ss.formula.functions.Var2or3ArgFunction;

public final class Lookup
extends Var2or3ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            LookupUtils.ValueVector resultVector;
            LookupUtils.ValueVector lookupVector;
            ValueEval lookupValue = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            TwoDEval lookupArray = LookupUtils.resolveTableArrayArg(arg1);
            if (lookupArray.getWidth() > lookupArray.getHeight()) {
                lookupVector = Lookup.createVector(lookupArray.getRow(0));
                resultVector = Lookup.createVector(lookupArray.getRow(lookupArray.getHeight() - 1));
            } else {
                lookupVector = Lookup.createVector(lookupArray.getColumn(0));
                resultVector = Lookup.createVector(lookupArray.getColumn(lookupArray.getWidth() - 1));
            }
            assert (lookupVector.getSize() == resultVector.getSize());
            int index = LookupUtils.lookupIndexOfValue(lookupValue, lookupVector, true);
            return resultVector.getItem(index);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            ValueEval lookupValue = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            TwoDEval aeLookupVector = LookupUtils.resolveTableArrayArg(arg1);
            TwoDEval aeResultVector = LookupUtils.resolveTableArrayArg(arg2);
            LookupUtils.ValueVector lookupVector = Lookup.createVector(aeLookupVector);
            LookupUtils.ValueVector resultVector = Lookup.createVector(aeResultVector);
            if (lookupVector.getSize() > resultVector.getSize()) {
                throw new RuntimeException("Lookup vector and result vector of differing sizes not supported yet");
            }
            int index = LookupUtils.lookupIndexOfValue(lookupValue, lookupVector, true);
            return resultVector.getItem(index);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static LookupUtils.ValueVector createVector(TwoDEval ae) {
        LookupUtils.ValueVector result = LookupUtils.createVector(ae);
        if (result != null) {
            return result;
        }
        throw new RuntimeException("non-vector lookup or result areas not supported yet");
    }
}

