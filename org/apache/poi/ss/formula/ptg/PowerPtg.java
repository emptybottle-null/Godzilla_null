/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public final class PowerPtg
extends ValueOperatorPtg {
    public static final byte sid = 7;
    public static final PowerPtg instance = new PowerPtg();

    private PowerPtg() {
    }

    @Override
    protected byte getSid() {
        return 7;
    }

    @Override
    public int getNumberOfOperands() {
        return 2;
    }

    @Override
    public String toFormulaString(String[] operands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(operands[0]);
        buffer.append("^");
        buffer.append(operands[1]);
        return buffer.toString();
    }

    @Override
    public PowerPtg copy() {
        return instance;
    }
}

