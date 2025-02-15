/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class SubtractPtg
extends ValueOperatorPtg {
    public static final byte sid = 4;
    public static final SubtractPtg instance = new SubtractPtg();

    private SubtractPtg() {
    }

    @Override
    protected byte getSid() {
        return 4;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append("-");
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public SubtractPtg copy() {
        return instance;
    }
}

