/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.util.LittleEndianOutput;

public final class ParenthesisPtg
extends ControlPtg {
    private static final int SIZE = 1;
    public static final byte sid = 21;
    public static final ParenthesisPtg instance = new ParenthesisPtg();

    private ParenthesisPtg() {
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(21 + this.getPtgClass());
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String toFormulaString() {
        return "()";
    }

    public String toFormulaString(String[] operands) {
        return "(" + operands[0] + ")";
    }

    @Override
    public ParenthesisPtg copy() {
        return instance;
    }
}

