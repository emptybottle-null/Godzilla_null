/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.Ptg;

public abstract class ScalarConstantPtg
extends Ptg {
    @Override
    public final boolean isBaseToken() {
        return true;
    }

    @Override
    public final byte getDefaultOperandClass() {
        return 32;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName()).append(" [");
        sb.append(this.toFormulaString());
        sb.append("]");
        return sb.toString();
    }
}

