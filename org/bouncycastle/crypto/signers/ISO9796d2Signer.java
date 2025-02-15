/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.ISOTrailers;
import org.bouncycastle.util.Arrays;

public class ISO9796d2Signer
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
    private int trailer;
    private int keyBits;
    private byte[] block;
    private byte[] mBuf;
    private int messageLength;
    private boolean fullMessage;
    private byte[] recoveredMessage;
    private byte[] preSig;
    private byte[] preBlock;

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean bl) {
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

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters)cipherParameters;
        this.cipher.init(bl, rSAKeyParameters);
        this.keyBits = rSAKeyParameters.getModulus().bitLength();
        this.block = new byte[(this.keyBits + 7) / 8];
        this.mBuf = this.trailer == 188 ? new byte[this.block.length - this.digest.getDigestSize() - 2] : new byte[this.block.length - this.digest.getDigestSize() - 3];
        this.reset();
    }

    private boolean isSameAs(byte[] byArray, byte[] byArray2) {
        boolean bl = true;
        if (this.messageLength > this.mBuf.length) {
            if (this.mBuf.length > byArray2.length) {
                bl = false;
            }
            for (int i = 0; i != this.mBuf.length; ++i) {
                if (byArray[i] == byArray2[i]) continue;
                bl = false;
            }
        } else {
            if (this.messageLength != byArray2.length) {
                bl = false;
            }
            for (int i = 0; i != byArray2.length; ++i) {
                if (byArray[i] == byArray2[i]) continue;
                bl = false;
            }
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
        byte[] byArray2 = this.cipher.processBlock(byArray, 0, byArray.length);
        if ((byArray2[0] & 0xC0 ^ 0x40) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        if ((byArray2[byArray2.length - 1] & 0xF ^ 0xC) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        int n2 = 0;
        if ((byArray2[byArray2.length - 1] & 0xFF ^ 0xBC) == 0) {
            n2 = 1;
        } else {
            n = (byArray2[byArray2.length - 2] & 0xFF) << 8 | byArray2[byArray2.length - 1] & 0xFF;
            Integer n3 = ISOTrailers.getTrailer(this.digest);
            if (n3 != null) {
                if (n != n3) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + n);
                }
            } else {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            n2 = 2;
        }
        n = 0;
        for (n = 0; n != byArray2.length && (byArray2[n] & 0xF ^ 0xA) != 0; ++n) {
        }
        int n4 = byArray2.length - n2 - this.digest.getDigestSize();
        if (n4 - ++n <= 0) {
            throw new InvalidCipherTextException("malformed block");
        }
        if ((byArray2[0] & 0x20) == 0) {
            this.fullMessage = true;
            this.recoveredMessage = new byte[n4 - n];
            System.arraycopy(byArray2, n, this.recoveredMessage, 0, this.recoveredMessage.length);
        } else {
            this.fullMessage = false;
            this.recoveredMessage = new byte[n4 - n];
            System.arraycopy(byArray2, n, this.recoveredMessage, 0, this.recoveredMessage.length);
        }
        this.preSig = byArray;
        this.preBlock = byArray2;
        this.digest.update(this.recoveredMessage, 0, this.recoveredMessage.length);
        this.messageLength = this.recoveredMessage.length;
        System.arraycopy(this.recoveredMessage, 0, this.mBuf, 0, this.recoveredMessage.length);
    }

    public void update(byte by) {
        this.digest.update(by);
        if (this.messageLength < this.mBuf.length) {
            this.mBuf[this.messageLength] = by;
        }
        ++this.messageLength;
    }

    public void update(byte[] byArray, int n, int n2) {
        while (n2 > 0 && this.messageLength < this.mBuf.length) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
        this.digest.update(byArray, n, n2);
        this.messageLength += n2;
    }

    public void reset() {
        this.digest.reset();
        this.messageLength = 0;
        this.clearBlock(this.mBuf);
        if (this.recoveredMessage != null) {
            this.clearBlock(this.recoveredMessage);
        }
        this.recoveredMessage = null;
        this.fullMessage = false;
        if (this.preSig != null) {
            this.preSig = null;
            this.clearBlock(this.preBlock);
            this.preBlock = null;
        }
    }

    public byte[] generateSignature() throws CryptoException {
        int n;
        int n2 = this.digest.getDigestSize();
        int n3 = 0;
        int n4 = 0;
        if (this.trailer == 188) {
            n3 = 8;
            n4 = this.block.length - n2 - 1;
            this.digest.doFinal(this.block, n4);
            this.block[this.block.length - 1] = -68;
        } else {
            n3 = 16;
            n4 = this.block.length - n2 - 2;
            this.digest.doFinal(this.block, n4);
            this.block[this.block.length - 2] = (byte)(this.trailer >>> 8);
            this.block[this.block.length - 1] = (byte)this.trailer;
        }
        int n5 = 0;
        int n6 = (n2 + this.messageLength) * 8 + n3 + 4 - this.keyBits;
        if (n6 > 0) {
            n = this.messageLength - (n6 + 7) / 8;
            n5 = 96;
            System.arraycopy(this.mBuf, 0, this.block, n4 -= n, n);
            this.recoveredMessage = new byte[n];
        } else {
            n5 = 64;
            System.arraycopy(this.mBuf, 0, this.block, n4 -= this.messageLength, this.messageLength);
            this.recoveredMessage = new byte[this.messageLength];
        }
        if (n4 - 1 > 0) {
            for (n = n4 - 1; n != 0; --n) {
                this.block[n] = -69;
            }
            int n7 = n4 - 1;
            this.block[n7] = (byte)(this.block[n7] ^ 1);
            this.block[0] = 11;
            this.block[0] = (byte)(this.block[0] | n5);
        } else {
            this.block[0] = 10;
            this.block[0] = (byte)(this.block[0] | n5);
        }
        byte[] byArray = this.cipher.processBlock(this.block, 0, this.block.length);
        this.fullMessage = (n5 & 0x20) == 0;
        System.arraycopy(this.mBuf, 0, this.recoveredMessage, 0, this.recoveredMessage.length);
        this.messageLength = 0;
        this.clearBlock(this.mBuf);
        this.clearBlock(this.block);
        return byArray;
    }

    public boolean verifySignature(byte[] byArray) {
        Object object;
        int n;
        byte[] byArray2 = null;
        if (this.preSig == null) {
            try {
                byArray2 = this.cipher.processBlock(byArray, 0, byArray.length);
            } catch (Exception exception) {
                return false;
            }
        } else {
            if (!Arrays.areEqual(this.preSig, byArray)) {
                throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
            }
            byArray2 = this.preBlock;
            this.preSig = null;
            this.preBlock = null;
        }
        if ((byArray2[0] & 0xC0 ^ 0x40) != 0) {
            return this.returnFalse(byArray2);
        }
        if ((byArray2[byArray2.length - 1] & 0xF ^ 0xC) != 0) {
            return this.returnFalse(byArray2);
        }
        int n2 = 0;
        if ((byArray2[byArray2.length - 1] & 0xFF ^ 0xBC) == 0) {
            n2 = 1;
        } else {
            n = (byArray2[byArray2.length - 2] & 0xFF) << 8 | byArray2[byArray2.length - 1] & 0xFF;
            object = ISOTrailers.getTrailer(this.digest);
            if (object != null) {
                if (n != object.intValue()) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + n);
                }
            } else {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            n2 = 2;
        }
        n = 0;
        for (n = 0; n != byArray2.length && (byArray2[n] & 0xF ^ 0xA) != 0; ++n) {
        }
        object = new byte[this.digest.getDigestSize()];
        int n3 = byArray2.length - n2 - ((byte[])object).length;
        if (n3 - ++n <= 0) {
            return this.returnFalse(byArray2);
        }
        if ((byArray2[0] & 0x20) == 0) {
            this.fullMessage = true;
            if (this.messageLength > n3 - n) {
                return this.returnFalse(byArray2);
            }
            this.digest.reset();
            this.digest.update(byArray2, n, n3 - n);
            this.digest.doFinal((byte[])object, 0);
            boolean bl = true;
            for (int i = 0; i != ((Object)object).length; ++i) {
                int n4 = n3 + i;
                byArray2[n4] = (byte)(byArray2[n4] ^ object[i]);
                if (byArray2[n3 + i] == 0) continue;
                bl = false;
            }
            if (!bl) {
                return this.returnFalse(byArray2);
            }
            this.recoveredMessage = new byte[n3 - n];
            System.arraycopy(byArray2, n, this.recoveredMessage, 0, this.recoveredMessage.length);
        } else {
            this.fullMessage = false;
            this.digest.doFinal((byte[])object, 0);
            boolean bl = true;
            for (int i = 0; i != ((Object)object).length; ++i) {
                int n5 = n3 + i;
                byArray2[n5] = (byte)(byArray2[n5] ^ object[i]);
                if (byArray2[n3 + i] == 0) continue;
                bl = false;
            }
            if (!bl) {
                return this.returnFalse(byArray2);
            }
            this.recoveredMessage = new byte[n3 - n];
            System.arraycopy(byArray2, n, this.recoveredMessage, 0, this.recoveredMessage.length);
        }
        if (this.messageLength != 0 && !this.isSameAs(this.mBuf, this.recoveredMessage)) {
            return this.returnFalse(byArray2);
        }
        this.clearBlock(this.mBuf);
        this.clearBlock(byArray2);
        this.messageLength = 0;
        return true;
    }

    private boolean returnFalse(byte[] byArray) {
        this.messageLength = 0;
        this.clearBlock(this.mBuf);
        this.clearBlock(byArray);
        return false;
    }

    public boolean hasFullMessage() {
        return this.fullMessage;
    }

    public byte[] getRecoveredMessage() {
        return this.recoveredMessage;
    }
}

