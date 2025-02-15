/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.HPSFRuntimeException;

public class NoSingleSectionException
extends HPSFRuntimeException {
    public NoSingleSectionException() {
    }

    public NoSingleSectionException(String msg) {
        super(msg);
    }

    public NoSingleSectionException(Throwable reason) {
        super(reason);
    }

    public NoSingleSectionException(String msg, Throwable reason) {
        super(msg, reason);
    }
}

