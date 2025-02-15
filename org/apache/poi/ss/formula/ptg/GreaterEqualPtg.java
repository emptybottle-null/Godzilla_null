/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class GreaterEqualPtg
extends ValueOperatorPtg {
    public static final int SIZE = 1;
    public static final byte sid = 12;
    public static final GreaterEqualPtg instance = new GreaterEqualPtg();

    private GreaterEqualPtg() {
    }

    @Override
    protected byte getSid() {
        return 12;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append(">=");
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public GreaterEqualPtg copy() {
        return instance;
    }
}

