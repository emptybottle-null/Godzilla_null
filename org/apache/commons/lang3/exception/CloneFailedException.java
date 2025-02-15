/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.exception;

public class CloneFailedException
extends RuntimeException {
    private static final long serialVersionUID = 20091223L;

    public CloneFailedException(String message) {
        super(message);
    }

    public CloneFailedException(Throwable cause) {
        super(cause);
    }

    public CloneFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

