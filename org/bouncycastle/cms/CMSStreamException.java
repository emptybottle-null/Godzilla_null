/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;

public class CMSStreamException
extends IOException {
    private final Throwable underlying;

    CMSStreamException(String string) {
        super(string);
        this.underlying = null;
    }

    CMSStreamException(String string, Throwable throwable) {
        super(string);
        this.underlying = throwable;
    }

    public Throwable getCause() {
        return this.underlying;
    }
}

