/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.Register;

public class PUSH
extends AbstractInstruction {
    private int register;
    private byte imm8;
    private int imm32;

    public PUSH(int register) {
        this.register = register;
        this.code = this.toCode(0x50 | register);
    }

    public PUSH(byte imm8) {
        this.imm8 = imm8;
        this.code = this.toCode(106, imm8);
    }

    public PUSH(int opcode, int imm32) {
        this.imm32 = imm32;
        this.code = this.toCode(opcode, imm32);
    }

    @Override
    public String toIntelAssembly() {
        switch (this.getOpCode()) {
            case 106: {
                return "push " + PUSH.toHexString(this.imm8, false);
            }
            case 104: {
                return "push " + PUSH.toHexString(this.imm32, false);
            }
        }
        return "push " + Register.to32(this.register);
    }
}

