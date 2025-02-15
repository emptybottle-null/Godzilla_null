/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.IDStarAlgorithm;

public final class DGet
implements IDStarAlgorithm {
    private ValueEval result;

    @Override
    public boolean processMatch(ValueEval eval) {
        if (this.result == null) {
            this.result = eval;
        } else if (this.result instanceof BlankEval) {
            this.result = eval;
        } else if (!(eval instanceof BlankEval)) {
            this.result = ErrorEval.NUM_ERROR;
            return false;
        }
        return true;
    }

    @Override
    public ValueEval getResult() {
        if (this.result == null) {
            return ErrorEval.VALUE_INVALID;
        }
        if (this.result instanceof BlankEval) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            if (OperandResolver.coerceValueToString(OperandResolver.getSingleValue(this.result, 0, 0)).isEmpty()) {
                return ErrorEval.VALUE_INVALID;
            }
            return this.result;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}

