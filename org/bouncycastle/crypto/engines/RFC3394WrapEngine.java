/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class RFC3394WrapEngine
implements Wrapper {
    private BlockCipher engine;
    private boolean wrapCipherMode;
    private KeyParameter param;
    private boolean forWrapping;
    private byte[] iv = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};

    public RFC3394WrapEngine(BlockCipher blockCipher) {
        this(blockCipher, false);
    }

    public RFC3394WrapEngine(BlockCipher blockCipher, boolean bl) {
        this.engine = blockCipher;
        this.wrapCipherMode = !bl;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forWrapping = bl;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom)cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.param = (KeyParameter)cipherParameters;
        } else if (cipherParameters instanceof ParametersWithIV) {
            this.iv = ((ParametersWithIV)cipherParameters).getIV();
            this.param = (KeyParameter)((ParametersWithIV)cipherParameters).getParameters();
            if (this.iv.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    public byte[] wrap(byte[] byArray, int n, int n2) {
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        }
        int n3 = n2 / 8;
        if (n3 * 8 != n2) {
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        byte[] byArray2 = new byte[n2 + this.iv.length];
        byte[] byArray3 = new byte[8 + this.iv.length];
        System.arraycopy(this.iv, 0, byArray2, 0, this.iv.length);
        System.arraycopy(byArray, n, byArray2, this.iv.length, n2);
        this.engine.init(this.wrapCipherMode, this.param);
        for (int i = 0; i != 6; ++i) {
            for (int j = 1; j <= n3; ++j) {
                System.arraycopy(byArray2, 0, byArray3, 0, this.iv.length);
                System.arraycopy(byArray2, 8 * j, byArray3, this.iv.length, 8);
                this.engine.processBlock(byArray3, 0, byArray3, 0);
                int n4 = n3 * i + j;
                int n5 = 1;
                while (n4 != 0) {
                    byte by = (byte)n4;
                    int n6 = this.iv.length - n5;
                    byArray3[n6] = (byte)(byArray3[n6] ^ by);
                    n4 >>>= 8;
                    ++n5;
                }
                System.arraycopy(byArray3, 0, byArray2, 0, 8);
                System.arraycopy(byArray3, 8, byArray2, 8 * j, 8);
            }
        }
        return byArray2;
    }

    public byte[] unwrap(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int n3 = n2 / 8;
        if (n3 * 8 != n2) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        byte[] byArray2 = new byte[n2 - this.iv.length];
        byte[] byArray3 = new byte[this.iv.length];
        byte[] byArray4 = new byte[8 + this.iv.length];
        System.arraycopy(byArray, n, byArray3, 0, this.iv.length);
        System.arraycopy(byArray, n + this.iv.length, byArray2, 0, n2 - this.iv.length);
        this.engine.init(!this.wrapCipherMode, this.param);
        --n3;
        for (int i = 5; i >= 0; --i) {
            for (int j = n3; j >= 1; --j) {
                System.arraycopy(byArray3, 0, byArray4, 0, this.iv.length);
                System.arraycopy(byArray2, 8 * (j - 1), byArray4, this.iv.length, 8);
                int n4 = n3 * i + j;
                int n5 = 1;
                while (n4 != 0) {
                    byte by = (byte)n4;
                    int n6 = this.iv.length - n5;
                    byArray4[n6] = (byte)(byArray4[n6] ^ by);
                    n4 >>>= 8;
                    ++n5;
                }
                this.engine.processBlock(byArray4, 0, byArray4, 0);
                System.arraycopy(byArray4, 0, byArray3, 0, 8);
                System.arraycopy(byArray4, 8, byArray2, 8 * (j - 1), 8);
            }
        }
        if (!Arrays.constantTimeAreEqual(byArray3, this.iv)) {
            throw new InvalidCipherTextException("checksum failed");
        }
        return byArray2;
    }
}

