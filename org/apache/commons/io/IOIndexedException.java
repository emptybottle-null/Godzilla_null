/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.IOException;

public class IOIndexedException
extends IOException {
    private static final long serialVersionUID = 1L;
    private final int index;

    public IOIndexedException(int index, Throwable cause) {
        super(IOIndexedException.toMessage(index, cause), cause);
        this.index = index;
    }

    protected static String toMessage(int index, Throwable cause) {
        String unspecified = "Null";
        String name = cause == null ? "Null" : cause.getClass().getSimpleName();
        String msg = cause == null ? "Null" : cause.getMessage();
        return String.format("%s #%,d: %s", name, index, msg);
    }

    public int getIndex() {
        return this.index;
    }
}

