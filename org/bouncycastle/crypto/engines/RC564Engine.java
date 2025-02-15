/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RC5Parameters;

public class RC564Engine
implements BlockCipher {
    private static final int wordSize = 64;
    private static final int bytesPerWord = 8;
    private int _noRounds = 12;
    private long[] _S = null;
    private static final long P64 = -5196783011329398165L;
    private static final long Q64 = -7046029254386353131L;
    private boolean forEncryption;

    public String getAlgorithmName() {
        return "RC5-64";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof RC5Parameters)) {
            throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + cipherParameters.getClass().getName());
        }
        RC5Parameters rC5Parameters = (RC5Parameters)cipherParameters;
        this.forEncryption = bl;
        this._noRounds = rC5Parameters.getRounds();
        this.setKey(rC5Parameters.getKey());
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        return this.forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
    }

    private void setKey(byte[] byArray) {
        int n;
        long[] lArray = new long[(byArray.length + 7) / 8];
        for (n = 0; n != byArray.length; ++n) {
            int n2 = n / 8;
            lArray[n2] = lArray[n2] + ((long)(byArray[n] & 0xFF) << 8 * (n % 8));
        }
        this._S = new long[2 * (this._noRounds + 1)];
        this._S[0] = -5196783011329398165L;
        for (n = 1; n < this._S.length; ++n) {
            this._S[n] = this._S[n - 1] + -7046029254386353131L;
        }
        n = lArray.length > this._S.length ? 3 * lArray.length : 3 * this._S.length;
        long l = 0L;
        long l2 = 0L;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < n; ++i) {
            l = this._S[n3] = this.rotateLeft(this._S[n3] + l + l2, 3L);
            l2 = lArray[n4] = this.rotateLeft(lArray[n4] + l + l2, l + l2);
            n3 = (n3 + 1) % this._S.length;
            n4 = (n4 + 1) % lArray.length;
        }
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        long l = this.bytesToWord(byArray, n) + this._S[0];
        long l2 = this.bytesToWord(byArray, n + 8) + this._S[1];
        for (int i = 1; i <= this._noRounds; ++i) {
            l = this.rotateLeft(l ^ l2, l2) + this._S[2 * i];
            l2 = this.rotateLeft(l2 ^ l, l) + this._S[2 * i + 1];
        }
        this.wordToBytes(l, byArray2, n2);
        this.wordToBytes(l2, byArray2, n2 + 8);
        return 16;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        long l = this.bytesToWord(byArray, n);
        long l2 = this.bytesToWord(byArray, n + 8);
        for (int i = this._noRounds; i >= 1; --i) {
            l2 = this.rotateRight(l2 - this._S[2 * i + 1], l) ^ l;
            l = this.rotateRight(l - this._S[2 * i], l2) ^ l2;
        }
        this.wordToBytes(l - this._S[0], byArray2, n2);
        this.wordToBytes(l2 - this._S[1], byArray2, n2 + 8);
        return 16;
    }

    private long rotateLeft(long l, long l2) {
        return l << (int)(l2 & 0x3FL) | l >>> (int)(64L - (l2 & 0x3FL));
    }

    private long rotateRight(long l, long l2) {
        return l >>> (int)(l2 & 0x3FL) | l << (int)(64L - (l2 & 0x3FL));
    }

    private long bytesToWord(byte[] byArray, int n) {
        long l = 0L;
        for (int i = 7; i >= 0; --i) {
            l = (l << 8) + (long)(byArray[i + n] & 0xFF);
        }
        return l;
    }

    private void wordToBytes(long l, byte[] byArray, int n) {
        for (int i = 0; i < 8; ++i) {
            byArray[i + n] = (byte)l;
            l >>>= 8;
        }
    }
}

