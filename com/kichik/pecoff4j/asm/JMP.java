/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;

public class JMP
extends AbstractInstruction {
    private byte imm8;
    private int imm32;

    public JMP(byte imm8) {
        this.imm8 = imm8;
        this.code = this.toCode(235, imm8);
    }

    public JMP(int imm32) {
        this.imm32 = imm32;
        this.code = this.toCode(233, imm32);
    }

    @Override
    public String toIntelAssembly() {
        switch (this.getOpCode()) {
            case 233: {
                return "jmp  " + JMP.toHexString(this.imm32, false);
            }
        }
        return "jmp  " + JMP.toHexString(this.imm8, false);
    }
}

