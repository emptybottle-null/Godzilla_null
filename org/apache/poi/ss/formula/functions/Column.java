/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function0Arg;
import org.apache.poi.ss.formula.functions.Function1Arg;

public final class Column
implements Function0Arg,
Function1Arg {
    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex) {
        return new NumberEval(srcColumnIndex + 1);
    }

    @Override
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        int rnum;
        if (arg0 instanceof AreaEval) {
            rnum = ((AreaEval)arg0).getFirstColumn();
        } else if (arg0 instanceof RefEval) {
            rnum = ((RefEval)arg0).getColumn();
        } else {
            return ErrorEval.VALUE_INVALID;
        }
        return new NumberEval(rnum + 1);
    }

    @Override
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        switch (args.length) {
            case 1: {
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0]);
            }
            case 0: {
                return new NumberEval(srcColumnIndex + 1);
            }
        }
        return ErrorEval.VALUE_INVALID;
    }
}

