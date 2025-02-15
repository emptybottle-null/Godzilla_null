/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

public class KXTSBlockCipher
extends BufferedBlockCipher {
    private static final long RED_POLY_128 = 135L;
    private static final long RED_POLY_256 = 1061L;
    private static final long RED_POLY_512 = 293L;
    private final int blockSize;
    private final long reductionPolynomial;
    private final long[] tw_init;
    private final long[] tw_current;
    private int counter;

    protected static long getReductionPolynomial(int n) {
        switch (n) {
            case 16: {
                return 135L;
            }
            case 32: {
                return 1061L;
            }
            case 64: {
                return 293L;
            }
        }
        throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
    }

    public KXTSBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        this.reductionPolynomial = KXTSBlockCipher.getReductionPolynomial(this.blockSize);
        this.tw_init = new long[this.blockSize >>> 3];
        this.tw_current = new long[this.blockSize >>> 3];
        this.counter = -1;
    }

    public int getOutputSize(int n) {
        return n;
    }

    public int getUpdateOutputSize(int n) {
        return n;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameters passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        cipherParameters = parametersWithIV.getParameters();
        byte[] byArray = parametersWithIV.getIV();
        if (byArray.length != this.blockSize) {
            throw new IllegalArgumentException("Currently only support IVs of exactly one block");
        }
        byte[] byArray2 = new byte[this.blockSize];
        System.arraycopy(byArray, 0, byArray2, 0, this.blockSize);
        this.cipher.init(true, cipherParameters);
        this.cipher.processBlock(byArray2, 0, byArray2, 0);
        this.cipher.init(bl, cipherParameters);
        Pack.littleEndianToLong(byArray2, 0, this.tw_init);
        System.arraycopy(this.tw_init, 0, this.tw_current, 0, this.tw_init.length);
        this.counter = 0;
    }

    public int processByte(byte by, byte[] byArray, int n) {
        throw new IllegalStateException("unsupported operation");
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        if (byArray.length - n < n2) {
            throw new DataLengthException("Input buffer too short");
        }
        if (byArray2.length - n < n2) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (n2 % this.blockSize != 0) {
            throw new IllegalArgumentException("Partial blocks not supported");
        }
        for (int i = 0; i < n2; i += this.blockSize) {
            this.processBlock(byArray, n + i, byArray2, n3 + i);
        }
        return n2;
    }

    private void processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        if (this.counter == -1) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        ++this.counter;
        KXTSBlockCipher.GF_double(this.reductionPolynomial, this.tw_current);
        byte[] byArray3 = new byte[this.blockSize];
        Pack.longToLittleEndian(this.tw_current, byArray3, 0);
        byte[] byArray4 = new byte[this.blockSize];
        System.arraycopy(byArray3, 0, byArray4, 0, this.blockSize);
        for (n3 = 0; n3 < this.blockSize; ++n3) {
            int n4 = n3;
            byArray4[n4] = (byte)(byArray4[n4] ^ byArray[n + n3]);
        }
        this.cipher.processBlock(byArray4, 0, byArray4, 0);
        for (n3 = 0; n3 < this.blockSize; ++n3) {
            byArray2[n2 + n3] = (byte)(byArray4[n3] ^ byArray3[n3]);
        }
    }

    public int doFinal(byte[] byArray, int n) {
        this.reset();
        return 0;
    }

    public void reset() {
        this.cipher.reset();
        System.arraycopy(this.tw_init, 0, this.tw_current, 0, this.tw_init.length);
        this.counter = 0;
    }

    private static void GF_double(long l, long[] lArray) {
        long l2 = 0L;
        for (int i = 0; i < lArray.length; ++i) {
            long l3 = lArray[i];
            long l4 = l3 >>> 63;
            lArray[i] = l3 << 1 ^ l2;
            l2 = l4;
        }
        lArray[0] = lArray[0] ^ l & -l2;
    }
}

