/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;

public class TEST
extends AbstractInstruction {
    private ModRM modrm;

    public TEST(ModRM modrm) {
        this.modrm = modrm;
        this.code = this.toCode(133, modrm);
    }

    @Override
    public String toIntelAssembly() {
        return "test " + Register.to32(this.modrm.reg1) + ", " + Register.to32(this.modrm.reg2);
    }
}

