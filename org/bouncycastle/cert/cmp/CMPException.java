/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

public class CMPException
extends Exception {
    private Throwable cause;

    public CMPException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public CMPException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

