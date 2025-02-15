/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.HPSFRuntimeException;

public class IllegalPropertySetDataException
extends HPSFRuntimeException {
    public IllegalPropertySetDataException() {
    }

    public IllegalPropertySetDataException(String msg) {
        super(msg);
    }

    public IllegalPropertySetDataException(Throwable reason) {
        super(reason);
    }

    public IllegalPropertySetDataException(String msg, Throwable reason) {
        super(msg, reason);
    }
}

