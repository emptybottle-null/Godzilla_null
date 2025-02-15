/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RC5Parameters;

public class RC532Engine
implements BlockCipher {
    private int _noRounds = 12;
    private int[] _S = null;
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;
    private boolean forEncryption;

    public String getAlgorithmName() {
        return "RC5-32";
    }

    public int getBlockSize() {
        return 8;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters)cipherParameters;
            this._noRounds = rC5Parameters.getRounds();
            this.setKey(rC5Parameters.getKey());
        } else if (cipherParameters instanceof KeyParameter) {
            KeyParameter keyParameter = (KeyParameter)cipherParameters;
            this.setKey(keyParameter.getKey());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC532 init - " + cipherParameters.getClass().getName());
        }
        this.forEncryption = bl;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        return this.forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
    }

    private void setKey(byte[] byArray) {
        int n;
        int[] nArray = new int[(byArray.length + 3) / 4];
        for (n = 0; n != byArray.length; ++n) {
            int n2 = n / 4;
            nArray[n2] = nArray[n2] + ((byArray[n] & 0xFF) << 8 * (n % 4));
        }
        this._S = new int[2 * (this._noRounds + 1)];
        this._S[0] = -1209970333;
        for (n = 1; n < this._S.length; ++n) {
            this._S[n] = this._S[n - 1] + -1640531527;
        }
        n = nArray.length > this._S.length ? 3 * nArray.length : 3 * this._S.length;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < n; ++i) {
            n3 = this._S[n5] = this.rotateLeft(this._S[n5] + n3 + n4, 3);
            n4 = nArray[n6] = this.rotateLeft(nArray[n6] + n3 + n4, n3 + n4);
            n5 = (n5 + 1) % this._S.length;
            n6 = (n6 + 1) % nArray.length;
        }
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToWord(byArray, n) + this._S[0];
        int n4 = this.bytesToWord(byArray, n + 4) + this._S[1];
        for (int i = 1; i <= this._noRounds; ++i) {
            n3 = this.rotateLeft(n3 ^ n4, n4) + this._S[2 * i];
            n4 = this.rotateLeft(n4 ^ n3, n3) + this._S[2 * i + 1];
        }
        this.wordToBytes(n3, byArray2, n2);
        this.wordToBytes(n4, byArray2, n2 + 4);
        return 8;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToWord(byArray, n);
        int n4 = this.bytesToWord(byArray, n + 4);
        for (int i = this._noRounds; i >= 1; --i) {
            n4 = this.rotateRight(n4 - this._S[2 * i + 1], n3) ^ n3;
            n3 = this.rotateRight(n3 - this._S[2 * i], n4) ^ n4;
        }
        this.wordToBytes(n3 - this._S[0], byArray2, n2);
        this.wordToBytes(n4 - this._S[1], byArray2, n2 + 4);
        return 8;
    }

    private int rotateLeft(int n, int n2) {
        return n << (n2 & 0x1F) | n >>> 32 - (n2 & 0x1F);
    }

    private int rotateRight(int n, int n2) {
        return n >>> (n2 & 0x1F) | n << 32 - (n2 & 0x1F);
    }

    private int bytesToWord(byte[] byArray, int n) {
        return byArray[n] & 0xFF | (byArray[n + 1] & 0xFF) << 8 | (byArray[n + 2] & 0xFF) << 16 | (byArray[n + 3] & 0xFF) << 24;
    }

    private void wordToBytes(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)n;
        byArray[n2 + 1] = (byte)(n >> 8);
        byArray[n2 + 2] = (byte)(n >> 16);
        byArray[n2 + 3] = (byte)(n >> 24);
    }
}

