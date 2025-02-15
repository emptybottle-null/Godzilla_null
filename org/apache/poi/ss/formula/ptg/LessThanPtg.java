/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class LessThanPtg
extends ValueOperatorPtg {
    public static final byte sid = 9;
    private static final String LESSTHAN = "<";
    public static final LessThanPtg instance = new LessThanPtg();

    private LessThanPtg() {
    }

    @Override
    protected byte getSid() {
        return 9;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append(LESSTHAN);
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public LessThanPtg copy() {
        return instance;
    }
}

