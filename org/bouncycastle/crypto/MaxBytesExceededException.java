/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.RuntimeCryptoException;

public class MaxBytesExceededException
extends RuntimeCryptoException {
    public MaxBytesExceededException() {
    }

    public MaxBytesExceededException(String string) {
        super(string);
    }
}

