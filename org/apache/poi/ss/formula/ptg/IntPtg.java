/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ScalarConstantPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class IntPtg
extends ScalarConstantPtg {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 65535;
    public static final int SIZE = 3;
    public static final byte sid = 30;
    private final int field_1_value;

    public static boolean isInRange(int i) {
        return i >= 0 && i <= 65535;
    }

    public IntPtg(LittleEndianInput in) {
        this(in.readUShort());
    }

    public IntPtg(int value) {
        if (!IntPtg.isInRange(value)) {
            throw new IllegalArgumentException("value is out of range: " + value);
        }
        this.field_1_value = value;
    }

    public int getValue() {
        return this.field_1_value;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(30 + this.getPtgClass());
        out.writeShort(this.getValue());
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public String toFormulaString() {
        return String.valueOf(this.getValue());
    }

    @Override
    public IntPtg copy() {
        return this;
    }
}

