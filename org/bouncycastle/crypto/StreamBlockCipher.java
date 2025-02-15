/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;

public abstract class StreamBlockCipher
implements BlockCipher,
StreamCipher {
    private final BlockCipher cipher;

    protected StreamBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public final byte returnByte(byte by) {
        return this.calculateByte(by);
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too small");
        }
        if (n3 + n2 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int n4 = n;
        int n5 = n + n2;
        int n6 = n3;
        while (n4 < n5) {
            byArray2[n6++] = this.calculateByte(byArray[n4++]);
        }
        return n2;
    }

    protected abstract byte calculateByte(byte var1);
}

