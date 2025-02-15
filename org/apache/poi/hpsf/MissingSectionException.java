/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.HPSFRuntimeException;

public class MissingSectionException
extends HPSFRuntimeException {
    public MissingSectionException() {
    }

    public MissingSectionException(String msg) {
        super(msg);
    }

    public MissingSectionException(Throwable reason) {
        super(reason);
    }

    public MissingSectionException(String msg, Throwable reason) {
        super(msg, reason);
    }
}

