/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.IDStarAlgorithm;

public final class DSum
implements IDStarAlgorithm {
    private double totalValue = 0.0;

    @Override
    public boolean processMatch(ValueEval eval) {
        if (eval instanceof NumericValueEval) {
            double currentValue = ((NumericValueEval)eval).getNumberValue();
            this.totalValue += currentValue;
        }
        return true;
    }

    @Override
    public ValueEval getResult() {
        return new NumberEval(this.totalValue);
    }
}

