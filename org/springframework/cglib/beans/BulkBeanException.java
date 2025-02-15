/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.beans;

public class BulkBeanException
extends RuntimeException {
    private int index;
    private Throwable cause;

    public BulkBeanException(String message, int index) {
        super(message);
        this.index = index;
    }

    public BulkBeanException(Throwable cause, int index) {
        super(cause.getMessage());
        this.index = index;
        this.cause = cause;
    }

    public int getIndex() {
        return this.index;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

