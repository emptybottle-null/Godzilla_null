/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert;

import java.io.IOException;

public class CertIOException
extends IOException {
    private Throwable cause;

    public CertIOException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public CertIOException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

