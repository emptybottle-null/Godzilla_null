/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

public class DVCSException
extends Exception {
    private static final long serialVersionUID = 389345256020131488L;
    private Throwable cause;

    public DVCSException(String string) {
        super(string);
    }

    public DVCSException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

