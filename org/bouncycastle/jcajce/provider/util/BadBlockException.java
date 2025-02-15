/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.util;

import javax.crypto.BadPaddingException;

public class BadBlockException
extends BadPaddingException {
    private final Throwable cause;

    public BadBlockException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

