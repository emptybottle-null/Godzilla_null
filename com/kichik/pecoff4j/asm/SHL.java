/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;

public class SHL
extends AbstractInstruction {
    private ModRM modrm;
    private byte imm8;

    public SHL(ModRM modrm, byte imm8) {
        this.modrm = modrm;
        this.imm8 = imm8;
        this.code = this.toCode(193, modrm, imm8);
    }

    @Override
    public String toIntelAssembly() {
        return "shl  " + Register.to32(this.modrm.reg1) + ", " + SHL.toHexString(this.imm8, false);
    }
}

