/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.dump;

import java.io.IOException;

public class DumpArchiveException
extends IOException {
    private static final long serialVersionUID = 1L;

    public DumpArchiveException() {
    }

    public DumpArchiveException(String msg) {
        super(msg);
    }

    public DumpArchiveException(Throwable cause) {
        this.initCause(cause);
    }

    public DumpArchiveException(String msg, Throwable cause) {
        super(msg);
        this.initCause(cause);
    }
}

