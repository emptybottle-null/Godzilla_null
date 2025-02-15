/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class RC6Engine
implements BlockCipher {
    private static final int wordSize = 32;
    private static final int bytesPerWord = 4;
    private static final int _noRounds = 20;
    private int[] _S = null;
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;
    private static final int LGW = 5;
    private boolean forEncryption;

    public String getAlgorithmName() {
        return "RC6";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to RC6 init - " + cipherParameters.getClass().getName());
        }
        KeyParameter keyParameter = (KeyParameter)cipherParameters;
        this.forEncryption = bl;
        this.setKey(keyParameter.getKey());
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.getBlockSize();
        if (this._S == null) {
            throw new IllegalStateException("RC6 engine not initialised");
        }
        if (n + n3 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + n3 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        return this.forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
    }

    private void setKey(byte[] byArray) {
        int n;
        int n2 = (byArray.length + 3) / 4;
        if (n2 == 0) {
            n2 = 1;
        }
        int[] nArray = new int[(byArray.length + 4 - 1) / 4];
        for (n = byArray.length - 1; n >= 0; --n) {
            nArray[n / 4] = (nArray[n / 4] << 8) + (byArray[n] & 0xFF);
        }
        this._S = new int[44];
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
        int n3 = this.bytesToWord(byArray, n);
        int n4 = this.bytesToWord(byArray, n + 4);
        int n5 = this.bytesToWord(byArray, n + 8);
        int n6 = this.bytesToWord(byArray, n + 12);
        n4 += this._S[0];
        n6 += this._S[1];
        for (int i = 1; i <= 20; ++i) {
            int n7 = 0;
            int n8 = 0;
            n7 = n4 * (2 * n4 + 1);
            n7 = this.rotateLeft(n7, 5);
            n8 = n6 * (2 * n6 + 1);
            n8 = this.rotateLeft(n8, 5);
            n3 ^= n7;
            n3 = this.rotateLeft(n3, n8);
            n5 ^= n8;
            n5 = this.rotateLeft(n5, n7);
            int n9 = n3 += this._S[2 * i];
            n3 = n4;
            n4 = n5 += this._S[2 * i + 1];
            n5 = n6;
            n6 = n9;
        }
        this.wordToBytes(n3 += this._S[42], byArray2, n2);
        this.wordToBytes(n4, byArray2, n2 + 4);
        this.wordToBytes(n5 += this._S[43], byArray2, n2 + 8);
        this.wordToBytes(n6, byArray2, n2 + 12);
        return 16;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.bytesToWord(byArray, n);
        int n4 = this.bytesToWord(byArray, n + 4);
        int n5 = this.bytesToWord(byArray, n + 8);
        int n6 = this.bytesToWord(byArray, n + 12);
        n5 -= this._S[43];
        n3 -= this._S[42];
        for (int i = 20; i >= 1; --i) {
            int n7 = 0;
            int n8 = 0;
            int n9 = n6;
            n6 = n5;
            n5 = n4;
            n4 = n3;
            n3 = n9;
            n7 = n4 * (2 * n4 + 1);
            n7 = this.rotateLeft(n7, 5);
            n8 = n6 * (2 * n6 + 1);
            n8 = this.rotateLeft(n8, 5);
            n5 -= this._S[2 * i + 1];
            n5 = this.rotateRight(n5, n7);
            n5 ^= n8;
            n3 -= this._S[2 * i];
            n3 = this.rotateRight(n3, n8);
            n3 ^= n7;
        }
        this.wordToBytes(n3, byArray2, n2);
        this.wordToBytes(n4 -= this._S[0], byArray2, n2 + 4);
        this.wordToBytes(n5, byArray2, n2 + 8);
        this.wordToBytes(n6 -= this._S[1], byArray2, n2 + 12);
        return 16;
    }

    private int rotateLeft(int n, int n2) {
        return n << n2 | n >>> -n2;
    }

    private int rotateRight(int n, int n2) {
        return n >>> n2 | n << -n2;
    }

    private int bytesToWord(byte[] byArray, int n) {
        int n2 = 0;
        for (int i = 3; i >= 0; --i) {
            n2 = (n2 << 8) + (byArray[i + n] & 0xFF);
        }
        return n2;
    }

    private void wordToBytes(int n, byte[] byArray, int n2) {
        for (int i = 0; i < 4; ++i) {
            byArray[i + n2] = (byte)n;
            n >>>= 8;
        }
    }
}

