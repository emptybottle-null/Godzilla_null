/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.Register;

public class POP
extends AbstractInstruction {
    private int register;

    public POP(int register) {
        this.register = register;
        this.code = this.toCode(0x58 | register);
    }

    @Override
    public String toIntelAssembly() {
        return "pop  " + Register.to32(this.register);
    }
}

