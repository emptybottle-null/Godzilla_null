/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.ElGamalKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.BigIntegers;

public class ElGamalEngine
implements AsymmetricBlockCipher {
    private ElGamalKeyParameters key;
    private SecureRandom random;
    private boolean forEncryption;
    private int bitSize;
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);

    public void init(boolean bl, CipherParameters cipherParameters) {
        Object object;
        if (cipherParameters instanceof ParametersWithRandom) {
            object = (ParametersWithRandom)cipherParameters;
            this.key = (ElGamalKeyParameters)((ParametersWithRandom)object).getParameters();
            this.random = ((ParametersWithRandom)object).getRandom();
        } else {
            this.key = (ElGamalKeyParameters)cipherParameters;
            this.random = new SecureRandom();
        }
        this.forEncryption = bl;
        object = this.key.getParameters().getP();
        this.bitSize = ((BigInteger)object).bitLength();
        if (bl) {
            if (!(this.key instanceof ElGamalPublicKeyParameters)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.key instanceof ElGamalPrivateKeyParameters)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }

    public int getInputBlockSize() {
        if (this.forEncryption) {
            return (this.bitSize - 1) / 8;
        }
        return 2 * ((this.bitSize + 7) / 8);
    }

    public int getOutputBlockSize() {
        if (this.forEncryption) {
            return 2 * ((this.bitSize + 7) / 8);
        }
        return (this.bitSize - 1) / 8;
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) {
        byte[] byArray2;
        int n3;
        if (this.key == null) {
            throw new IllegalStateException("ElGamal engine not initialised");
        }
        int n4 = n3 = this.forEncryption ? (this.bitSize - 1 + 7) / 8 : this.getInputBlockSize();
        if (n2 > n3) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        BigInteger bigInteger = this.key.getParameters().getP();
        if (this.key instanceof ElGamalPrivateKeyParameters) {
            byte[] byArray3 = new byte[n2 / 2];
            byte[] byArray4 = new byte[n2 / 2];
            System.arraycopy(byArray, n, byArray3, 0, byArray3.length);
            System.arraycopy(byArray, n + byArray3.length, byArray4, 0, byArray4.length);
            BigInteger bigInteger2 = new BigInteger(1, byArray3);
            BigInteger bigInteger3 = new BigInteger(1, byArray4);
            ElGamalPrivateKeyParameters elGamalPrivateKeyParameters = (ElGamalPrivateKeyParameters)this.key;
            BigInteger bigInteger4 = bigInteger2.modPow(bigInteger.subtract(ONE).subtract(elGamalPrivateKeyParameters.getX()), bigInteger).multiply(bigInteger3).mod(bigInteger);
            return BigIntegers.asUnsignedByteArray(bigInteger4);
        }
        if (n != 0 || n2 != byArray.length) {
            byArray2 = new byte[n2];
            System.arraycopy(byArray, n, byArray2, 0, n2);
        } else {
            byArray2 = byArray;
        }
        BigInteger bigInteger5 = new BigInteger(1, byArray2);
        if (bigInteger5.compareTo(bigInteger) >= 0) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        ElGamalPublicKeyParameters elGamalPublicKeyParameters = (ElGamalPublicKeyParameters)this.key;
        int n5 = bigInteger.bitLength();
        BigInteger bigInteger6 = new BigInteger(n5, this.random);
        while (bigInteger6.equals(ZERO) || bigInteger6.compareTo(bigInteger.subtract(TWO)) > 0) {
            bigInteger6 = new BigInteger(n5, this.random);
        }
        BigInteger bigInteger7 = this.key.getParameters().getG();
        BigInteger bigInteger8 = bigInteger7.modPow(bigInteger6, bigInteger);
        BigInteger bigInteger9 = bigInteger5.multiply(elGamalPublicKeyParameters.getY().modPow(bigInteger6, bigInteger)).mod(bigInteger);
        byte[] byArray5 = bigInteger8.toByteArray();
        byte[] byArray6 = bigInteger9.toByteArray();
        byte[] byArray7 = new byte[this.getOutputBlockSize()];
        if (byArray5.length > byArray7.length / 2) {
            System.arraycopy(byArray5, 1, byArray7, byArray7.length / 2 - (byArray5.length - 1), byArray5.length - 1);
        } else {
            System.arraycopy(byArray5, 0, byArray7, byArray7.length / 2 - byArray5.length, byArray5.length);
        }
        if (byArray6.length > byArray7.length / 2) {
            System.arraycopy(byArray6, 1, byArray7, byArray7.length - (byArray6.length - 1), byArray6.length - 1);
        } else {
            System.arraycopy(byArray6, 0, byArray7, byArray7.length - byArray6.length, byArray6.length);
        }
        return byArray7;
    }
}

