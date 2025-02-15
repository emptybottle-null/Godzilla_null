/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.KeyEncapsulation;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public class ECIESKeyEncapsulation
implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DerivationFunction kdf;
    private SecureRandom rnd;
    private ECKeyParameters key;
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom, boolean bl, boolean bl2, boolean bl3) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = bl;
        this.OldCofactorMode = bl2;
        this.SingleHashMode = bl3;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ECKeyParameters)) {
            throw new IllegalArgumentException("EC key required");
        }
        this.key = (ECKeyParameters)cipherParameters;
    }

    public CipherParameters encrypt(byte[] byArray, int n, int n2) throws IllegalArgumentException {
        if (!(this.key instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)this.key;
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        ECCurve eCCurve = eCDomainParameters.getCurve();
        BigInteger bigInteger = eCDomainParameters.getN();
        BigInteger bigInteger2 = eCDomainParameters.getH();
        BigInteger bigInteger3 = BigIntegers.createRandomInRange(ONE, bigInteger, this.rnd);
        BigInteger bigInteger4 = this.CofactorMode ? bigInteger3.multiply(bigInteger2).mod(bigInteger) : bigInteger3;
        ECMultiplier eCMultiplier = this.createBasePointMultiplier();
        ECPoint[] eCPointArray = new ECPoint[]{eCMultiplier.multiply(eCDomainParameters.getG(), bigInteger3), eCPublicKeyParameters.getQ().multiply(bigInteger4)};
        eCCurve.normalizeAll(eCPointArray);
        ECPoint eCPoint = eCPointArray[0];
        ECPoint eCPoint2 = eCPointArray[1];
        byte[] byArray2 = eCPoint.getEncoded(false);
        System.arraycopy(byArray2, 0, byArray, n, byArray2.length);
        byte[] byArray3 = eCPoint2.getAffineXCoord().getEncoded();
        return this.deriveKey(n2, byArray2, byArray3);
    }

    public CipherParameters encrypt(byte[] byArray, int n) {
        return this.encrypt(byArray, 0, n);
    }

    public CipherParameters decrypt(byte[] byArray, int n, int n2, int n3) throws IllegalArgumentException {
        ECPoint eCPoint;
        if (!(this.key instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("Private key required for encryption");
        }
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters)this.key;
        ECDomainParameters eCDomainParameters = eCPrivateKeyParameters.getParameters();
        ECCurve eCCurve = eCDomainParameters.getCurve();
        BigInteger bigInteger = eCDomainParameters.getN();
        BigInteger bigInteger2 = eCDomainParameters.getH();
        byte[] byArray2 = new byte[n2];
        System.arraycopy(byArray, n, byArray2, 0, n2);
        ECPoint eCPoint2 = eCPoint = eCCurve.decodePoint(byArray2);
        if (this.CofactorMode || this.OldCofactorMode) {
            eCPoint2 = eCPoint2.multiply(bigInteger2);
        }
        BigInteger bigInteger3 = eCPrivateKeyParameters.getD();
        if (this.CofactorMode) {
            bigInteger3 = bigInteger3.multiply(bigInteger2.modInverse(bigInteger)).mod(bigInteger);
        }
        ECPoint eCPoint3 = eCPoint2.multiply(bigInteger3).normalize();
        byte[] byArray3 = eCPoint3.getAffineXCoord().getEncoded();
        return this.deriveKey(n3, byArray2, byArray3);
    }

    public CipherParameters decrypt(byte[] byArray, int n) {
        return this.decrypt(byArray, 0, byArray.length, n);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected KeyParameter deriveKey(int n, byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = byArray2;
        if (!this.SingleHashMode) {
            byArray3 = Arrays.concatenate(byArray, byArray2);
            Arrays.fill(byArray2, (byte)0);
        }
        try {
            this.kdf.init(new KDFParameters(byArray3, null));
            byte[] byArray4 = new byte[n];
            this.kdf.generateBytes(byArray4, 0, byArray4.length);
            KeyParameter keyParameter = new KeyParameter(byArray4);
            return keyParameter;
        } finally {
            Arrays.fill(byArray3, (byte)0);
        }
    }
}

