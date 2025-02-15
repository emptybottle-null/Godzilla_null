/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;

public class SUB
extends AbstractInstruction {
    private ModRM modrm;
    private int imm32;

    public SUB(ModRM modrm, int imm32) {
        this.modrm = modrm;
        this.imm32 = imm32;
        this.code = this.toCode(129, modrm, imm32);
    }

    @Override
    public String toIntelAssembly() {
        return "sub  " + Register.to32(this.modrm.reg1) + ", " + SUB.toHexString(this.imm32, false);
    }
}

