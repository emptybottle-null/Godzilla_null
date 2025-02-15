/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;

public class JGE
extends AbstractInstruction {
    private byte imm8;

    public JGE(byte imm8) {
        this.imm8 = imm8;
        this.code = this.toCode(125, imm8);
    }

    @Override
    public String toIntelAssembly() {
        return "jge  " + JGE.toHexString(this.imm8, true);
    }
}

