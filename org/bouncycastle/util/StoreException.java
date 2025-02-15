/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

public class StoreException
extends RuntimeException {
    private Throwable _e;

    public StoreException(String string, Throwable throwable) {
        super(string);
        this._e = throwable;
    }

    public Throwable getCause() {
        return this._e;
    }
}

