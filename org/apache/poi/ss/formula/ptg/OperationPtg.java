/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.Ptg;

public abstract class OperationPtg
extends Ptg {
    public static final int TYPE_UNARY = 0;
    public static final int TYPE_BINARY = 1;
    public static final int TYPE_FUNCTION = 2;

    protected OperationPtg() {
    }

    public abstract String toFormulaString(String[] var1);

    public abstract int getNumberOfOperands();

    @Override
    public byte getDefaultOperandClass() {
        return 32;
    }
}

