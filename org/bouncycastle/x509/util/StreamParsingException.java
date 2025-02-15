/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509.util;

public class StreamParsingException
extends Exception {
    Throwable _e;

    public StreamParsingException(String string, Throwable throwable) {
        super(string);
        this._e = throwable;
    }

    public Throwable getCause() {
        return this._e;
    }
}

