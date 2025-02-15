/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

public class CryptoException
extends Exception {
    private Throwable cause;

    public CryptoException() {
    }

    public CryptoException(String string) {
        super(string);
    }

    public CryptoException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

