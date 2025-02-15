/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;

public class JumpIfInstruction
extends AbstractInstruction {
    private int op;
    private int imm32;

    public JumpIfInstruction(int op, int imm32) {
        this.op = op;
        this.imm32 = imm32;
        this.code = this.toCode(15, new ModRM(op), imm32);
    }

    public String getOp() {
        switch (this.op) {
            case 133: {
                return "jnz";
            }
            case 141: {
                return "jge";
            }
        }
        return "???";
    }

    @Override
    public String toIntelAssembly() {
        return this.getOp() + "  " + JumpIfInstruction.toHexString(this.imm32, false) + " (" + JumpIfInstruction.toHexString(this.offset + this.imm32 + this.size(), false) + ")";
    }
}

