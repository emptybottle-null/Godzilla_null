/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.CAST5Engine;

public final class CAST6Engine
extends CAST5Engine {
    protected static final int ROUNDS = 12;
    protected static final int BLOCK_SIZE = 16;
    protected int[] _Kr = new int[48];
    protected int[] _Km = new int[48];
    protected int[] _Tr = new int[192];
    protected int[] _Tm = new int[192];
    private int[] _workingKey = new int[8];

    public String getAlgorithmName() {
        return "CAST6";
    }

    public void reset() {
    }

    public int getBlockSize() {
        return 16;
    }

    protected void setKey(byte[] byArray) {
        int n;
        int n2;
        int n3 = 1518500249;
        int n4 = 1859775393;
        int n5 = 19;
        int n6 = 17;
        for (int i = 0; i < 24; ++i) {
            for (n2 = 0; n2 < 8; ++n2) {
                this._Tm[i * 8 + n2] = n3;
                n3 += n4;
                this._Tr[i * 8 + n2] = n5;
                n5 = n5 + n6 & 0x1F;
            }
        }
        byte[] byArray2 = new byte[64];
        n2 = byArray.length;
        System.arraycopy(byArray, 0, byArray2, 0, n2);
        for (n = 0; n < 8; ++n) {
            this._workingKey[n] = this.BytesTo32bits(byArray2, n * 4);
        }
        for (n = 0; n < 12; ++n) {
            int n7 = n * 2 * 8;
            this._workingKey[6] = this._workingKey[6] ^ this.F1(this._workingKey[7], this._Tm[n7], this._Tr[n7]);
            this._workingKey[5] = this._workingKey[5] ^ this.F2(this._workingKey[6], this._Tm[n7 + 1], this._Tr[n7 + 1]);
            this._workingKey[4] = this._workingKey[4] ^ this.F3(this._workingKey[5], this._Tm[n7 + 2], this._Tr[n7 + 2]);
            this._workingKey[3] = this._workingKey[3] ^ this.F1(this._workingKey[4], this._Tm[n7 + 3], this._Tr[n7 + 3]);
            this._workingKey[2] = this._workingKey[2] ^ this.F2(this._workingKey[3], this._Tm[n7 + 4], this._Tr[n7 + 4]);
            this._workingKey[1] = this._workingKey[1] ^ this.F3(this._workingKey[2], this._Tm[n7 + 5], this._Tr[n7 + 5]);
            this._workingKey[0] = this._workingKey[0] ^ this.F1(this._workingKey[1], this._Tm[n7 + 6], this._Tr[n7 + 6]);
            this._workingKey[7] = this._workingKey[7] ^ this.F2(this._workingKey[0], this._Tm[n7 + 7], this._Tr[n7 + 7]);
            n7 = (n * 2 + 1) * 8;
            this._workingKey[6] = this._workingKey[6] ^ this.F1(this._workingKey[7], this._Tm[n7], this._Tr[n7]);
            this._workingKey[5] = this._workingKey[5] ^ this.F2(this._workingKey[6], this._Tm[n7 + 1], this._Tr[n7 + 1]);
            this._workingKey[4] = this._workingKey[4] ^ this.F3(this._workingKey[5], this._Tm[n7 + 2], this._Tr[n7 + 2]);
            this._workingKey[3] = this._workingKey[3] ^ this.F1(this._workingKey[4], this._Tm[n7 + 3], this._Tr[n7 + 3]);
            this._workingKey[2] = this._workingKey[2] ^ this.F2(this._workingKey[3], this._Tm[n7 + 4], this._Tr[n7 + 4]);
            this._workingKey[1] = this._workingKey[1] ^ this.F3(this._workingKey[2], this._Tm[n7 + 5], this._Tr[n7 + 5]);
            this._workingKey[0] = this._workingKey[0] ^ this.F1(this._workingKey[1], this._Tm[n7 + 6], this._Tr[n7 + 6]);
            this._workingKey[7] = this._workingKey[7] ^ this.F2(this._workingKey[0], this._Tm[n7 + 7], this._Tr[n7 + 7]);
            this._Kr[n * 4] = this._workingKey[0] & 0x1F;
            this._Kr[n * 4 + 1] = this._workingKey[2] & 0x1F;
            this._Kr[n * 4 + 2] = this._workingKey[4] & 0x1F;
            this._Kr[n * 4 + 3] = this._workingKey[6] & 0x1F;
            this._Km[n * 4] = this._workingKey[7];
            this._Km[n * 4 + 1] = this._workingKey[5];
            this._Km[n * 4 + 2] = this._workingKey[3];
            this._Km[n * 4 + 3] = this._workingKey[1];
        }
    }

    protected int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int[] nArray = new int[4];
        int n3 = this.BytesTo32bits(byArray, n);
        int n4 = this.BytesTo32bits(byArray, n + 4);
        int n5 = this.BytesTo32bits(byArray, n + 8);
        int n6 = this.BytesTo32bits(byArray, n + 12);
        this.CAST_Encipher(n3, n4, n5, n6, nArray);
        this.Bits32ToBytes(nArray[0], byArray2, n2);
        this.Bits32ToBytes(nArray[1], byArray2, n2 + 4);
        this.Bits32ToBytes(nArray[2], byArray2, n2 + 8);
        this.Bits32ToBytes(nArray[3], byArray2, n2 + 12);
        return 16;
    }

    protected int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int[] nArray = new int[4];
        int n3 = this.BytesTo32bits(byArray, n);
        int n4 = this.BytesTo32bits(byArray, n + 4);
        int n5 = this.BytesTo32bits(byArray, n + 8);
        int n6 = this.BytesTo32bits(byArray, n + 12);
        this.CAST_Decipher(n3, n4, n5, n6, nArray);
        this.Bits32ToBytes(nArray[0], byArray2, n2);
        this.Bits32ToBytes(nArray[1], byArray2, n2 + 4);
        this.Bits32ToBytes(nArray[2], byArray2, n2 + 8);
        this.Bits32ToBytes(nArray[3], byArray2, n2 + 12);
        return 16;
    }

    protected final void CAST_Encipher(int n, int n2, int n3, int n4, int[] nArray) {
        int n5;
        int n6;
        for (n6 = 0; n6 < 6; ++n6) {
            n5 = n6 * 4;
            n4 ^= this.F1(n ^= this.F3(n2 ^= this.F2(n3 ^= this.F1(n4, this._Km[n5], this._Kr[n5]), this._Km[n5 + 1], this._Kr[n5 + 1]), this._Km[n5 + 2], this._Kr[n5 + 2]), this._Km[n5 + 3], this._Kr[n5 + 3]);
        }
        for (n6 = 6; n6 < 12; ++n6) {
            n5 = n6 * 4;
            n2 ^= this.F2(n3, this._Km[n5 + 1], this._Kr[n5 + 1]);
            n3 ^= this.F1(n4 ^= this.F1(n ^= this.F3(n2, this._Km[n5 + 2], this._Kr[n5 + 2]), this._Km[n5 + 3], this._Kr[n5 + 3]), this._Km[n5], this._Kr[n5]);
        }
        nArray[0] = n;
        nArray[1] = n2;
        nArray[2] = n3;
        nArray[3] = n4;
    }

    protected final void CAST_Decipher(int n, int n2, int n3, int n4, int[] nArray) {
        int n5;
        int n6;
        for (n6 = 0; n6 < 6; ++n6) {
            n5 = (11 - n6) * 4;
            n4 ^= this.F1(n ^= this.F3(n2 ^= this.F2(n3 ^= this.F1(n4, this._Km[n5], this._Kr[n5]), this._Km[n5 + 1], this._Kr[n5 + 1]), this._Km[n5 + 2], this._Kr[n5 + 2]), this._Km[n5 + 3], this._Kr[n5 + 3]);
        }
        for (n6 = 6; n6 < 12; ++n6) {
            n5 = (11 - n6) * 4;
            n2 ^= this.F2(n3, this._Km[n5 + 1], this._Kr[n5 + 1]);
            n3 ^= this.F1(n4 ^= this.F1(n ^= this.F3(n2, this._Km[n5 + 2], this._Kr[n5 + 2]), this._Km[n5 + 3], this._Kr[n5 + 3]), this._Km[n5], this._Kr[n5]);
        }
        nArray[0] = n;
        nArray[1] = n2;
        nArray[2] = n3;
        nArray[3] = n4;
    }
}

