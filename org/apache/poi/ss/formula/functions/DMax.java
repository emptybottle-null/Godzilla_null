/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.IDStarAlgorithm;

public final class DMax
implements IDStarAlgorithm {
    private ValueEval maximumValue;

    @Override
    public boolean processMatch(ValueEval eval) {
        if (eval instanceof NumericValueEval) {
            if (this.maximumValue == null) {
                this.maximumValue = eval;
            } else {
                double oldValue;
                double currentValue = ((NumericValueEval)eval).getNumberValue();
                if (currentValue > (oldValue = ((NumericValueEval)this.maximumValue).getNumberValue())) {
                    this.maximumValue = eval;
                }
            }
        }
        return true;
    }

    @Override
    public ValueEval getResult() {
        if (this.maximumValue == null) {
            return NumberEval.ZERO;
        }
        return this.maximumValue;
    }
}

