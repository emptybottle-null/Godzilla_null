/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;

public class RET
extends AbstractInstruction {
    public RET() {
        this.code = this.toCode(195);
    }

    @Override
    public String toIntelAssembly() {
        return "ret";
    }
}

