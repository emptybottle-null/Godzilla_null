/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel;

import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.spel.CodeFlow;

public interface CompilablePropertyAccessor
extends PropertyAccessor,
Opcodes {
    public boolean isCompilable();

    public Class<?> getPropertyType();

    public void generateCode(String var1, MethodVisitor var2, CodeFlow var3);
}

