/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.sphincs;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHA512tDigest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256Signer;
import org.bouncycastle.pqc.jcajce.provider.sphincs.BCSphincs256PrivateKey;
import org.bouncycastle.pqc.jcajce.provider.sphincs.BCSphincs256PublicKey;

public class SignatureSpi
extends java.security.SignatureSpi {
    private Digest digest;
    private SPHINCS256Signer signer;
    private SecureRandom random;

    protected SignatureSpi(Digest digest, SPHINCS256Signer sPHINCS256Signer) {
        this.digest = digest;
        this.signer = sPHINCS256Signer;
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCSphincs256PublicKey)) {
            throw new InvalidKeyException("unknown public key passed to SPHINCS-256");
        }
        CipherParameters cipherParameters = ((BCSphincs256PublicKey)publicKey).getKeyParams();
        this.digest.reset();
        this.signer.init(false, cipherParameters);
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        this.engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters cipherParameters;
        if (privateKey instanceof BCSphincs256PrivateKey) {
            cipherParameters = ((BCSphincs256PrivateKey)privateKey).getKeyParams();
            if (this.random != null) {
                cipherParameters = new ParametersWithRandom(cipherParameters, this.random);
            }
        } else {
            throw new InvalidKeyException("unknown private key passed to SPHINCS-256");
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

    public static class withSha3_512
    extends SignatureSpi {
        public withSha3_512() {
            super(new SHA3Digest(512), new SPHINCS256Signer(new SHA3Digest(256), new SHA3Digest(512)));
        }
    }

    public static class withSha512
    extends SignatureSpi {
        public withSha512() {
            super(new SHA512Digest(), new SPHINCS256Signer(new SHA512tDigest(256), new SHA512Digest()));
        }
    }
}

