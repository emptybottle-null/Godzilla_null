/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.engines.RFC3394WrapEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class RFC5649WrapEngine
implements Wrapper {
    private BlockCipher engine;
    private KeyParameter param;
    private boolean forWrapping;
    private byte[] highOrderIV = new byte[]{-90, 89, 89, -90};
    private byte[] preIV = this.highOrderIV;
    private byte[] extractedAIV = null;

    public RFC5649WrapEngine(BlockCipher blockCipher) {
        this.engine = blockCipher;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forWrapping = bl;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom)cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.param = (KeyParameter)cipherParameters;
            this.preIV = this.highOrderIV;
        } else if (cipherParameters instanceof ParametersWithIV) {
            this.preIV = ((ParametersWithIV)cipherParameters).getIV();
            this.param = (KeyParameter)((ParametersWithIV)cipherParameters).getParameters();
            if (this.preIV.length != 4) {
                throw new IllegalArgumentException("IV length not equal to 4");
            }
        }
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    private byte[] padPlaintext(byte[] byArray) {
        int n = byArray.length;
        int n2 = (8 - n % 8) % 8;
        byte[] byArray2 = new byte[n + n2];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        if (n2 != 0) {
            byte[] byArray3 = new byte[n2];
            System.arraycopy(byArray3, 0, byArray2, n, n2);
        }
        return byArray2;
    }

    public byte[] wrap(byte[] byArray, int n, int n2) {
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        }
        byte[] byArray2 = new byte[8];
        byte[] byArray3 = Pack.intToBigEndian(n2);
        System.arraycopy(this.preIV, 0, byArray2, 0, this.preIV.length);
        System.arraycopy(byArray3, 0, byArray2, this.preIV.length, byArray3.length);
        byte[] byArray4 = new byte[n2];
        System.arraycopy(byArray, n, byArray4, 0, n2);
        byte[] byArray5 = this.padPlaintext(byArray4);
        if (byArray5.length == 8) {
            byte[] byArray6 = new byte[byArray5.length + byArray2.length];
            System.arraycopy(byArray2, 0, byArray6, 0, byArray2.length);
            System.arraycopy(byArray5, 0, byArray6, byArray2.length, byArray5.length);
            this.engine.init(true, this.param);
            for (int i = 0; i < byArray6.length; i += this.engine.getBlockSize()) {
                this.engine.processBlock(byArray6, i, byArray6, i);
            }
            return byArray6;
        }
        RFC3394WrapEngine rFC3394WrapEngine = new RFC3394WrapEngine(this.engine);
        ParametersWithIV parametersWithIV = new ParametersWithIV(this.param, byArray2);
        rFC3394WrapEngine.init(true, parametersWithIV);
        return rFC3394WrapEngine.wrap(byArray5, 0, byArray5.length);
    }

    public byte[] unwrap(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        int n3;
        int n4;
        int n5;
        byte[] byArray2;
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int n6 = n2 / 8;
        if (n6 * 8 != n2) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        if (n6 == 1) {
            throw new InvalidCipherTextException("unwrap data must be at least 16 bytes");
        }
        byte[] byArray3 = new byte[n2];
        System.arraycopy(byArray, n, byArray3, 0, n2);
        byte[] byArray4 = new byte[n2];
        if (n6 == 2) {
            this.engine.init(false, this.param);
            for (int i = 0; i < byArray3.length; i += this.engine.getBlockSize()) {
                this.engine.processBlock(byArray3, i, byArray4, i);
            }
            this.extractedAIV = new byte[8];
            System.arraycopy(byArray4, 0, this.extractedAIV, 0, this.extractedAIV.length);
            byArray2 = new byte[byArray4.length - this.extractedAIV.length];
            System.arraycopy(byArray4, this.extractedAIV.length, byArray2, 0, byArray2.length);
        } else {
            byArray2 = byArray4 = this.rfc3394UnwrapNoIvCheck(byArray, n, n2);
        }
        byte[] byArray5 = new byte[4];
        byte[] byArray6 = new byte[4];
        System.arraycopy(this.extractedAIV, 0, byArray5, 0, byArray5.length);
        System.arraycopy(this.extractedAIV, byArray5.length, byArray6, 0, byArray6.length);
        int n7 = Pack.bigEndianToInt(byArray6, 0);
        boolean bl = true;
        if (!Arrays.constantTimeAreEqual(byArray5, this.preIV)) {
            bl = false;
        }
        if (n7 <= (n5 = (n4 = byArray2.length) - 8)) {
            bl = false;
        }
        if (n7 > n4) {
            bl = false;
        }
        if ((n3 = n4 - n7) >= byArray2.length) {
            bl = false;
            n3 = byArray2.length;
        }
        byte[] byArray7 = new byte[n3];
        byte[] byArray8 = new byte[n3];
        System.arraycopy(byArray2, byArray2.length - n3, byArray8, 0, n3);
        if (!Arrays.constantTimeAreEqual(byArray8, byArray7)) {
            bl = false;
        }
        if (!bl) {
            throw new InvalidCipherTextException("checksum failed");
        }
        byte[] byArray9 = new byte[n7];
        System.arraycopy(byArray2, 0, byArray9, 0, byArray9.length);
        return byArray9;
    }

    private byte[] rfc3394UnwrapNoIvCheck(byte[] byArray, int n, int n2) {
        byte[] byArray2 = new byte[8];
        byte[] byArray3 = new byte[n2 - byArray2.length];
        byte[] byArray4 = new byte[byArray2.length];
        byte[] byArray5 = new byte[8 + byArray2.length];
        System.arraycopy(byArray, n, byArray4, 0, byArray2.length);
        System.arraycopy(byArray, n + byArray2.length, byArray3, 0, n2 - byArray2.length);
        this.engine.init(false, this.param);
        int n3 = n2 / 8;
        --n3;
        for (int i = 5; i >= 0; --i) {
            for (int j = n3; j >= 1; --j) {
                System.arraycopy(byArray4, 0, byArray5, 0, byArray2.length);
                System.arraycopy(byArray3, 8 * (j - 1), byArray5, byArray2.length, 8);
                int n4 = n3 * i + j;
                int n5 = 1;
                while (n4 != 0) {
                    byte by = (byte)n4;
                    int n6 = byArray2.length - n5;
                    byArray5[n6] = (byte)(byArray5[n6] ^ by);
                    n4 >>>= 8;
                    ++n5;
                }
                this.engine.processBlock(byArray5, 0, byArray5, 0);
                System.arraycopy(byArray5, 0, byArray4, 0, 8);
                System.arraycopy(byArray5, 8, byArray3, 8 * (j - 1), 8);
            }
        }
        this.extractedAIV = byArray4;
        return byArray3;
    }
}

