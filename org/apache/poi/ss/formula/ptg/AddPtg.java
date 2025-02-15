/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class AddPtg
extends ValueOperatorPtg {
    public static final byte sid = 3;
    private static final String ADD = "+";
    public static final AddPtg instance = new AddPtg();

    private AddPtg() {
    }

    @Override
    protected byte getSid() {
        return 3;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append(ADD);
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public AddPtg copy() {
        return instance;
    }
}

