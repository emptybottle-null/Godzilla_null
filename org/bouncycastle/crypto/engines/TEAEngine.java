/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class TEAEngine
implements BlockCipher {
    private static final int rounds = 32;
    private static final int block_size = 8;
    private static final int delta = -1640531527;
    private static final int d_sum = -957401312;
    private int _a;
    private int _b;
    private int _c;
    private int _d;
    private boolean _initialised = false;
    private boolean _forEncryption;

    public String getAlgorithmName() {
        return "TEA";
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
        this._a = this.bytesToInt(byArray, 0);
        this._b = this.bytesToInt(byArray, 4);
        this._c = this.bytesToInt(byArray, 8);
        this._d = this.bytesToInt(byArray, 12);
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToInt(byArray, n);
        int n4 = this.bytesToInt(byArray, n + 4);
        int n5 = 0;
        for (int i = 0; i != 32; ++i) {
            n4 += ((n3 += (n4 << 4) + this._a ^ n4 + (n5 -= 1640531527) ^ (n4 >>> 5) + this._b) << 4) + this._c ^ n3 + n5 ^ (n3 >>> 5) + this._d;
        }
        this.unpackInt(n3, byArray2, n2);
        this.unpackInt(n4, byArray2, n2 + 4);
        return 8;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToInt(byArray, n);
        int n4 = this.bytesToInt(byArray, n + 4);
        int n5 = -957401312;
        for (int i = 0; i != 32; ++i) {
            n3 -= ((n4 -= (n3 << 4) + this._c ^ n3 + n5 ^ (n3 >>> 5) + this._d) << 4) + this._a ^ n4 + n5 ^ (n4 >>> 5) + this._b;
            n5 += 1640531527;
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

