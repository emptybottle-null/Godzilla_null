/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

public class HPSFRuntimeException
extends RuntimeException {
    private static final long serialVersionUID = -7804271670232727159L;
    private Throwable reason;

    public HPSFRuntimeException() {
    }

    public HPSFRuntimeException(String msg) {
        super(msg);
    }

    public HPSFRuntimeException(Throwable reason) {
        this.reason = reason;
    }

    public HPSFRuntimeException(String msg, Throwable reason) {
        super(msg);
        this.reason = reason;
    }

    public Throwable getReason() {
        return this.reason;
    }
}

