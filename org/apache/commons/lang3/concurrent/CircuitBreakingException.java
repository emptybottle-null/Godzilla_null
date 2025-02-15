/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

public class CircuitBreakingException
extends RuntimeException {
    private static final long serialVersionUID = 1408176654686913340L;

    public CircuitBreakingException() {
    }

    public CircuitBreakingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircuitBreakingException(String message) {
        super(message);
    }

    public CircuitBreakingException(Throwable cause) {
        super(cause);
    }
}

