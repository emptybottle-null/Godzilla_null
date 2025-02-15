/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import java.io.IOException;

public class TSPIOException
extends IOException {
    Throwable underlyingException;

    public TSPIOException(String string) {
        super(string);
    }

    public TSPIOException(String string, Throwable throwable) {
        super(string);
        this.underlyingException = throwable;
    }

    public Exception getUnderlyingException() {
        return (Exception)this.underlyingException;
    }

    public Throwable getCause() {
        return this.underlyingException;
    }
}

