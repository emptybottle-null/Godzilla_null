/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;

public class PSSSignatureSpi
extends SignatureSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private AlgorithmParameters engineParams;
    private PSSParameterSpec paramSpec;
    private PSSParameterSpec originalSpec;
    private AsymmetricBlockCipher signer;
    private Digest contentDigest;
    private Digest mgfDigest;
    private int saltLength;
    private byte trailer;
    private boolean isRaw;
    private PSSSigner pss;

    private byte getTrailer(int n) {
        if (n == 1) {
            return -68;
        }
        throw new IllegalArgumentException("unknown trailer field");
    }

    private void setupContentDigest() {
        this.contentDigest = this.isRaw ? new NullPssDigest(this.mgfDigest) : this.mgfDigest;
    }

    protected PSSSignatureSpi(AsymmetricBlockCipher asymmetricBlockCipher, PSSParameterSpec pSSParameterSpec) {
        this(asymmetricBlockCipher, pSSParameterSpec, false);
    }

    protected PSSSignatureSpi(AsymmetricBlockCipher asymmetricBlockCipher, PSSParameterSpec pSSParameterSpec, boolean bl) {
        this.signer = asymmetricBlockCipher;
        this.originalSpec = pSSParameterSpec;
        this.paramSpec = pSSParameterSpec == null ? PSSParameterSpec.DEFAULT : pSSParameterSpec;
        this.mgfDigest = DigestFactory.getDigest(this.paramSpec.getDigestAlgorithm());
        this.saltLength = this.paramSpec.getSaltLength();
        this.trailer = this.getTrailer(this.paramSpec.getTrailerField());
        this.isRaw = bl;
        this.setupContentDigest();
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof RSAPublicKey)) {
            throw new InvalidKeyException("Supplied key is not a RSAPublicKey instance");
        }
        this.pss = new PSSSigner(this.signer, this.contentDigest, this.mgfDigest, this.saltLength, this.trailer);
        this.pss.init(false, RSAUtil.generatePublicKeyParameter((RSAPublicKey)publicKey));
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        if (!(privateKey instanceof RSAPrivateKey)) {
            throw new InvalidKeyException("Supplied key is not a RSAPrivateKey instance");
        }
        this.pss = new PSSSigner(this.signer, this.contentDigest, this.mgfDigest, this.saltLength, this.trailer);
        this.pss.init(true, new ParametersWithRandom(RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey), secureRandom));
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof RSAPrivateKey)) {
            throw new InvalidKeyException("Supplied key is not a RSAPrivateKey instance");
        }
        this.pss = new PSSSigner(this.signer, this.contentDigest, this.mgfDigest, this.saltLength, this.trailer);
        this.pss.init(true, RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey));
    }

    protected void engineUpdate(byte by) throws SignatureException {
        this.pss.update(by);
    }

    protected void engineUpdate(byte[] byArray, int n, int n2) throws SignatureException {
        this.pss.update(byArray, n, n2);
    }

    protected byte[] engineSign() throws SignatureException {
        try {
            return this.pss.generateSignature();
        } catch (CryptoException cryptoException) {
            throw new SignatureException(cryptoException.getMessage());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        return this.pss.verifySignature(byArray);
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidAlgorithmParameterException {
        Digest digest;
        PSSParameterSpec pSSParameterSpec;
        if (algorithmParameterSpec instanceof PSSParameterSpec) {
            pSSParameterSpec = (PSSParameterSpec)algorithmParameterSpec;
            if (this.originalSpec != null && !DigestFactory.isSameDigest(this.originalSpec.getDigestAlgorithm(), pSSParameterSpec.getDigestAlgorithm())) {
                throw new InvalidAlgorithmParameterException("parameter must be using " + this.originalSpec.getDigestAlgorithm());
            }
            if (!pSSParameterSpec.getMGFAlgorithm().equalsIgnoreCase("MGF1") && !pSSParameterSpec.getMGFAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1.getId())) {
                throw new InvalidAlgorithmParameterException("unknown mask generation function specified");
            }
            if (!(pSSParameterSpec.getMGFParameters() instanceof MGF1ParameterSpec)) {
                throw new InvalidAlgorithmParameterException("unknown MGF parameters");
            }
            MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec)pSSParameterSpec.getMGFParameters();
            if (!DigestFactory.isSameDigest(mGF1ParameterSpec.getDigestAlgorithm(), pSSParameterSpec.getDigestAlgorithm())) {
                throw new InvalidAlgorithmParameterException("digest algorithm for MGF should be the same as for PSS parameters.");
            }
            digest = DigestFactory.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
            if (digest == null) {
                throw new InvalidAlgorithmParameterException("no match on MGF digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
            }
        } else {
            throw new InvalidAlgorithmParameterException("Only PSSParameterSpec supported");
        }
        this.engineParams = null;
        this.paramSpec = pSSParameterSpec;
        this.mgfDigest = digest;
        this.saltLength = this.paramSpec.getSaltLength();
        this.trailer = this.getTrailer(this.paramSpec.getTrailerField());
        this.setupContentDigest();
    }

    protected AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null && this.paramSpec != null) {
            try {
                this.engineParams = this.helper.createAlgorithmParameters("PSS");
                this.engineParams.init(this.paramSpec);
            } catch (Exception exception) {
                throw new RuntimeException(exception.toString());
            }
        }
        return this.engineParams;
    }

    protected void engineSetParameter(String string, Object object) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String string) {
        throw new UnsupportedOperationException("engineGetParameter unsupported");
    }

    private class NullPssDigest
    implements Digest {
        private ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        private Digest baseDigest;
        private boolean oddTime = true;

        public NullPssDigest(Digest digest) {
            this.baseDigest = digest;
        }

        public String getAlgorithmName() {
            return "NULL";
        }

        public int getDigestSize() {
            return this.baseDigest.getDigestSize();
        }

        public void update(byte by) {
            this.bOut.write(by);
        }

        public void update(byte[] byArray, int n, int n2) {
            this.bOut.write(byArray, n, n2);
        }

        public int doFinal(byte[] byArray, int n) {
            byte[] byArray2 = this.bOut.toByteArray();
            if (this.oddTime) {
                System.arraycopy(byArray2, 0, byArray, n, byArray2.length);
            } else {
                this.baseDigest.update(byArray2, 0, byArray2.length);
                this.baseDigest.doFinal(byArray, n);
            }
            this.reset();
            this.oddTime = !this.oddTime;
            return byArray2.length;
        }

        public void reset() {
            this.bOut.reset();
            this.baseDigest.reset();
        }

        public int getByteLength() {
            return 0;
        }
    }

    public static class PSSwithRSA
    extends PSSSignatureSpi {
        public PSSwithRSA() {
            super(new RSABlindedEngine(), null);
        }
    }

    public static class SHA1withRSA
    extends PSSSignatureSpi {
        public SHA1withRSA() {
            super(new RSABlindedEngine(), PSSParameterSpec.DEFAULT);
        }
    }

    public static class SHA224withRSA
    extends PSSSignatureSpi {
        public SHA224withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), 28, 1));
        }
    }

    public static class SHA256withRSA
    extends PSSSignatureSpi {
        public SHA256withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), 32, 1));
        }
    }

    public static class SHA384withRSA
    extends PSSSignatureSpi {
        public SHA384withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-384", "MGF1", new MGF1ParameterSpec("SHA-384"), 48, 1));
        }
    }

    public static class SHA3_224withRSA
    extends PSSSignatureSpi {
        public SHA3_224withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA3-224", "MGF1", new MGF1ParameterSpec("SHA3-224"), 28, 1));
        }
    }

    public static class SHA3_256withRSA
    extends PSSSignatureSpi {
        public SHA3_256withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA3-256", "MGF1", new MGF1ParameterSpec("SHA3-256"), 32, 1));
        }
    }

    public static class SHA3_384withRSA
    extends PSSSignatureSpi {
        public SHA3_384withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA3-384", "MGF1", new MGF1ParameterSpec("SHA3-384"), 48, 1));
        }
    }

    public static class SHA3_512withRSA
    extends PSSSignatureSpi {
        public SHA3_512withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA3-512", "MGF1", new MGF1ParameterSpec("SHA3-512"), 64, 1));
        }
    }

    public static class SHA512_224withRSA
    extends PSSSignatureSpi {
        public SHA512_224withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-512(224)", "MGF1", new MGF1ParameterSpec("SHA-512(224)"), 28, 1));
        }
    }

    public static class SHA512_256withRSA
    extends PSSSignatureSpi {
        public SHA512_256withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-512(256)", "MGF1", new MGF1ParameterSpec("SHA-512(256)"), 32, 1));
        }
    }

    public static class SHA512withRSA
    extends PSSSignatureSpi {
        public SHA512withRSA() {
            super(new RSABlindedEngine(), new PSSParameterSpec("SHA-512", "MGF1", new MGF1ParameterSpec("SHA-512"), 64, 1));
        }
    }

    public static class nonePSS
    extends PSSSignatureSpi {
        public nonePSS() {
            super(new RSABlindedEngine(), null, true);
        }
    }
}

