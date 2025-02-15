/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.LittleEndianOutput;

public class UnknownPtg
extends Ptg {
    private short size = 1;
    private final int _sid;

    public UnknownPtg(int sid) {
        this._sid = sid;
    }

    @Override
    public boolean isBaseToken() {
        return true;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(this._sid);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String toFormulaString() {
        return "UNKNOWN";
    }

    @Override
    public byte getDefaultOperandClass() {
        return 32;
    }

    @Override
    public UnknownPtg copy() {
        return this;
    }
}

