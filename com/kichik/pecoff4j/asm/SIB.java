/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.Register;

public class SIB {
    public final int scale = (value &= 0xFF) >> 6;
    public final int index;
    public final int base;

    public SIB(int value) {
        this.index = value >> 3 & 7;
        this.base = value & 7;
    }

    public byte encode() {
        return (byte)(this.scale << 6 | this.index << 3 | this.base);
    }

    public String toString(int imm32) {
        return Register.to32(this.index) + "*" + this.scale * 2 + "+" + Register.to32(this.base) + AbstractInstruction.toHexString(imm32, true);
    }
}

