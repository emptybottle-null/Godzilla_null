/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class GreaterThanPtg
extends ValueOperatorPtg {
    public static final byte sid = 13;
    private static final String GREATERTHAN = ">";
    public static final GreaterThanPtg instance = new GreaterThanPtg();

    private GreaterThanPtg() {
    }

    @Override
    protected byte getSid() {
        return 13;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append(GREATERTHAN);
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public GreaterThanPtg copy() {
        return instance;
    }
}

