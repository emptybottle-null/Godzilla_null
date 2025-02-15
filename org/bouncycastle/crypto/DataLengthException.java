/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.RuntimeCryptoException;

public class DataLengthException
extends RuntimeCryptoException {
    public DataLengthException() {
    }

    public DataLengthException(String string) {
        super(string);
    }
}

