/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function0Arg;

public abstract class Fixed0ArgFunction
implements Function0Arg {
    @Override
    public final ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 0) {
            return ErrorEval.VALUE_INVALID;
        }
        return this.evaluate(srcRowIndex, srcColumnIndex);
    }
}

