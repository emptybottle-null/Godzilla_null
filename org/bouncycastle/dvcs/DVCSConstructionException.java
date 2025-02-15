/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.dvcs.DVCSException;

public class DVCSConstructionException
extends DVCSException {
    private static final long serialVersionUID = 660035299653583980L;

    public DVCSConstructionException(String string) {
        super(string);
    }

    public DVCSConstructionException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

