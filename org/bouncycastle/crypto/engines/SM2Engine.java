/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public class SM2Engine {
    private final Digest digest;
    private boolean forEncryption;
    private ECKeyParameters ecKey;
    private ECDomainParameters ecParams;
    private int curveLength;
    private SecureRandom random;

    public SM2Engine() {
        this(new SM3Digest());
    }

    public SM2Engine(Digest digest) {
        this.digest = digest;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forEncryption = bl;
        if (bl) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.ecKey = (ECKeyParameters)parametersWithRandom.getParameters();
            this.ecParams = this.ecKey.getParameters();
            ECPoint eCPoint = ((ECPublicKeyParameters)this.ecKey).getQ().multiply(this.ecParams.getH());
            if (eCPoint.isInfinity()) {
                throw new IllegalArgumentException("invalid key: [h]Q at infinity");
            }
            this.random = parametersWithRandom.getRandom();
        } else {
            this.ecKey = (ECKeyParameters)cipherParameters;
            this.ecParams = this.ecKey.getParameters();
        }
        this.curveLength = (this.ecParams.getCurve().getFieldSize() + 7) / 8;
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return this.encrypt(byArray, n, n2);
        }
        return this.decrypt(byArray, n, n2);
    }

    private byte[] encrypt(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        ECPoint eCPoint;
        byte[] byArray2;
        Object object;
        byte[] byArray3 = new byte[n2];
        System.arraycopy(byArray, n, byArray3, 0, byArray3.length);
        do {
            object = this.nextK();
            ECPoint eCPoint2 = this.ecParams.getG().multiply((BigInteger)object).normalize();
            byArray2 = eCPoint2.getEncoded(false);
            eCPoint = ((ECPublicKeyParameters)this.ecKey).getQ().multiply((BigInteger)object).normalize();
            this.kdf(this.digest, eCPoint, byArray3);
        } while (this.notEncrypted(byArray3, byArray, n));
        object = new byte[this.digest.getDigestSize()];
        this.addFieldElement(this.digest, eCPoint.getAffineXCoord());
        this.digest.update(byArray, n, n2);
        this.addFieldElement(this.digest, eCPoint.getAffineYCoord());
        this.digest.doFinal((byte[])object, 0);
        return Arrays.concatenate(byArray2, byArray3, (byte[])object);
    }

    private byte[] decrypt(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        byte[] byArray2 = new byte[this.curveLength * 2 + 1];
        System.arraycopy(byArray, n, byArray2, 0, byArray2.length);
        ECPoint eCPoint = this.ecParams.getCurve().decodePoint(byArray2);
        ECPoint eCPoint2 = eCPoint.multiply(this.ecParams.getH());
        if (eCPoint2.isInfinity()) {
            throw new InvalidCipherTextException("[h]C1 at infinity");
        }
        eCPoint = eCPoint.multiply(((ECPrivateKeyParameters)this.ecKey).getD()).normalize();
        byte[] byArray3 = new byte[n2 - byArray2.length - this.digest.getDigestSize()];
        System.arraycopy(byArray, n + byArray2.length, byArray3, 0, byArray3.length);
        this.kdf(this.digest, eCPoint, byArray3);
        byte[] byArray4 = new byte[this.digest.getDigestSize()];
        this.addFieldElement(this.digest, eCPoint.getAffineXCoord());
        this.digest.update(byArray3, 0, byArray3.length);
        this.addFieldElement(this.digest, eCPoint.getAffineYCoord());
        this.digest.doFinal(byArray4, 0);
        int n3 = 0;
        for (int i = 0; i != byArray4.length; ++i) {
            n3 |= byArray4[i] ^ byArray[byArray2.length + byArray3.length + i];
        }
        this.clearBlock(byArray2);
        this.clearBlock(byArray4);
        if (n3 != 0) {
            this.clearBlock(byArray3);
            throw new InvalidCipherTextException("invalid cipher text");
        }
        return byArray3;
    }

    private boolean notEncrypted(byte[] byArray, byte[] byArray2, int n) {
        for (int i = 0; i != byArray.length; ++i) {
            if (byArray[i] == byArray2[n]) continue;
            return false;
        }
        return true;
    }

    private void kdf(Digest digest, ECPoint eCPoint, byte[] byArray) {
        int n = 1;
        int n2 = digest.getDigestSize();
        byte[] byArray2 = new byte[digest.getDigestSize()];
        int n3 = 0;
        for (int i = 1; i <= (byArray.length + n2 - 1) / n2; ++i) {
            this.addFieldElement(digest, eCPoint.getAffineXCoord());
            this.addFieldElement(digest, eCPoint.getAffineYCoord());
            digest.update((byte)(n >> 24));
            digest.update((byte)(n >> 16));
            digest.update((byte)(n >> 8));
            digest.update((byte)n);
            digest.doFinal(byArray2, 0);
            if (n3 + byArray2.length < byArray.length) {
                this.xor(byArray, byArray2, n3, byArray2.length);
            } else {
                this.xor(byArray, byArray2, n3, byArray.length - n3);
            }
            n3 += byArray2.length;
            ++n;
        }
    }

    private void xor(byte[] byArray, byte[] byArray2, int n, int n2) {
        for (int i = 0; i != n2; ++i) {
            int n3 = n + i;
            byArray[n3] = (byte)(byArray[n3] ^ byArray2[i]);
        }
    }

    private BigInteger nextK() {
        BigInteger bigInteger;
        int n = this.ecParams.getN().bitLength();
        while ((bigInteger = new BigInteger(n, this.random)).equals(ECConstants.ZERO) || bigInteger.compareTo(this.ecParams.getN()) >= 0) {
        }
        return bigInteger;
    }

    private void addFieldElement(Digest digest, ECFieldElement eCFieldElement) {
        byte[] byArray = BigIntegers.asUnsignedByteArray(this.curveLength, eCFieldElement.toBigInteger());
        digest.update(byArray, 0, byArray.length);
    }

    private void clearBlock(byte[] byArray) {
        for (int i = 0; i != byArray.length; ++i) {
            byArray[i] = 0;
        }
    }
}

