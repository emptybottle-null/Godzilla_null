/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class ExternalNameEval
implements ValueEval {
    private final EvaluationName _name;

    public ExternalNameEval(EvaluationName name) {
        this._name = name;
    }

    public EvaluationName getName() {
        return this._name;
    }

    public String toString() {
        return this.getClass().getName() + " [" + this._name.getNameText() + "]";
    }
}

