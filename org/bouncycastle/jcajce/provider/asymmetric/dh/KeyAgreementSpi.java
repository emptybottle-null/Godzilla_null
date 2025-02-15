/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dh;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.dh.BCDHPublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;

public class KeyAgreementSpi
extends BaseAgreementSpi {
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private BigInteger x;
    private BigInteger p;
    private BigInteger g;
    private BigInteger result;

    public KeyAgreementSpi() {
        super("Diffie-Hellman", null);
    }

    public KeyAgreementSpi(String string, DerivationFunction derivationFunction) {
        super(string, derivationFunction);
    }

    protected byte[] bigIntToBytes(BigInteger bigInteger) {
        int n = (this.p.bitLength() + 7) / 8;
        byte[] byArray = bigInteger.toByteArray();
        if (byArray.length == n) {
            return byArray;
        }
        if (byArray[0] == 0 && byArray.length == n + 1) {
            byte[] byArray2 = new byte[byArray.length - 1];
            System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
            return byArray2;
        }
        byte[] byArray3 = new byte[n];
        System.arraycopy(byArray, 0, byArray3, byArray3.length - byArray.length, byArray.length);
        return byArray3;
    }

    protected Key engineDoPhase(Key key, boolean bl) throws InvalidKeyException, IllegalStateException {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        if (!(key instanceof DHPublicKey)) {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        }
        DHPublicKey dHPublicKey = (DHPublicKey)key;
        if (!dHPublicKey.getParams().getG().equals(this.g) || !dHPublicKey.getParams().getP().equals(this.p)) {
            throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
        }
        BigInteger bigInteger = ((DHPublicKey)key).getY();
        if (bigInteger == null || bigInteger.compareTo(TWO) < 0 || bigInteger.compareTo(this.p.subtract(ONE)) >= 0) {
            throw new InvalidKeyException("Invalid DH PublicKey");
        }
        this.result = bigInteger.modPow(this.x, this.p);
        if (this.result.compareTo(ONE) == 0) {
            throw new InvalidKeyException("Shared key can't be 1");
        }
        if (bl) {
            return null;
        }
        return new BCDHPublicKey(this.result, dHPublicKey.getParams());
    }

    protected byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        return super.engineGenerateSecret();
    }

    protected int engineGenerateSecret(byte[] byArray, int n) throws IllegalStateException, ShortBufferException {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        return super.engineGenerateSecret(byArray, n);
    }

    protected SecretKey engineGenerateSecret(String string) throws NoSuchAlgorithmException {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        byte[] byArray = this.bigIntToBytes(this.result);
        if (string.equals("TlsPremasterSecret")) {
            return new SecretKeySpec(KeyAgreementSpi.trimZeroes(byArray), string);
        }
        return super.engineGenerateSecret(string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey)key;
        if (algorithmParameterSpec != null) {
            if (algorithmParameterSpec instanceof DHParameterSpec) {
                DHParameterSpec dHParameterSpec = (DHParameterSpec)algorithmParameterSpec;
                this.p = dHParameterSpec.getP();
                this.g = dHParameterSpec.getG();
            } else {
                if (!(algorithmParameterSpec instanceof UserKeyingMaterialSpec)) throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
                this.p = dHPrivateKey.getParams().getP();
                this.g = dHPrivateKey.getParams().getG();
                this.ukmParameters = ((UserKeyingMaterialSpec)algorithmParameterSpec).getUserKeyingMaterial();
            }
        } else {
            this.p = dHPrivateKey.getParams().getP();
            this.g = dHPrivateKey.getParams().getG();
        }
        this.x = this.result = dHPrivateKey.getX();
    }

    protected void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey)key;
        this.p = dHPrivateKey.getParams().getP();
        this.g = dHPrivateKey.getParams().getG();
        this.x = this.result = dHPrivateKey.getX();
    }

    protected byte[] calcSecret() {
        return this.bigIntToBytes(this.result);
    }

    public static class DHwithRFC2631KDF
    extends KeyAgreementSpi {
        public DHwithRFC2631KDF() {
            super("DHwithRFC2631KDF", new DHKEKGenerator(DigestFactory.createSHA1()));
        }
    }
}

