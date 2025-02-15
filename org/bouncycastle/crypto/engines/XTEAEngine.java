/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class XTEAEngine
implements BlockCipher {
    private static final int rounds = 32;
    private static final int block_size = 8;
    private static final int delta = -1640531527;
    private int[] _S = new int[4];
    private int[] _sum0 = new int[32];
    private int[] _sum1 = new int[32];
    private boolean _initialised = false;
    private boolean _forEncryption;

    public String getAlgorithmName() {
        return "XTEA";
    }

    public int getBlockSize() {
        return 8;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to TEA init - " + cipherParameters.getClass().getName());
        }
        this._forEncryption = bl;
        this._initialised = true;
        KeyParameter keyParameter = (KeyParameter)cipherParameters;
        this.setKey(keyParameter.getKey());
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (!this._initialised) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (n + 8 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 8 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        return this._forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
    }

    private void setKey(byte[] byArray) {
        if (byArray.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        int n = 0;
        int n2 = 0;
        while (n2 < 4) {
            this._S[n2] = this.bytesToInt(byArray, n);
            ++n2;
            n += 4;
        }
        n = 0;
        for (n2 = 0; n2 < 32; ++n2) {
            this._sum0[n2] = n + this._S[n & 3];
            this._sum1[n2] = (n -= 1640531527) + this._S[n >>> 11 & 3];
        }
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToInt(byArray, n);
        int n4 = this.bytesToInt(byArray, n + 4);
        for (int i = 0; i < 32; ++i) {
            n4 += ((n3 += (n4 << 4 ^ n4 >>> 5) + n4 ^ this._sum0[i]) << 4 ^ n3 >>> 5) + n3 ^ this._sum1[i];
        }
        this.unpackInt(n3, byArray2, n2);
        this.unpackInt(n4, byArray2, n2 + 4);
        return 8;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToInt(byArray, n);
        int n4 = this.bytesToInt(byArray, n + 4);
        for (int i = 31; i >= 0; --i) {
            n3 -= ((n4 -= (n3 << 4 ^ n3 >>> 5) + n3 ^ this._sum1[i]) << 4 ^ n4 >>> 5) + n4 ^ this._sum0[i];
        }
        this.unpackInt(n3, byArray2, n2);
        this.unpackInt(n4, byArray2, n2 + 4);
        return 8;
    }

    private int bytesToInt(byte[] byArray, int n) {
        return byArray[n++] << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n] & 0xFF;
    }

    private void unpackInt(int n, byte[] byArray, int n2) {
        byArray[n2++] = (byte)(n >>> 24);
        byArray[n2++] = (byte)(n >>> 16);
        byArray[n2++] = (byte)(n >>> 8);
        byArray[n2] = (byte)n;
    }
}

