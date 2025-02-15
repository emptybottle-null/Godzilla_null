/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.StringPtg;

public final class StringEval
implements StringValueEval {
    public static final StringEval EMPTY_INSTANCE = new StringEval("");
    private final String _value;

    public StringEval(Ptg ptg) {
        this(((StringPtg)ptg).getValue());
    }

    public StringEval(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        this._value = value;
    }

    @Override
    public String getStringValue() {
        return this._value;
    }

    public String toString() {
        return this.getClass().getName() + " [" + this._value + "]";
    }
}

