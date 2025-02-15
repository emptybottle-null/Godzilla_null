/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class PercentPtg
extends ValueOperatorPtg {
    public static final int SIZE = 1;
    public static final byte sid = 20;
    private static final String PERCENT = "%";
    public static final PercentPtg instance = new PercentPtg();

    private PercentPtg() {
    }

    @Override
    protected byte getSid() {
        return 20;
    }

    @Override
    public int getNumberOfOperands() {
        return 1;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append(PERCENT);
        return buffer.toString();
    }

    @Override
    public PercentPtg copy() {
        return instance;
    }
}

