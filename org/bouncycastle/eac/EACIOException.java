/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.eac;

import java.io.IOException;

public class EACIOException
extends IOException {
    private Throwable cause;

    public EACIOException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public EACIOException(String string) {
        super(string);
    }

    public Throwable getCause() {
        return this.cause;
    }
}

