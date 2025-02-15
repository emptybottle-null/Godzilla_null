/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;

public class DSTU7624Mac
implements Mac {
    private static final int BITS_IN_BYTE = 8;
    private byte[] buf;
    private int bufOff;
    private int macSize;
    private int blockSize;
    private DSTU7624Engine engine;
    private byte[] c;
    private byte[] cTemp;
    private byte[] kDelta;

    public DSTU7624Mac(int n, int n2) {
        this.engine = new DSTU7624Engine(n);
        this.blockSize = n / 8;
        this.macSize = n2 / 8;
        this.c = new byte[this.blockSize];
        this.kDelta = new byte[this.blockSize];
        this.cTemp = new byte[this.blockSize];
        this.buf = new byte[this.blockSize];
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Mac");
        }
        this.engine.init(true, cipherParameters);
        this.engine.processBlock(this.kDelta, 0, this.kDelta, 0);
    }

    public String getAlgorithmName() {
        return "DSTU7624Mac";
    }

    public int getMacSize() {
        return this.macSize;
    }

    public void update(byte by) {
        if (this.bufOff == this.buf.length) {
            this.processBlock(this.buf, 0);
            this.bufOff = 0;
        }
        this.buf[this.bufOff++] = by;
    }

    public void update(byte[] byArray, int n, int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("can't have a negative input length!");
        }
        int n3 = this.engine.getBlockSize();
        int n4 = n3 - this.bufOff;
        if (n2 > n4) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n4);
            this.processBlock(this.buf, 0);
            this.bufOff = 0;
            n2 -= n4;
            n += n4;
            while (n2 > n3) {
                this.processBlock(byArray, n);
                n2 -= n3;
                n += n3;
            }
        }
        System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
        this.bufOff += n2;
    }

    private void processBlock(byte[] byArray, int n) {
        this.xor(this.c, 0, byArray, n, this.cTemp);
        this.engine.processBlock(this.cTemp, 0, this.c, 0);
    }

    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        if (this.bufOff % this.buf.length != 0) {
            throw new DataLengthException("input must be a multiple of blocksize");
        }
        this.xor(this.c, 0, this.buf, 0, this.cTemp);
        this.xor(this.cTemp, 0, this.kDelta, 0, this.c);
        this.engine.processBlock(this.c, 0, this.c, 0);
        if (this.macSize + n > byArray.length) {
            throw new OutputLengthException("output buffer too short");
        }
        System.arraycopy(this.c, 0, byArray, n, this.macSize);
        return this.macSize;
    }

    public void reset() {
        Arrays.fill(this.c, (byte)0);
        Arrays.fill(this.cTemp, (byte)0);
        Arrays.fill(this.kDelta, (byte)0);
        Arrays.fill(this.buf, (byte)0);
        this.engine.reset();
        this.engine.processBlock(this.kDelta, 0, this.kDelta, 0);
        this.bufOff = 0;
    }

    private void xor(byte[] byArray, int n, byte[] byArray2, int n2, byte[] byArray3) {
        if (byArray.length - n < this.blockSize || byArray2.length - n2 < this.blockSize || byArray3.length < this.blockSize) {
            throw new IllegalArgumentException("some of input buffers too short");
        }
        for (int i = 0; i < this.blockSize; ++i) {
            byArray3[i] = (byte)(byArray[i + n] ^ byArray2[i + n2]);
        }
    }
}

