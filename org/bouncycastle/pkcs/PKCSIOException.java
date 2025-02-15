/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import java.io.IOException;

public class PKCSIOException
extends IOException {
    private Throwable cause;

    public PKCSIOException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public PKCSIOException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

