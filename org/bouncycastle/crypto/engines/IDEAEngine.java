/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class IDEAEngine
implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private int[] workingKey = null;
    private static final int MASK = 65535;
    private static final int BASE = 65537;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = this.generateWorkingKey(bl, ((KeyParameter)cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "IDEA";
    }

    public int getBlockSize() {
        return 8;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("IDEA engine not initialised");
        }
        if (n + 8 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 8 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.ideaFunc(this.workingKey, byArray, n, byArray2, n2);
        return 8;
    }

    public void reset() {
    }

    private int bytesToWord(byte[] byArray, int n) {
        return (byArray[n] << 8 & 0xFF00) + (byArray[n + 1] & 0xFF);
    }

    private void wordToBytes(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)(n >>> 8);
        byArray[n2 + 1] = (byte)n;
    }

    private int mul(int n, int n2) {
        int n3;
        n = n == 0 ? 65537 - n2 : (n2 == 0 ? 65537 - n : n2 - n + ((n2 = (n3 = n * n2) & 0xFFFF) < (n = n3 >>> 16) ? 1 : 0));
        return n & 0xFFFF;
    }

    private void ideaFunc(int[] nArray, byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = 0;
        int n4 = this.bytesToWord(byArray, n);
        int n5 = this.bytesToWord(byArray, n + 2);
        int n6 = this.bytesToWord(byArray, n + 4);
        int n7 = this.bytesToWord(byArray, n + 6);
        for (int i = 0; i < 8; ++i) {
            n4 = this.mul(n4, nArray[n3++]);
            n5 += nArray[n3++];
            n6 += nArray[n3++];
            n7 = this.mul(n7, nArray[n3++]);
            int n8 = n5 &= 0xFFFF;
            int n9 = n6 &= 0xFFFF;
            n6 ^= n4;
            n5 ^= n7;
            n6 = this.mul(n6, nArray[n3++]);
            n5 += n6;
            n5 &= 0xFFFF;
            n5 = this.mul(n5, nArray[n3++]);
            n6 += n5;
            n4 ^= n5;
            n7 ^= (n6 &= 0xFFFF);
            n5 ^= n9;
            n6 ^= n8;
        }
        this.wordToBytes(this.mul(n4, nArray[n3++]), byArray2, n2);
        this.wordToBytes(n6 + nArray[n3++], byArray2, n2 + 2);
        this.wordToBytes(n5 + nArray[n3++], byArray2, n2 + 4);
        this.wordToBytes(this.mul(n7, nArray[n3]), byArray2, n2 + 6);
    }

    private int[] expandKey(byte[] byArray) {
        int n;
        int[] nArray = new int[52];
        if (byArray.length < 16) {
            byte[] byArray2 = new byte[16];
            System.arraycopy(byArray, 0, byArray2, byArray2.length - byArray.length, byArray.length);
            byArray = byArray2;
        }
        for (n = 0; n < 8; ++n) {
            nArray[n] = this.bytesToWord(byArray, n * 2);
        }
        for (n = 8; n < 52; ++n) {
            nArray[n] = (n & 7) < 6 ? ((nArray[n - 7] & 0x7F) << 9 | nArray[n - 6] >> 7) & 0xFFFF : ((n & 7) == 6 ? ((nArray[n - 7] & 0x7F) << 9 | nArray[n - 14] >> 7) & 0xFFFF : ((nArray[n - 15] & 0x7F) << 9 | nArray[n - 14] >> 7) & 0xFFFF);
        }
        return nArray;
    }

    private int mulInv(int n) {
        if (n < 2) {
            return n;
        }
        int n2 = 1;
        int n3 = 65537 / n;
        for (int i = 65537 % n; i != 1; i %= n) {
            int n4 = n / i;
            n2 = n2 + n3 * n4 & 0xFFFF;
            if ((n %= i) == 1) {
                return n2;
            }
            n4 = i / n;
            n3 = n3 + n2 * n4 & 0xFFFF;
        }
        return 1 - n3 & 0xFFFF;
    }

    int addInv(int n) {
        return 0 - n & 0xFFFF;
    }

    private int[] invertKey(int[] nArray) {
        int n = 52;
        int[] nArray2 = new int[52];
        int n2 = 0;
        int n3 = this.mulInv(nArray[n2++]);
        int n4 = this.addInv(nArray[n2++]);
        int n5 = this.addInv(nArray[n2++]);
        int n6 = this.mulInv(nArray[n2++]);
        nArray2[--n] = n6;
        nArray2[--n] = n5;
        nArray2[--n] = n4;
        nArray2[--n] = n3;
        for (int i = 1; i < 8; ++i) {
            n3 = nArray[n2++];
            n4 = nArray[n2++];
            nArray2[--n] = n4;
            nArray2[--n] = n3;
            n3 = this.mulInv(nArray[n2++]);
            n4 = this.addInv(nArray[n2++]);
            n5 = this.addInv(nArray[n2++]);
            n6 = this.mulInv(nArray[n2++]);
            nArray2[--n] = n6;
            nArray2[--n] = n4;
            nArray2[--n] = n5;
            nArray2[--n] = n3;
        }
        n3 = nArray[n2++];
        n4 = nArray[n2++];
        nArray2[--n] = n4;
        nArray2[--n] = n3;
        n3 = this.mulInv(nArray[n2++]);
        n4 = this.addInv(nArray[n2++]);
        n5 = this.addInv(nArray[n2++]);
        n6 = this.mulInv(nArray[n2]);
        nArray2[--n] = n6;
        nArray2[--n] = n5;
        nArray2[--n] = n4;
        nArray2[--n] = n3;
        return nArray2;
    }

    private int[] generateWorkingKey(boolean bl, byte[] byArray) {
        if (bl) {
            return this.expandKey(byArray);
        }
        return this.invertKey(this.expandKey(byArray));
    }
}

