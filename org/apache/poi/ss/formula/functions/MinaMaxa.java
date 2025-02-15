/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.MathX;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

public abstract class MinaMaxa
extends MultiOperandNumericFunction {
    public static final Function MAXA = new MinaMaxa(){

        @Override
        protected double evaluate(double[] values) {
            return values.length > 0 ? MathX.max(values) : 0.0;
        }
    };
    public static final Function MINA = new MinaMaxa(){

        @Override
        protected double evaluate(double[] values) {
            return values.length > 0 ? MathX.min(values) : 0.0;
        }
    };

    protected MinaMaxa() {
        super(true, true);
    }
}

