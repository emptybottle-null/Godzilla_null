/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class ConcatPtg
extends ValueOperatorPtg {
    public static final byte sid = 8;
    private static final String CONCAT = "&";
    public static final ConcatPtg instance = new ConcatPtg();

    private ConcatPtg() {
    }

    @Override
    protected byte getSid() {
        return 8;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append(CONCAT);
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public ConcatPtg copy() {
        return instance;
    }
}

