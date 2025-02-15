/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;

public class PSSSigner
implements Signer {
    public static final byte TRAILER_IMPLICIT = -68;
    private Digest contentDigest;
    private Digest mgfDigest;
    private AsymmetricBlockCipher cipher;
    private SecureRandom random;
    private int hLen;
    private int mgfhLen;
    private boolean sSet;
    private int sLen;
    private int emBits;
    private byte[] salt;
    private byte[] mDash;
    private byte[] block;
    private byte trailer;

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int n) {
        this(asymmetricBlockCipher, digest, n, -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int n) {
        this(asymmetricBlockCipher, digest, digest2, n, -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int n, byte by) {
        this(asymmetricBlockCipher, digest, digest, n, by);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int n, byte by) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest = digest;
        this.mgfDigest = digest2;
        this.hLen = digest.getDigestSize();
        this.mgfhLen = digest2.getDigestSize();
        this.sSet = false;
        this.sLen = n;
        this.salt = new byte[n];
        this.mDash = new byte[8 + n + this.hLen];
        this.trailer = by;
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] byArray) {
        this(asymmetricBlockCipher, digest, digest, byArray, -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] byArray) {
        this(asymmetricBlockCipher, digest, digest2, byArray, -68);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] byArray, byte by) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest = digest;
        this.mgfDigest = digest2;
        this.hLen = digest.getDigestSize();
        this.mgfhLen = digest2.getDigestSize();
        this.sSet = true;
        this.sLen = byArray.length;
        this.salt = byArray;
        this.mDash = new byte[8 + this.sLen + this.hLen];
        this.trailer = by;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        CipherParameters cipherParameters3;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters3 = (ParametersWithRandom)cipherParameters;
            cipherParameters2 = ((ParametersWithRandom)cipherParameters3).getParameters();
            this.random = ((ParametersWithRandom)cipherParameters3).getRandom();
        } else {
            cipherParameters2 = cipherParameters;
            if (bl) {
                this.random = new SecureRandom();
            }
        }
        if (cipherParameters2 instanceof RSABlindingParameters) {
            cipherParameters3 = ((RSABlindingParameters)cipherParameters2).getPublicKey();
            this.cipher.init(bl, cipherParameters);
        } else {
            cipherParameters3 = (RSAKeyParameters)cipherParameters2;
            this.cipher.init(bl, cipherParameters2);
        }
        this.emBits = ((RSAKeyParameters)cipherParameters3).getModulus().bitLength() - 1;
        if (this.emBits < 8 * this.hLen + 8 * this.sLen + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.block = new byte[(this.emBits + 7) / 8];
        this.reset();
    }

    private void clearBlock(byte[] byArray) {
        for (int i = 0; i != byArray.length; ++i) {
            byArray[i] = 0;
        }
    }

    public void update(byte by) {
        this.contentDigest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.contentDigest.update(byArray, n, n2);
    }

    public void reset() {
        this.contentDigest.reset();
    }

    public byte[] generateSignature() throws CryptoException, DataLengthException {
        this.contentDigest.doFinal(this.mDash, this.mDash.length - this.hLen - this.sLen);
        if (this.sLen != 0) {
            if (!this.sSet) {
                this.random.nextBytes(this.salt);
            }
            System.arraycopy(this.salt, 0, this.mDash, this.mDash.length - this.sLen, this.sLen);
        }
        byte[] byArray = new byte[this.hLen];
        this.contentDigest.update(this.mDash, 0, this.mDash.length);
        this.contentDigest.doFinal(byArray, 0);
        this.block[this.block.length - this.sLen - 1 - this.hLen - 1] = 1;
        System.arraycopy(this.salt, 0, this.block, this.block.length - this.sLen - this.hLen - 1, this.sLen);
        byte[] byArray2 = this.maskGeneratorFunction1(byArray, 0, byArray.length, this.block.length - this.hLen - 1);
        for (int i = 0; i != byArray2.length; ++i) {
            int n = i;
            this.block[n] = (byte)(this.block[n] ^ byArray2[i]);
        }
        this.block[0] = (byte)(this.block[0] & 255 >> this.block.length * 8 - this.emBits);
        System.arraycopy(byArray, 0, this.block, this.block.length - this.hLen - 1, this.hLen);
        this.block[this.block.length - 1] = this.trailer;
        byte[] byArray3 = this.cipher.processBlock(this.block, 0, this.block.length);
        this.clearBlock(this.block);
        return byArray3;
    }

    public boolean verifySignature(byte[] byArray) {
        int n;
        byte[] byArray2;
        this.contentDigest.doFinal(this.mDash, this.mDash.length - this.hLen - this.sLen);
        try {
            byArray2 = this.cipher.processBlock(byArray, 0, byArray.length);
            System.arraycopy(byArray2, 0, this.block, this.block.length - byArray2.length, byArray2.length);
        } catch (Exception exception) {
            return false;
        }
        if (this.block[this.block.length - 1] != this.trailer) {
            this.clearBlock(this.block);
            return false;
        }
        byArray2 = this.maskGeneratorFunction1(this.block, this.block.length - this.hLen - 1, this.hLen, this.block.length - this.hLen - 1);
        for (n = 0; n != byArray2.length; ++n) {
            int n2 = n;
            this.block[n2] = (byte)(this.block[n2] ^ byArray2[n]);
        }
        this.block[0] = (byte)(this.block[0] & 255 >> this.block.length * 8 - this.emBits);
        for (n = 0; n != this.block.length - this.hLen - this.sLen - 2; ++n) {
            if (this.block[n] == 0) continue;
            this.clearBlock(this.block);
            return false;
        }
        if (this.block[this.block.length - this.hLen - this.sLen - 2] != 1) {
            this.clearBlock(this.block);
            return false;
        }
        if (this.sSet) {
            System.arraycopy(this.salt, 0, this.mDash, this.mDash.length - this.sLen, this.sLen);
        } else {
            System.arraycopy(this.block, this.block.length - this.sLen - this.hLen - 1, this.mDash, this.mDash.length - this.sLen, this.sLen);
        }
        this.contentDigest.update(this.mDash, 0, this.mDash.length);
        this.contentDigest.doFinal(this.mDash, this.mDash.length - this.hLen);
        n = this.block.length - this.hLen - 1;
        for (int i = this.mDash.length - this.hLen; i != this.mDash.length; ++i) {
            if ((this.block[n] ^ this.mDash[i]) != 0) {
                this.clearBlock(this.mDash);
                this.clearBlock(this.block);
                return false;
            }
            ++n;
        }
        this.clearBlock(this.mDash);
        this.clearBlock(this.block);
        return true;
    }

    private void ItoOSP(int n, byte[] byArray) {
        byArray[0] = (byte)(n >>> 24);
        byArray[1] = (byte)(n >>> 16);
        byArray[2] = (byte)(n >>> 8);
        byArray[3] = (byte)(n >>> 0);
    }

    private byte[] maskGeneratorFunction1(byte[] byArray, int n, int n2, int n3) {
        int n4;
        byte[] byArray2 = new byte[n3];
        byte[] byArray3 = new byte[this.mgfhLen];
        byte[] byArray4 = new byte[4];
        this.mgfDigest.reset();
        for (n4 = 0; n4 < n3 / this.mgfhLen; ++n4) {
            this.ItoOSP(n4, byArray4);
            this.mgfDigest.update(byArray, n, n2);
            this.mgfDigest.update(byArray4, 0, byArray4.length);
            this.mgfDigest.doFinal(byArray3, 0);
            System.arraycopy(byArray3, 0, byArray2, n4 * this.mgfhLen, this.mgfhLen);
        }
        if (n4 * this.mgfhLen < n3) {
            this.ItoOSP(n4, byArray4);
            this.mgfDigest.update(byArray, n, n2);
            this.mgfDigest.update(byArray4, 0, byArray4.length);
            this.mgfDigest.doFinal(byArray3, 0);
            System.arraycopy(byArray3, 0, byArray2, n4 * this.mgfhLen, byArray2.length - n4 * this.mgfhLen);
        }
        return byArray2;
    }
}

