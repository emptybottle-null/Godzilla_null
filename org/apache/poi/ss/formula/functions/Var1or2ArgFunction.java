/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function1Arg;
import org.apache.poi.ss.formula.functions.Function2Arg;

abstract class Var1or2ArgFunction
implements Function1Arg,
Function2Arg {
    Var1or2ArgFunction() {
    }

    @Override
    public final ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        switch (args.length) {
            case 1: {
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0]);
            }
            case 2: {
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1]);
            }
        }
        return ErrorEval.VALUE_INVALID;
    }
}

