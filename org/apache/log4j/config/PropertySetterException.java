/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.config;

public class PropertySetterException
extends Exception {
    private static final long serialVersionUID = -1352613734254235861L;
    protected Throwable rootCause;

    public PropertySetterException(String msg) {
        super(msg);
    }

    public PropertySetterException(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    public String getMessage() {
        String msg = super.getMessage();
        if (msg == null && this.rootCause != null) {
            msg = this.rootCause.getMessage();
        }
        return msg;
    }
}

