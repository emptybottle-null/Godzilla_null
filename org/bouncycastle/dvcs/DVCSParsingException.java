/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.dvcs.DVCSException;

public class DVCSParsingException
extends DVCSException {
    private static final long serialVersionUID = -7895880961377691266L;

    public DVCSParsingException(String string) {
        super(string);
    }

    public DVCSParsingException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

