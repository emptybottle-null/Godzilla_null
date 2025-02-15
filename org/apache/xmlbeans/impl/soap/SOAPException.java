/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.soap;

public class SOAPException
extends Exception {
    private Throwable cause;

    public SOAPException() {
        this.cause = null;
    }

    public SOAPException(String reason) {
        super(reason);
        this.cause = null;
    }

    public SOAPException(String reason, Throwable cause) {
        super(reason);
        this.initCause(cause);
    }

    public SOAPException(Throwable cause) {
        super(cause.toString());
        this.initCause(cause);
    }

    @Override
    public String getMessage() {
        String s = super.getMessage();
        if (s == null && this.cause != null) {
            return this.cause.getMessage();
        }
        return s;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        if (this.cause != null) {
            throw new IllegalStateException("Can't override cause");
        }
        if (cause == this) {
            throw new IllegalArgumentException("Self-causation not permitted");
        }
        this.cause = cause;
        return this;
    }
}

