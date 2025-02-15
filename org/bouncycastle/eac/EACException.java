/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac;

public class EACException
extends Exception {
    private Throwable cause;

    public EACException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public EACException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

