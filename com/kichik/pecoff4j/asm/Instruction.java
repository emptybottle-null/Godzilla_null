/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

public interface Instruction {
    public int size();

    public byte[] toCode();

    public String toIntelAssembly();
}

