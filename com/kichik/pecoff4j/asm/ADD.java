/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;

public class ADD
extends AbstractInstruction {
    private ModRM modrm;
    private byte imm8;
    private int imm32;

    public ADD(ModRM modrm, byte imm8) {
        this.modrm = modrm;
        this.imm8 = imm8;
        this.code = this.toCode(131, modrm, imm8);
    }

    public ADD(int opcode, ModRM modrm, int imm32) {
        this.modrm = modrm;
        this.imm32 = imm32;
        this.code = this.toCode(opcode, modrm, imm32);
    }

    @Override
    public String toIntelAssembly() {
        switch (this.getOpCode()) {
            case 3: {
                return "add  " + this.modrm.toIntelAssembly(this.imm32);
            }
        }
        return "add  " + Register.to32(this.modrm.reg1) + ", " + ADD.toHexString(this.imm8, false);
    }
}

