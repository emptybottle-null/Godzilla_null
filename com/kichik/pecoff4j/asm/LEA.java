/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;
import com.kichik.pecoff4j.asm.SIB;

public class LEA
extends AbstractInstruction {
    private ModRM modrm;
    private SIB sib;
    private int imm32;

    public LEA(ModRM modrm, int imm32) {
        this.modrm = modrm;
        this.imm32 = imm32;
        this.code = this.toCode(141, modrm, imm32);
    }

    public LEA(ModRM modrm, SIB sib, int imm32) {
        this.modrm = modrm;
        this.sib = sib;
        this.imm32 = imm32;
        this.code = this.toCode(141, modrm, sib, imm32);
    }

    @Override
    public String toIntelAssembly() {
        if (this.sib != null) {
            return "lea  " + Register.to32(this.modrm.reg2) + ", [" + this.sib.toString(this.imm32) + "]";
        }
        return "lea  " + Register.to32(this.modrm.reg2) + ", [" + Register.to32(this.modrm.reg1) + LEA.toHexString(this.imm32, true) + "]";
    }
}

