/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.rainbow.RainbowSigner;
import org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeysToParams;

public class SignatureSpi
extends java.security.SignatureSpi {
    private Digest digest;
    private RainbowSigner signer;
    private SecureRandom random;

    protected SignatureSpi(Digest digest, RainbowSigner rainbowSigner) {
        this.digest = digest;
        this.signer = rainbowSigner;
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter = RainbowKeysToParams.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, asymmetricKeyParameter);
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        this.engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters cipherParameters = RainbowKeysToParams.generatePrivateKeyParameter(privateKey);
        if (this.random != null) {
            cipherParameters = new ParametersWithRandom(cipherParameters, this.random);
        }
        this.digest.reset();
        this.signer.init(true, cipherParameters);
    }

    protected void engineUpdate(byte by) throws SignatureException {
        this.digest.update(by);
    }

    protected void engineUpdate(byte[] byArray, int n, int n2) throws SignatureException {
        this.digest.update(byArray, n, n2);
    }

    protected byte[] engineSign() throws SignatureException {
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        try {
            byte[] byArray2 = this.signer.generateSignature(byArray);
            return byArray2;
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        return this.signer.verifySignature(byArray2, byArray);
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected void engineSetParameter(String string, Object object) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String string) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    public static class withSha224
    extends SignatureSpi {
        public withSha224() {
            super(new SHA224Digest(), new RainbowSigner());
        }
    }

    public static class withSha256
    extends SignatureSpi {
        public withSha256() {
            super(new SHA256Digest(), new RainbowSigner());
        }
    }

    public static class withSha384
    extends SignatureSpi {
        public withSha384() {
            super(new SHA384Digest(), new RainbowSigner());
        }
    }

    public static class withSha512
    extends SignatureSpi {
        public withSha512() {
            super(new SHA512Digest(), new RainbowSigner());
        }
    }
}

