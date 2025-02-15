/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

class RSACoreEngine {
    private RSAKeyParameters key;
    private boolean forEncryption;

    RSACoreEngine() {
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.key = (RSAKeyParameters)parametersWithRandom.getParameters();
        } else {
            this.key = (RSAKeyParameters)cipherParameters;
        }
        this.forEncryption = bl;
    }

    public int getInputBlockSize() {
        int n = this.key.getModulus().bitLength();
        if (this.forEncryption) {
            return (n + 7) / 8 - 1;
        }
        return (n + 7) / 8;
    }

    public int getOutputBlockSize() {
        int n = this.key.getModulus().bitLength();
        if (this.forEncryption) {
            return (n + 7) / 8;
        }
        return (n + 7) / 8 - 1;
    }

    public BigInteger convertInput(byte[] byArray, int n, int n2) {
        byte[] byArray2;
        if (n2 > this.getInputBlockSize() + 1) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        if (n2 == this.getInputBlockSize() + 1 && !this.forEncryption) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        if (n != 0 || n2 != byArray.length) {
            byArray2 = new byte[n2];
            System.arraycopy(byArray, n, byArray2, 0, n2);
        } else {
            byArray2 = byArray;
        }
        BigInteger bigInteger = new BigInteger(1, byArray2);
        if (bigInteger.compareTo(this.key.getModulus()) >= 0) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        return bigInteger;
    }

    public byte[] convertOutput(BigInteger bigInteger) {
        byte[] byArray = bigInteger.toByteArray();
        if (this.forEncryption) {
            if (byArray[0] == 0 && byArray.length > this.getOutputBlockSize()) {
                byte[] byArray2 = new byte[byArray.length - 1];
                System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
                return byArray2;
            }
            if (byArray.length < this.getOutputBlockSize()) {
                byte[] byArray3 = new byte[this.getOutputBlockSize()];
                System.arraycopy(byArray, 0, byArray3, byArray3.length - byArray.length, byArray.length);
                return byArray3;
            }
        } else if (byArray[0] == 0) {
            byte[] byArray4 = new byte[byArray.length - 1];
            System.arraycopy(byArray, 1, byArray4, 0, byArray4.length);
            return byArray4;
        }
        return byArray;
    }

    public BigInteger processBlock(BigInteger bigInteger) {
        if (this.key instanceof RSAPrivateCrtKeyParameters) {
            RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters)this.key;
            BigInteger bigInteger2 = rSAPrivateCrtKeyParameters.getP();
            BigInteger bigInteger3 = rSAPrivateCrtKeyParameters.getQ();
            BigInteger bigInteger4 = rSAPrivateCrtKeyParameters.getDP();
            BigInteger bigInteger5 = rSAPrivateCrtKeyParameters.getDQ();
            BigInteger bigInteger6 = rSAPrivateCrtKeyParameters.getQInv();
            BigInteger bigInteger7 = bigInteger.remainder(bigInteger2).modPow(bigInteger4, bigInteger2);
            BigInteger bigInteger8 = bigInteger.remainder(bigInteger3).modPow(bigInteger5, bigInteger3);
            BigInteger bigInteger9 = bigInteger7.subtract(bigInteger8);
            bigInteger9 = bigInteger9.multiply(bigInteger6);
            bigInteger9 = bigInteger9.mod(bigInteger2);
            BigInteger bigInteger10 = bigInteger9.multiply(bigInteger3);
            bigInteger10 = bigInteger10.add(bigInteger8);
            return bigInteger10;
        }
        return bigInteger.modPow(this.key.getExponent(), this.key.getModulus());
    }
}

