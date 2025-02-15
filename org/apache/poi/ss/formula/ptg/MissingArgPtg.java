/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ScalarConstantPtg;
import org.apache.poi.util.LittleEndianOutput;

public final class MissingArgPtg
extends ScalarConstantPtg {
    private static final int SIZE = 1;
    public static final byte sid = 22;
    public static final Ptg instance = new MissingArgPtg();

    private MissingArgPtg() {
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(22 + this.getPtgClass());
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String toFormulaString() {
        return " ";
    }

    @Override
    public MissingArgPtg copy() {
        return this;
    }
}

