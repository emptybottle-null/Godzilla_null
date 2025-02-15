/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class UnaryMinusPtg
extends ValueOperatorPtg {
    public static final byte sid = 19;
    private static final String MINUS = "-";
    public static final UnaryMinusPtg instance = new UnaryMinusPtg();

    private UnaryMinusPtg() {
    }

    @Override
    protected byte getSid() {
        return 19;
    }

    @Override
    public int getNumberOfOperands() {
        return 1;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(MINUS);
        buffer.append(operands[0]);
        return buffer.toString();
    }

    @Override
    public UnaryMinusPtg copy() {
        return instance;
    }
}

