/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

public class NotImplementedException
extends RuntimeException {
    private static final long serialVersionUID = -5840703336495141301L;

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, NotImplementedException cause) {
        super(message, cause);
    }
}

