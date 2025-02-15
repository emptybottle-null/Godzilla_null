/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class ISAACEngine
implements StreamCipher {
    private final int sizeL = 8;
    private final int stateArraySize = 256;
    private int[] engineState = null;
    private int[] results = null;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int index = 0;
    private byte[] keyStream = new byte[1024];
    private byte[] workingKey = null;
    private boolean initialised = false;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + cipherParameters.getClass().getName());
        }
        KeyParameter keyParameter = (KeyParameter)cipherParameters;
        this.setKey(keyParameter.getKey());
    }

    public byte returnByte(byte by) {
        if (this.index == 0) {
            this.isaac();
            this.keyStream = Pack.intToBigEndian(this.results);
        }
        byte by2 = (byte)(this.keyStream[this.index] ^ by);
        this.index = this.index + 1 & 0x3FF;
        return by2;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        if (!this.initialised) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n3 + n2 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i = 0; i < n2; ++i) {
            if (this.index == 0) {
                this.isaac();
                this.keyStream = Pack.intToBigEndian(this.results);
            }
            byArray2[i + n3] = (byte)(this.keyStream[this.index] ^ byArray[i + n]);
            this.index = this.index + 1 & 0x3FF;
        }
        return n2;
    }

    public String getAlgorithmName() {
        return "ISAAC";
    }

    public void reset() {
        this.setKey(this.workingKey);
    }

    private void setKey(byte[] byArray) {
        int n;
        this.workingKey = byArray;
        if (this.engineState == null) {
            this.engineState = new int[256];
        }
        if (this.results == null) {
            this.results = new int[256];
        }
        for (n = 0; n < 256; ++n) {
            this.results[n] = 0;
            this.engineState[n] = 0;
        }
        this.c = 0;
        this.b = 0;
        this.a = 0;
        this.index = 0;
        byte[] byArray2 = new byte[byArray.length + (byArray.length & 3)];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        for (n = 0; n < byArray2.length; n += 4) {
            this.results[n >>> 2] = Pack.littleEndianToInt(byArray2, n);
        }
        int[] nArray = new int[8];
        for (n = 0; n < 8; ++n) {
            nArray[n] = -1640531527;
        }
        for (n = 0; n < 4; ++n) {
            this.mix(nArray);
        }
        for (n = 0; n < 2; ++n) {
            for (int i = 0; i < 256; i += 8) {
                int n2;
                for (n2 = 0; n2 < 8; ++n2) {
                    int n3 = n2;
                    nArray[n3] = nArray[n3] + (n < 1 ? this.results[i + n2] : this.engineState[i + n2]);
                }
                this.mix(nArray);
                for (n2 = 0; n2 < 8; ++n2) {
                    this.engineState[i + n2] = nArray[n2];
                }
            }
        }
        this.isaac();
        this.initialised = true;
    }

    private void isaac() {
        this.b += ++this.c;
        for (int i = 0; i < 256; ++i) {
            int n;
            int n2 = this.engineState[i];
            switch (i & 3) {
                case 0: {
                    this.a ^= this.a << 13;
                    break;
                }
                case 1: {
                    this.a ^= this.a >>> 6;
                    break;
                }
                case 2: {
                    this.a ^= this.a << 2;
                    break;
                }
                case 3: {
                    this.a ^= this.a >>> 16;
                }
            }
            this.a += this.engineState[i + 128 & 0xFF];
            this.engineState[i] = n = this.engineState[n2 >>> 2 & 0xFF] + this.a + this.b;
            this.results[i] = this.b = this.engineState[n >>> 10 & 0xFF] + n2;
        }
    }

    private void mix(int[] nArray) {
        nArray[0] = nArray[0] ^ nArray[1] << 11;
        nArray[3] = nArray[3] + nArray[0];
        nArray[1] = nArray[1] + nArray[2];
        nArray[1] = nArray[1] ^ nArray[2] >>> 2;
        nArray[4] = nArray[4] + nArray[1];
        nArray[2] = nArray[2] + nArray[3];
        nArray[2] = nArray[2] ^ nArray[3] << 8;
        nArray[5] = nArray[5] + nArray[2];
        nArray[3] = nArray[3] + nArray[4];
        nArray[3] = nArray[3] ^ nArray[4] >>> 16;
        nArray[6] = nArray[6] + nArray[3];
        nArray[4] = nArray[4] + nArray[5];
        nArray[4] = nArray[4] ^ nArray[5] << 10;
        nArray[7] = nArray[7] + nArray[4];
        nArray[5] = nArray[5] + nArray[6];
        nArray[5] = nArray[5] ^ nArray[6] >>> 4;
        nArray[0] = nArray[0] + nArray[5];
        nArray[6] = nArray[6] + nArray[7];
        nArray[6] = nArray[6] ^ nArray[7] << 8;
        nArray[1] = nArray[1] + nArray[6];
        nArray[7] = nArray[7] + nArray[0];
        nArray[7] = nArray[7] ^ nArray[0] >>> 9;
        nArray[2] = nArray[2] + nArray[7];
        nArray[0] = nArray[0] + nArray[1];
    }
}

