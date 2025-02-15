/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSalt;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.ISOTrailers;
import org.bouncycastle.util.Arrays;

public class ISO9796d2PSSSigner
implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private Digest digest;
    private AsymmetricBlockCipher cipher;
    private SecureRandom random;
    private byte[] standardSalt;
    private int hLen;
    private int trailer;
    private int keyBits;
    private byte[] block;
    private byte[] mBuf;
    private int messageLength;
    private int saltLength;
    private boolean fullMessage;
    private byte[] recoveredMessage;
    private byte[] preSig;
    private byte[] preBlock;
    private int preMStart;
    private int preTLength;

    public ISO9796d2PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int n, boolean bl) {
        this.cipher = asymmetricBlockCipher;
        this.digest = digest;
        this.hLen = digest.getDigestSize();
        this.saltLength = n;
        if (bl) {
            this.trailer = 188;
        } else {
            Integer n2 = ISOTrailers.getTrailer(digest);
            if (n2 != null) {
                this.trailer = n2;
            } else {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
        }
    }

    public ISO9796d2PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int n) {
        this(asymmetricBlockCipher, digest, n, false);
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters;
        int n = this.saltLength;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            rSAKeyParameters = (RSAKeyParameters)parametersWithRandom.getParameters();
            if (bl) {
                this.random = parametersWithRandom.getRandom();
            }
        } else if (cipherParameters instanceof ParametersWithSalt) {
            ParametersWithSalt parametersWithSalt = (ParametersWithSalt)cipherParameters;
            rSAKeyParameters = (RSAKeyParameters)parametersWithSalt.getParameters();
            this.standardSalt = parametersWithSalt.getSalt();
            n = this.standardSalt.length;
            if (this.standardSalt.length != this.saltLength) {
                throw new IllegalArgumentException("Fixed salt is of wrong length");
            }
        } else {
            rSAKeyParameters = (RSAKeyParameters)cipherParameters;
            if (bl) {
                this.random = new SecureRandom();
            }
        }
        this.cipher.init(bl, rSAKeyParameters);
        this.keyBits = rSAKeyParameters.getModulus().bitLength();
        this.block = new byte[(this.keyBits + 7) / 8];
        this.mBuf = this.trailer == 188 ? new byte[this.block.length - this.digest.getDigestSize() - n - 1 - 1] : new byte[this.block.length - this.digest.getDigestSize() - n - 1 - 2];
        this.reset();
    }

    private boolean isSameAs(byte[] byArray, byte[] byArray2) {
        boolean bl = true;
        if (this.messageLength != byArray2.length) {
            bl = false;
        }
        for (int i = 0; i != byArray2.length; ++i) {
            if (byArray[i] == byArray2[i]) continue;
            bl = false;
        }
        return bl;
    }

    private void clearBlock(byte[] byArray) {
        for (int i = 0; i != byArray.length; ++i) {
            byArray[i] = 0;
        }
    }

    public void updateWithRecoveredMessage(byte[] byArray) throws InvalidCipherTextException {
        int n;
        Object object;
        int n2;
        byte[] byArray2 = this.cipher.processBlock(byArray, 0, byArray.length);
        if (byArray2.length < (this.keyBits + 7) / 8) {
            byte[] byArray3 = new byte[(this.keyBits + 7) / 8];
            System.arraycopy(byArray2, 0, byArray3, byArray3.length - byArray2.length, byArray2.length);
            this.clearBlock(byArray2);
            byArray2 = byArray3;
        }
        if ((byArray2[byArray2.length - 1] & 0xFF ^ 0xBC) == 0) {
            n2 = 1;
        } else {
            int n3 = (byArray2[byArray2.length - 2] & 0xFF) << 8 | byArray2[byArray2.length - 1] & 0xFF;
            object = ISOTrailers.getTrailer(this.digest);
            if (object != null) {
                if (n3 != (Integer)object) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + n3);
                }
            } else {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            n2 = 2;
        }
        byte[] byArray4 = new byte[this.hLen];
        this.digest.doFinal(byArray4, 0);
        object = this.maskGeneratorFunction1(byArray2, byArray2.length - this.hLen - n2, this.hLen, byArray2.length - this.hLen - n2);
        for (n = 0; n != ((Object)object).length; ++n) {
            int n4 = n;
            byArray2[n4] = (byte)(byArray2[n4] ^ object[n]);
        }
        byArray2[0] = (byte)(byArray2[0] & 0x7F);
        for (n = 0; n != byArray2.length && byArray2[n] != 1; ++n) {
        }
        if (++n >= byArray2.length) {
            this.clearBlock(byArray2);
        }
        this.fullMessage = n > 1;
        this.recoveredMessage = new byte[((Object)object).length - n - this.saltLength];
        System.arraycopy(byArray2, n, this.recoveredMessage, 0, this.recoveredMessage.length);
        System.arraycopy(this.recoveredMessage, 0, this.mBuf, 0, this.recoveredMessage.length);
        this.preSig = byArray;
        this.preBlock = byArray2;
        this.preMStart = n;
        this.preTLength = n2;
    }

    public void update(byte by) {
        if (this.preSig == null && this.messageLength < this.mBuf.length) {
            this.mBuf[this.messageLength++] = by;
        } else {
            this.digest.update(by);
        }
    }

    public void update(byte[] byArray, int n, int n2) {
        if (this.preSig == null) {
            while (n2 > 0 && this.messageLength < this.mBuf.length) {
                this.update(byArray[n]);
                ++n;
                --n2;
            }
        }
        if (n2 > 0) {
            this.digest.update(byArray, n, n2);
        }
    }

    public void reset() {
        this.digest.reset();
        this.messageLength = 0;
        if (this.mBuf != null) {
            this.clearBlock(this.mBuf);
        }
        if (this.recoveredMessage != null) {
            this.clearBlock(this.recoveredMessage);
            this.recoveredMessage = null;
        }
        this.fullMessage = false;
        if (this.preSig != null) {
            this.preSig = null;
            this.clearBlock(this.preBlock);
            this.preBlock = null;
        }
    }

    public byte[] generateSignature() throws CryptoException {
        byte[] byArray;
        int n = this.digest.getDigestSize();
        byte[] byArray2 = new byte[n];
        this.digest.doFinal(byArray2, 0);
        byte[] byArray3 = new byte[8];
        this.LtoOSP(this.messageLength * 8, byArray3);
        this.digest.update(byArray3, 0, byArray3.length);
        this.digest.update(this.mBuf, 0, this.messageLength);
        this.digest.update(byArray2, 0, byArray2.length);
        if (this.standardSalt != null) {
            byArray = this.standardSalt;
        } else {
            byArray = new byte[this.saltLength];
            this.random.nextBytes(byArray);
        }
        this.digest.update(byArray, 0, byArray.length);
        byte[] byArray4 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray4, 0);
        int n2 = 2;
        if (this.trailer == 188) {
            n2 = 1;
        }
        int n3 = this.block.length - this.messageLength - byArray.length - this.hLen - n2 - 1;
        this.block[n3] = 1;
        System.arraycopy(this.mBuf, 0, this.block, n3 + 1, this.messageLength);
        System.arraycopy(byArray, 0, this.block, n3 + 1 + this.messageLength, byArray.length);
        byte[] byArray5 = this.maskGeneratorFunction1(byArray4, 0, byArray4.length, this.block.length - this.hLen - n2);
        for (int i = 0; i != byArray5.length; ++i) {
            int n4 = i;
            this.block[n4] = (byte)(this.block[n4] ^ byArray5[i]);
        }
        System.arraycopy(byArray4, 0, this.block, this.block.length - this.hLen - n2, this.hLen);
        if (this.trailer == 188) {
            this.block[this.block.length - 1] = -68;
        } else {
            this.block[this.block.length - 2] = (byte)(this.trailer >>> 8);
            this.block[this.block.length - 1] = (byte)this.trailer;
        }
        this.block[0] = (byte)(this.block[0] & 0x7F);
        byte[] byArray6 = this.cipher.processBlock(this.block, 0, this.block.length);
        this.recoveredMessage = new byte[this.messageLength];
        this.fullMessage = this.messageLength <= this.mBuf.length;
        System.arraycopy(this.mBuf, 0, this.recoveredMessage, 0, this.recoveredMessage.length);
        this.clearBlock(this.mBuf);
        this.clearBlock(this.block);
        this.messageLength = 0;
        return byArray6;
    }

    public boolean verifySignature(byte[] byArray) {
        byte[] byArray2 = new byte[this.hLen];
        this.digest.doFinal(byArray2, 0);
        int n = 0;
        if (this.preSig == null) {
            try {
                this.updateWithRecoveredMessage(byArray);
            } catch (Exception exception) {
                return false;
            }
        } else if (!Arrays.areEqual(this.preSig, byArray)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        }
        byte[] byArray3 = this.preBlock;
        n = this.preMStart;
        int n2 = this.preTLength;
        this.preSig = null;
        this.preBlock = null;
        byte[] byArray4 = new byte[8];
        this.LtoOSP(this.recoveredMessage.length * 8, byArray4);
        this.digest.update(byArray4, 0, byArray4.length);
        if (this.recoveredMessage.length != 0) {
            this.digest.update(this.recoveredMessage, 0, this.recoveredMessage.length);
        }
        this.digest.update(byArray2, 0, byArray2.length);
        if (this.standardSalt != null) {
            this.digest.update(this.standardSalt, 0, this.standardSalt.length);
        } else {
            this.digest.update(byArray3, n + this.recoveredMessage.length, this.saltLength);
        }
        byte[] byArray5 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray5, 0);
        int n3 = byArray3.length - n2 - byArray5.length;
        boolean bl = true;
        for (int i = 0; i != byArray5.length; ++i) {
            if (byArray5[i] == byArray3[n3 + i]) continue;
            bl = false;
        }
        this.clearBlock(byArray3);
        this.clearBlock(byArray5);
        if (!bl) {
            this.fullMessage = false;
            this.messageLength = 0;
            this.clearBlock(this.recoveredMessage);
            return false;
        }
        if (this.messageLength != 0 && !this.isSameAs(this.mBuf, this.recoveredMessage)) {
            this.messageLength = 0;
            this.clearBlock(this.mBuf);
            return false;
        }
        this.messageLength = 0;
        this.clearBlock(this.mBuf);
        return true;
    }

    public boolean hasFullMessage() {
        return this.fullMessage;
    }

    public byte[] getRecoveredMessage() {
        return this.recoveredMessage;
    }

    private void ItoOSP(int n, byte[] byArray) {
        byArray[0] = (byte)(n >>> 24);
        byArray[1] = (byte)(n >>> 16);
        byArray[2] = (byte)(n >>> 8);
        byArray[3] = (byte)(n >>> 0);
    }

    private void LtoOSP(long l, byte[] byArray) {
        byArray[0] = (byte)(l >>> 56);
        byArray[1] = (byte)(l >>> 48);
        byArray[2] = (byte)(l >>> 40);
        byArray[3] = (byte)(l >>> 32);
        byArray[4] = (byte)(l >>> 24);
        byArray[5] = (byte)(l >>> 16);
        byArray[6] = (byte)(l >>> 8);
        byArray[7] = (byte)(l >>> 0);
    }

    private byte[] maskGeneratorFunction1(byte[] byArray, int n, int n2, int n3) {
        int n4;
        byte[] byArray2 = new byte[n3];
        byte[] byArray3 = new byte[this.hLen];
        byte[] byArray4 = new byte[4];
        this.digest.reset();
        for (n4 = 0; n4 < n3 / this.hLen; ++n4) {
            this.ItoOSP(n4, byArray4);
            this.digest.update(byArray, n, n2);
            this.digest.update(byArray4, 0, byArray4.length);
            this.digest.doFinal(byArray3, 0);
            System.arraycopy(byArray3, 0, byArray2, n4 * this.hLen, this.hLen);
        }
        if (n4 * this.hLen < n3) {
            this.ItoOSP(n4, byArray4);
            this.digest.update(byArray, n, n2);
            this.digest.update(byArray4, 0, byArray4.length);
            this.digest.doFinal(byArray3, 0);
            System.arraycopy(byArray3, 0, byArray2, n4 * this.hLen, byArray2.length - n4 * this.hLen);
        }
        return byArray2;
    }
}

