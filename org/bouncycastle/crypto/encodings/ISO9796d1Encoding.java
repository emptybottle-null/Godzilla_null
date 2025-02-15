/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.encodings;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;

public class ISO9796d1Encoding
implements AsymmetricBlockCipher {
    private static final BigInteger SIXTEEN = BigInteger.valueOf(16L);
    private static final BigInteger SIX = BigInteger.valueOf(6L);
    private static byte[] shadows = new byte[]{14, 3, 5, 8, 9, 4, 2, 15, 0, 13, 11, 6, 7, 10, 12, 1};
    private static byte[] inverse = new byte[]{8, 15, 6, 1, 5, 2, 11, 12, 3, 4, 13, 10, 14, 9, 0, 7};
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private int bitSize;
    private int padBits = 0;
    private BigInteger modulus;

    public ISO9796d1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.engine = asymmetricBlockCipher;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = null;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            rSAKeyParameters = (RSAKeyParameters)parametersWithRandom.getParameters();
        } else {
            rSAKeyParameters = (RSAKeyParameters)cipherParameters;
        }
        this.engine.init(bl, cipherParameters);
        this.modulus = rSAKeyParameters.getModulus();
        this.bitSize = this.modulus.bitLength();
        this.forEncryption = bl;
    }

    public int getInputBlockSize() {
        int n = this.engine.getInputBlockSize();
        if (this.forEncryption) {
            return (n + 1) / 2;
        }
        return n;
    }

    public int getOutputBlockSize() {
        int n = this.engine.getOutputBlockSize();
        if (this.forEncryption) {
            return n;
        }
        return (n + 1) / 2;
    }

    public void setPadBits(int n) {
        if (n > 7) {
            throw new IllegalArgumentException("padBits > 7");
        }
        this.padBits = n;
    }

    public int getPadBits() {
        return this.padBits;
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return this.encodeBlock(byArray, n, n2);
        }
        return this.decodeBlock(byArray, n, n2);
    }

    private byte[] encodeBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        byte by;
        int n3;
        byte[] byArray2 = new byte[(this.bitSize + 7) / 8];
        int n4 = this.padBits + 1;
        int n5 = n2;
        int n6 = (this.bitSize + 13) / 16;
        for (n3 = 0; n3 < n6; n3 += n5) {
            if (n3 > n6 - n5) {
                System.arraycopy(byArray, n + n2 - (n6 - n3), byArray2, byArray2.length - n6, n6 - n3);
                continue;
            }
            System.arraycopy(byArray, n, byArray2, byArray2.length - (n3 + n5), n5);
        }
        for (n3 = byArray2.length - 2 * n6; n3 != byArray2.length; n3 += 2) {
            by = byArray2[byArray2.length - n6 + n3 / 2];
            byArray2[n3] = (byte)(shadows[(by & 0xFF) >>> 4] << 4 | shadows[by & 0xF]);
            byArray2[n3 + 1] = by;
        }
        int n7 = byArray2.length - 2 * n5;
        byArray2[n7] = (byte)(byArray2[n7] ^ n4);
        byArray2[byArray2.length - 1] = (byte)(byArray2[byArray2.length - 1] << 4 | 6);
        n3 = 8 - (this.bitSize - 1) % 8;
        by = 0;
        if (n3 != 8) {
            byArray2[0] = (byte)(byArray2[0] & 255 >>> n3);
            byArray2[0] = (byte)(byArray2[0] | 128 >>> n3);
        } else {
            byArray2[0] = 0;
            byArray2[1] = (byte)(byArray2[1] | 0x80);
            by = 1;
        }
        return this.engine.processBlock(byArray2, by, byArray2.length - by);
    }

    private byte[] decodeBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        int n3;
        BigInteger bigInteger;
        byte[] byArray2 = this.engine.processBlock(byArray, n, n2);
        int n4 = 1;
        int n5 = (this.bitSize + 13) / 16;
        BigInteger bigInteger2 = new BigInteger(1, byArray2);
        if (bigInteger2.mod(SIXTEEN).equals(SIX)) {
            bigInteger = bigInteger2;
        } else if (this.modulus.subtract(bigInteger2).mod(SIXTEEN).equals(SIX)) {
            bigInteger = this.modulus.subtract(bigInteger2);
        } else {
            throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
        }
        byArray2 = ISO9796d1Encoding.convertOutputDecryptOnly(bigInteger);
        if ((byArray2[byArray2.length - 1] & 0xF) != 6) {
            throw new InvalidCipherTextException("invalid forcing byte in block");
        }
        byArray2[byArray2.length - 1] = (byte)((byArray2[byArray2.length - 1] & 0xFF) >>> 4 | inverse[(byArray2[byArray2.length - 2] & 0xFF) >> 4] << 4);
        byArray2[0] = (byte)(shadows[(byArray2[1] & 0xFF) >>> 4] << 4 | shadows[byArray2[1] & 0xF]);
        boolean bl = false;
        int n6 = 0;
        for (int i = byArray2.length - 1; i >= byArray2.length - 2 * n5; i -= 2) {
            n3 = shadows[(byArray2[i] & 0xFF) >>> 4] << 4 | shadows[byArray2[i] & 0xF];
            if (((byArray2[i - 1] ^ n3) & 0xFF) == 0) continue;
            if (!bl) {
                bl = true;
                n4 = (byArray2[i - 1] ^ n3) & 0xFF;
                n6 = i - 1;
                continue;
            }
            throw new InvalidCipherTextException("invalid tsums in block");
        }
        byArray2[n6] = 0;
        byte[] byArray3 = new byte[(byArray2.length - n6) / 2];
        for (n3 = 0; n3 < byArray3.length; ++n3) {
            byArray3[n3] = byArray2[2 * n3 + n6 + 1];
        }
        this.padBits = n4 - 1;
        return byArray3;
    }

    private static byte[] convertOutputDecryptOnly(BigInteger bigInteger) {
        byte[] byArray = bigInteger.toByteArray();
        if (byArray[0] == 0) {
            byte[] byArray2 = new byte[byArray.length - 1];
            System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
            return byArray2;
        }
        return byArray;
    }
}

