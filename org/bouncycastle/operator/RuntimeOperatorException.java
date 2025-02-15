/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

public class RuntimeOperatorException
extends RuntimeException {
    private Throwable cause;

    public RuntimeOperatorException(String string) {
        super(string);
    }

    public RuntimeOperatorException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

