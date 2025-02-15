/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.ErrorEval;

public final class EvaluationException
extends Exception {
    private final ErrorEval _errorEval;

    public EvaluationException(ErrorEval errorEval) {
        this._errorEval = errorEval;
    }

    public static EvaluationException invalidValue() {
        return new EvaluationException(ErrorEval.VALUE_INVALID);
    }

    public static EvaluationException invalidRef() {
        return new EvaluationException(ErrorEval.REF_INVALID);
    }

    public static EvaluationException numberError() {
        return new EvaluationException(ErrorEval.NUM_ERROR);
    }

    public ErrorEval getErrorEval() {
        return this._errorEval;
    }
}

