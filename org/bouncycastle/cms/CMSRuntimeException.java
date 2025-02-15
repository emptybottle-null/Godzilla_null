/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

public class CMSRuntimeException
extends RuntimeException {
    Exception e;

    public CMSRuntimeException(String string) {
        super(string);
    }

    public CMSRuntimeException(String string, Exception exception) {
        super(string);
        this.e = exception;
    }

    public Exception getUnderlyingException() {
        return this.e;
    }

    public Throwable getCause() {
        return this.e;
    }
}

