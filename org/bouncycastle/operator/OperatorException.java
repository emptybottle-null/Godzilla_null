/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

public class OperatorException
extends Exception {
    private Throwable cause;

    public OperatorException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public OperatorException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

