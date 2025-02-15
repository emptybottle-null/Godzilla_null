/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.NumberToTextConverter;

public final class NumberEval
implements NumericValueEval,
StringValueEval {
    public static final NumberEval ZERO = new NumberEval(0.0);
    private final double _value;
    private String _stringValue;

    public NumberEval(Ptg ptg) {
        if (ptg == null) {
            throw new IllegalArgumentException("ptg must not be null");
        }
        if (ptg instanceof IntPtg) {
            this._value = ((IntPtg)ptg).getValue();
        } else if (ptg instanceof NumberPtg) {
            this._value = ((NumberPtg)ptg).getValue();
        } else {
            throw new IllegalArgumentException("bad argument type (" + ptg.getClass().getName() + ")");
        }
    }

    public NumberEval(double value) {
        this._value = value;
    }

    @Override
    public double getNumberValue() {
        return this._value;
    }

    @Override
    public String getStringValue() {
        if (this._stringValue == null) {
            this._stringValue = NumberToTextConverter.toText(this._value);
        }
        return this._stringValue;
    }

    public final String toString() {
        return this.getClass().getName() + " [" + this.getStringValue() + "]";
    }
}

