/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

public class PKCSException
extends Exception {
    private Throwable cause;

    public PKCSException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public PKCSException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

