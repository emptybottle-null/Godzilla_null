/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.path;

public class CertPathValidationException
extends Exception {
    private final Exception cause;

    public CertPathValidationException(String string) {
        this(string, null);
    }

    public CertPathValidationException(String string, Exception exception) {
        super(string);
        this.cause = exception;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

