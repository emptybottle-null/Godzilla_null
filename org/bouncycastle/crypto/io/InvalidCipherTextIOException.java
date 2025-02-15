/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import org.bouncycastle.crypto.io.CipherIOException;

public class InvalidCipherTextIOException
extends CipherIOException {
    private static final long serialVersionUID = 1L;

    public InvalidCipherTextIOException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

