/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.Register;

public class ModRM {
    public final int value;
    public final int mod;
    public final int reg1;
    public final int reg2;

    public ModRM(int value) {
        this.value = value;
        this.mod = value >> 6 & 0xF;
        this.reg2 = value >> 3 & 7;
        this.reg1 = value & 7;
    }

    public byte encode() {
        return (byte)(this.mod << 6 | this.reg2 << 3 | this.reg1);
    }

    public String toIntelAssembly(int imm32) {
        switch (this.mod) {
            case 0: {
                return Register.to32(this.reg2) + ", " + Register.to32(this.reg1);
            }
            case 1: {
                return Register.to32(this.reg2) + ", [" + Register.to32(this.reg1) + AbstractInstruction.toHexString((byte)imm32, true) + "]";
            }
            case 2: {
                return Register.to32(this.reg2) + ", [" + Register.to32(this.reg1) + AbstractInstruction.toHexString(imm32, true) + "]";
            }
        }
        return null;
    }
}

