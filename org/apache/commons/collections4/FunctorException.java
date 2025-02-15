/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

public class FunctorException
extends RuntimeException {
    private static final long serialVersionUID = -4704772662059351193L;

    public FunctorException() {
    }

    public FunctorException(String msg) {
        super(msg);
    }

    public FunctorException(Throwable rootCause) {
        super(rootCause);
    }

    public FunctorException(String msg, Throwable rootCause) {
        super(msg, rootCause);
    }
}

