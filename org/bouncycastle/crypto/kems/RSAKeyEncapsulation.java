/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.KeyEncapsulation;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.BigIntegers;

public class RSAKeyEncapsulation
implements KeyEncapsulation {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DerivationFunction kdf;
    private SecureRandom rnd;
    private RSAKeyParameters key;

    public RSAKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof RSAKeyParameters)) {
            throw new IllegalArgumentException("RSA key required");
        }
        this.key = (RSAKeyParameters)cipherParameters;
    }

    public CipherParameters encrypt(byte[] byArray, int n, int n2) throws IllegalArgumentException {
        if (this.key.isPrivate()) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        BigInteger bigInteger = this.key.getModulus();
        BigInteger bigInteger2 = this.key.getExponent();
        BigInteger bigInteger3 = BigIntegers.createRandomInRange(ZERO, bigInteger.subtract(ONE), this.rnd);
        BigInteger bigInteger4 = bigInteger3.modPow(bigInteger2, bigInteger);
        byte[] byArray2 = BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger4);
        System.arraycopy(byArray2, 0, byArray, n, byArray2.length);
        return this.generateKey(bigInteger, bigInteger3, n2);
    }

    public CipherParameters encrypt(byte[] byArray, int n) {
        return this.encrypt(byArray, 0, n);
    }

    public CipherParameters decrypt(byte[] byArray, int n, int n2, int n3) throws IllegalArgumentException {
        if (!this.key.isPrivate()) {
            throw new IllegalArgumentException("Private key required for decryption");
        }
        BigInteger bigInteger = this.key.getModulus();
        BigInteger bigInteger2 = this.key.getExponent();
        byte[] byArray2 = new byte[n2];
        System.arraycopy(byArray, n, byArray2, 0, byArray2.length);
        BigInteger bigInteger3 = new BigInteger(1, byArray2);
        BigInteger bigInteger4 = bigInteger3.modPow(bigInteger2, bigInteger);
        return this.generateKey(bigInteger, bigInteger4, n3);
    }

    public CipherParameters decrypt(byte[] byArray, int n) {
        return this.decrypt(byArray, 0, byArray.length, n);
    }

    protected KeyParameter generateKey(BigInteger bigInteger, BigInteger bigInteger2, int n) {
        byte[] byArray = BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger2);
        this.kdf.init(new KDFParameters(byArray, null));
        byte[] byArray2 = new byte[n];
        this.kdf.generateBytes(byArray2, 0, byArray2.length);
        return new KeyParameter(byArray2);
    }
}

