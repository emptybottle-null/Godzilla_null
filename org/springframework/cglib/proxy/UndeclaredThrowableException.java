/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import org.springframework.cglib.core.CodeGenerationException;

public class UndeclaredThrowableException
extends CodeGenerationException {
    public UndeclaredThrowableException(Throwable t) {
        super(t);
    }

    public Throwable getUndeclaredThrowable() {
        return this.getCause();
    }
}

