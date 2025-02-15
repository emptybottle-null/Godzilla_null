/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function3Arg;
import org.apache.poi.ss.formula.functions.Function4Arg;

abstract class Var3or4ArgFunction
implements Function3Arg,
Function4Arg {
    Var3or4ArgFunction() {
    }

    @Override
    public final ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        switch (args.length) {
            case 3: {
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2]);
            }
            case 4: {
                return this.evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], args[3]);
            }
        }
        return ErrorEval.VALUE_INVALID;
    }
}

