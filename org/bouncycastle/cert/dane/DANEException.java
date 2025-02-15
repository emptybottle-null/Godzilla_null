/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.dane;

public class DANEException
extends Exception {
    private Throwable cause;

    public DANEException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public DANEException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

