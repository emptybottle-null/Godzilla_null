/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class MultiplyPtg
extends ValueOperatorPtg {
    public static final byte sid = 5;
    public static final MultiplyPtg instance = new MultiplyPtg();

    private MultiplyPtg() {
    }

    @Override
    protected byte getSid() {
        return 5;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append("*");
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public MultiplyPtg copy() {
        return instance;
    }
}

