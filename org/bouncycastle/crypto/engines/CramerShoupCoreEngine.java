/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.engines.CramerShoupCiphertext;
import org.bouncycastle.crypto.params.CramerShoupKeyParameters;
import org.bouncycastle.crypto.params.CramerShoupPrivateKeyParameters;
import org.bouncycastle.crypto.params.CramerShoupPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.BigIntegers;

public class CramerShoupCoreEngine {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private CramerShoupKeyParameters key;
    private SecureRandom random;
    private boolean forEncryption;
    private String label = null;

    public void init(boolean bl, CipherParameters cipherParameters, String string) {
        this.init(bl, cipherParameters);
        this.label = string;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        SecureRandom secureRandom = null;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.key = (CramerShoupKeyParameters)parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.key = (CramerShoupKeyParameters)cipherParameters;
        }
        this.random = this.initSecureRandom(bl, secureRandom);
        this.forEncryption = bl;
    }

    public int getInputBlockSize() {
        int n = this.key.getParameters().getP().bitLength();
        if (this.forEncryption) {
            return (n + 7) / 8 - 1;
        }
        return (n + 7) / 8;
    }

    public int getOutputBlockSize() {
        int n = this.key.getParameters().getP().bitLength();
        if (this.forEncryption) {
            return (n + 7) / 8;
        }
        return (n + 7) / 8 - 1;
    }

    public BigInteger convertInput(byte[] byArray, int n, int n2) {
        byte[] byArray2;
        if (n2 > this.getInputBlockSize() + 1) {
            throw new DataLengthException("input too large for Cramer Shoup cipher.");
        }
        if (n2 == this.getInputBlockSize() + 1 && this.forEncryption) {
            throw new DataLengthException("input too large for Cramer Shoup cipher.");
        }
        if (n != 0 || n2 != byArray.length) {
            byArray2 = new byte[n2];
            System.arraycopy(byArray, n, byArray2, 0, n2);
        } else {
            byArray2 = byArray;
        }
        BigInteger bigInteger = new BigInteger(1, byArray2);
        if (bigInteger.compareTo(this.key.getParameters().getP()) >= 0) {
            throw new DataLengthException("input too large for Cramer Shoup cipher.");
        }
        return bigInteger;
    }

    public byte[] convertOutput(BigInteger bigInteger) {
        byte[] byArray = bigInteger.toByteArray();
        if (!this.forEncryption) {
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

    public CramerShoupCiphertext encryptBlock(BigInteger bigInteger) {
        CramerShoupCiphertext cramerShoupCiphertext = null;
        if (!this.key.isPrivate() && this.forEncryption && this.key instanceof CramerShoupPublicKeyParameters) {
            byte[] byArray;
            CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters)this.key;
            BigInteger bigInteger2 = cramerShoupPublicKeyParameters.getParameters().getP();
            BigInteger bigInteger3 = cramerShoupPublicKeyParameters.getParameters().getG1();
            BigInteger bigInteger4 = cramerShoupPublicKeyParameters.getParameters().getG2();
            BigInteger bigInteger5 = cramerShoupPublicKeyParameters.getH();
            if (!this.isValidMessage(bigInteger, bigInteger2)) {
                return cramerShoupCiphertext;
            }
            BigInteger bigInteger6 = this.generateRandomElement(bigInteger2, this.random);
            BigInteger bigInteger7 = bigInteger3.modPow(bigInteger6, bigInteger2);
            BigInteger bigInteger8 = bigInteger4.modPow(bigInteger6, bigInteger2);
            BigInteger bigInteger9 = bigInteger5.modPow(bigInteger6, bigInteger2).multiply(bigInteger).mod(bigInteger2);
            Digest digest = cramerShoupPublicKeyParameters.getParameters().getH();
            byte[] byArray2 = bigInteger7.toByteArray();
            digest.update(byArray2, 0, byArray2.length);
            byte[] byArray3 = bigInteger8.toByteArray();
            digest.update(byArray3, 0, byArray3.length);
            byte[] byArray4 = bigInteger9.toByteArray();
            digest.update(byArray4, 0, byArray4.length);
            if (this.label != null) {
                byArray = this.label.getBytes();
                digest.update(byArray, 0, byArray.length);
            }
            byArray = new byte[digest.getDigestSize()];
            digest.doFinal(byArray, 0);
            BigInteger bigInteger10 = new BigInteger(1, byArray);
            BigInteger bigInteger11 = cramerShoupPublicKeyParameters.getC().modPow(bigInteger6, bigInteger2).multiply(cramerShoupPublicKeyParameters.getD().modPow(bigInteger6.multiply(bigInteger10), bigInteger2)).mod(bigInteger2);
            cramerShoupCiphertext = new CramerShoupCiphertext(bigInteger7, bigInteger8, bigInteger9, bigInteger11);
        }
        return cramerShoupCiphertext;
    }

    public BigInteger decryptBlock(CramerShoupCiphertext cramerShoupCiphertext) throws CramerShoupCiphertextException {
        BigInteger bigInteger = null;
        if (this.key.isPrivate() && !this.forEncryption && this.key instanceof CramerShoupPrivateKeyParameters) {
            byte[] byArray;
            CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters)this.key;
            BigInteger bigInteger2 = cramerShoupPrivateKeyParameters.getParameters().getP();
            Digest digest = cramerShoupPrivateKeyParameters.getParameters().getH();
            byte[] byArray2 = cramerShoupCiphertext.getU1().toByteArray();
            digest.update(byArray2, 0, byArray2.length);
            byte[] byArray3 = cramerShoupCiphertext.getU2().toByteArray();
            digest.update(byArray3, 0, byArray3.length);
            byte[] byArray4 = cramerShoupCiphertext.getE().toByteArray();
            digest.update(byArray4, 0, byArray4.length);
            if (this.label != null) {
                byArray = this.label.getBytes();
                digest.update(byArray, 0, byArray.length);
            }
            byArray = new byte[digest.getDigestSize()];
            digest.doFinal(byArray, 0);
            BigInteger bigInteger3 = new BigInteger(1, byArray);
            BigInteger bigInteger4 = cramerShoupCiphertext.u1.modPow(cramerShoupPrivateKeyParameters.getX1().add(cramerShoupPrivateKeyParameters.getY1().multiply(bigInteger3)), bigInteger2).multiply(cramerShoupCiphertext.u2.modPow(cramerShoupPrivateKeyParameters.getX2().add(cramerShoupPrivateKeyParameters.getY2().multiply(bigInteger3)), bigInteger2)).mod(bigInteger2);
            if (cramerShoupCiphertext.v.equals(bigInteger4)) {
                bigInteger = cramerShoupCiphertext.e.multiply(cramerShoupCiphertext.u1.modPow(cramerShoupPrivateKeyParameters.getZ(), bigInteger2).modInverse(bigInteger2)).mod(bigInteger2);
            } else {
                throw new CramerShoupCiphertextException("Sorry, that ciphertext is not correct");
            }
        }
        return bigInteger;
    }

    private BigInteger generateRandomElement(BigInteger bigInteger, SecureRandom secureRandom) {
        return BigIntegers.createRandomInRange(ONE, bigInteger.subtract(ONE), secureRandom);
    }

    private boolean isValidMessage(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) < 0;
    }

    protected SecureRandom initSecureRandom(boolean bl, SecureRandom secureRandom) {
        return !bl ? null : (secureRandom != null ? secureRandom : new SecureRandom());
    }

    public static class CramerShoupCiphertextException
    extends Exception {
        private static final long serialVersionUID = -6360977166495345076L;

        public CramerShoupCiphertextException(String string) {
            super(string);
        }
    }
}

