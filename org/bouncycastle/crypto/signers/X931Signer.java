/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.ISOTrailers;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public class X931Signer
implements Signer {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_WHIRLPOOL = 14284;
    public static final int TRAILER_SHA224 = 14540;
    private Digest digest;
    private AsymmetricBlockCipher cipher;
    private RSAKeyParameters kParam;
    private int trailer;
    private int keyBits;
    private byte[] block;

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean bl) {
        this.cipher = asymmetricBlockCipher;
        this.digest = digest;
        if (bl) {
            this.trailer = 188;
        } else {
            Integer n = ISOTrailers.getTrailer(digest);
            if (n != null) {
                this.trailer = n;
            } else {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
        }
    }

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.kParam = (RSAKeyParameters)cipherParameters;
        this.cipher.init(bl, this.kParam);
        this.keyBits = this.kParam.getModulus().bitLength();
        this.block = new byte[(this.keyBits + 7) / 8];
        this.reset();
    }

    private void clearBlock(byte[] byArray) {
        for (int i = 0; i != byArray.length; ++i) {
            byArray[i] = 0;
        }
    }

    public void update(byte by) {
        this.digest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public void reset() {
        this.digest.reset();
    }

    public byte[] generateSignature() throws CryptoException {
        this.createSignatureBlock();
        BigInteger bigInteger = new BigInteger(1, this.cipher.processBlock(this.block, 0, this.block.length));
        this.clearBlock(this.block);
        bigInteger = bigInteger.min(this.kParam.getModulus().subtract(bigInteger));
        return BigIntegers.asUnsignedByteArray((this.kParam.getModulus().bitLength() + 7) / 8, bigInteger);
    }

    private void createSignatureBlock() {
        int n;
        int n2 = this.digest.getDigestSize();
        if (this.trailer == 188) {
            n = this.block.length - n2 - 1;
            this.digest.doFinal(this.block, n);
            this.block[this.block.length - 1] = -68;
        } else {
            n = this.block.length - n2 - 2;
            this.digest.doFinal(this.block, n);
            this.block[this.block.length - 2] = (byte)(this.trailer >>> 8);
            this.block[this.block.length - 1] = (byte)this.trailer;
        }
        this.block[0] = 107;
        for (int i = n - 2; i != 0; --i) {
            this.block[i] = -69;
        }
        this.block[n - 1] = -70;
    }

    public boolean verifySignature(byte[] byArray) {
        BigInteger bigInteger;
        try {
            this.block = this.cipher.processBlock(byArray, 0, byArray.length);
        } catch (Exception exception) {
            return false;
        }
        BigInteger bigInteger2 = new BigInteger(1, this.block);
        if ((bigInteger2.intValue() & 0xF) == 12) {
            bigInteger = bigInteger2;
        } else {
            bigInteger2 = this.kParam.getModulus().subtract(bigInteger2);
            if ((bigInteger2.intValue() & 0xF) == 12) {
                bigInteger = bigInteger2;
            } else {
                return false;
            }
        }
        this.createSignatureBlock();
        byte[] byArray2 = BigIntegers.asUnsignedByteArray(this.block.length, bigInteger);
        boolean bl = Arrays.constantTimeAreEqual(this.block, byArray2);
        this.clearBlock(this.block);
        this.clearBlock(byArray2);
        return bl;
    }
}

