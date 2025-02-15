/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;

public final class Rows
extends Fixed1ArgFunction {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        int result;
        if (arg0 instanceof TwoDEval) {
            result = ((TwoDEval)arg0).getHeight();
        } else if (arg0 instanceof RefEval) {
            result = 1;
        } else {
            return ErrorEval.VALUE_INVALID;
        }
        return new NumberEval(result);
    }
}

