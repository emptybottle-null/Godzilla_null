/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class UnaryPlusPtg
extends ValueOperatorPtg {
    public static final byte sid = 18;
    private static final String ADD = "+";
    public static final UnaryPlusPtg instance = new UnaryPlusPtg();

    private UnaryPlusPtg() {
    }

    @Override
    protected byte getSid() {
        return 18;
    }

    @Override
    public int getNumberOfOperands() {
        return 1;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(ADD);
        buffer.append(operands[0]);
        return buffer.toString();
    }

    @Override
    public UnaryPlusPtg copy() {
        return instance;
    }
}

