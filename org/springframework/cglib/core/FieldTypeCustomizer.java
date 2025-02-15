/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.asm.Type;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.KeyFactoryCustomizer;

public interface FieldTypeCustomizer
extends KeyFactoryCustomizer {
    public void customize(CodeEmitter var1, int var2, Type var3);

    public Type getOutType(int var1, Type var2);
}

