/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.CryptoException;

public class InvalidCipherTextException
extends CryptoException {
    public InvalidCipherTextException() {
    }

    public InvalidCipherTextException(String string) {
        super(string);
    }

    public InvalidCipherTextException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

