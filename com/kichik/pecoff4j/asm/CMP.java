/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;

public class CMP
extends AbstractInstruction {
    private ModRM modrm;
    private byte imm8;

    public CMP(ModRM modrm, byte imm8) {
        this.modrm = modrm;
        this.imm8 = imm8;
        this.code = this.toCode(59, modrm, imm8);
    }

    @Override
    public String toIntelAssembly() {
        return "cmp  " + Register.to32(this.modrm.reg2) + ", [" + Register.to32(this.modrm.reg1) + CMP.toHexString(this.imm8, true) + "]";
    }
}

