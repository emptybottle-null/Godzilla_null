/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.ValueEval;

public final class FunctionNameEval
implements ValueEval {
    private final String _functionName;

    public FunctionNameEval(String functionName) {
        this._functionName = functionName;
    }

    public String getFunctionName() {
        return this._functionName;
    }

    public String toString() {
        return this.getClass().getName() + " [" + this._functionName + "]";
    }
}

