/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl;

import java.io.IOException;

public class PEMException
extends IOException {
    Exception underlying;

    public PEMException(String string) {
        super(string);
    }

    public PEMException(String string, Exception exception) {
        super(string);
        this.underlying = exception;
    }

    public Exception getUnderlyingException() {
        return this.underlying;
    }

    public Throwable getCause() {
        return this.underlying;
    }
}

