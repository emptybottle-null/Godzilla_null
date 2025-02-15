/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.HPSFException;

public class MarkUnsupportedException
extends HPSFException {
    public MarkUnsupportedException() {
    }

    public MarkUnsupportedException(String msg) {
        super(msg);
    }

    public MarkUnsupportedException(Throwable reason) {
        super(reason);
    }

    public MarkUnsupportedException(String msg, Throwable reason) {
        super(msg, reason);
    }
}

