/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;

public final class Countblank
extends Fixed1ArgFunction {
    private static final CountUtils.I_MatchPredicate predicate = new CountUtils.I_MatchPredicate(){

        @Override
        public boolean matches(ValueEval valueEval) {
            return valueEval == BlankEval.instance || valueEval instanceof StringEval && ((StringEval)valueEval).getStringValue().isEmpty();
        }
    };

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        double result;
        if (arg0 instanceof RefEval) {
            result = CountUtils.countMatchingCellsInRef((RefEval)arg0, predicate);
        } else if (arg0 instanceof ThreeDEval) {
            result = CountUtils.countMatchingCellsInArea((ThreeDEval)arg0, predicate);
        } else {
            throw new IllegalArgumentException("Bad range arg type (" + arg0.getClass().getName() + ")");
        }
        return new NumberEval(result);
    }
}

