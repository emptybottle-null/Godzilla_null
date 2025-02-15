/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;

public class CALL
extends AbstractInstruction {
    private int imm32;

    public CALL(ModRM modrm, int imm32) {
        this.imm32 = imm32;
        this.code = this.toCode(255, modrm, imm32);
    }

    public CALL(int opcode, int imm32) {
        this.imm32 = imm32;
        this.code = this.toCode(opcode, imm32);
    }

    @Override
    public String toIntelAssembly() {
        switch (this.getOpCode()) {
            case 232: {
                return "call " + CALL.toHexString(this.imm32, false) + " (" + CALL.toHexString(this.offset + this.imm32 + this.size(), false) + ")";
            }
        }
        return "call " + CALL.toHexString(this.imm32, false);
    }
}

