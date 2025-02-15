/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.WhirlpoolDigest;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.X931Signer;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;

public class X931SignatureSpi
extends SignatureSpi {
    private X931Signer signer;

    protected X931SignatureSpi(Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.signer = new X931Signer(asymmetricBlockCipher, digest);
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        RSAKeyParameters rSAKeyParameters = RSAUtil.generatePublicKeyParameter((RSAPublicKey)publicKey);
        this.signer.init(false, rSAKeyParameters);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        RSAKeyParameters rSAKeyParameters = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey);
        this.signer.init(true, rSAKeyParameters);
    }

    protected void engineUpdate(byte by) throws SignatureException {
        this.signer.update(by);
    }

    protected void engineUpdate(byte[] byArray, int n, int n2) throws SignatureException {
        this.signer.update(byArray, n, n2);
    }

    protected byte[] engineSign() throws SignatureException {
        try {
            byte[] byArray = this.signer.generateSignature();
            return byArray;
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        boolean bl = this.signer.verifySignature(byArray);
        return bl;
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

    public static class RIPEMD128WithRSAEncryption
    extends X931SignatureSpi {
        public RIPEMD128WithRSAEncryption() {
            super(new RIPEMD128Digest(), new RSABlindedEngine());
        }
    }

    public static class RIPEMD160WithRSAEncryption
    extends X931SignatureSpi {
        public RIPEMD160WithRSAEncryption() {
            super(new RIPEMD160Digest(), new RSABlindedEngine());
        }
    }

    public static class SHA1WithRSAEncryption
    extends X931SignatureSpi {
        public SHA1WithRSAEncryption() {
            super(DigestFactory.createSHA1(), new RSABlindedEngine());
        }
    }

    public static class SHA224WithRSAEncryption
    extends X931SignatureSpi {
        public SHA224WithRSAEncryption() {
            super(DigestFactory.createSHA224(), new RSABlindedEngine());
        }
    }

    public static class SHA256WithRSAEncryption
    extends X931SignatureSpi {
        public SHA256WithRSAEncryption() {
            super(DigestFactory.createSHA256(), new RSABlindedEngine());
        }
    }

    public static class SHA384WithRSAEncryption
    extends X931SignatureSpi {
        public SHA384WithRSAEncryption() {
            super(DigestFactory.createSHA384(), new RSABlindedEngine());
        }
    }

    public static class SHA512WithRSAEncryption
    extends X931SignatureSpi {
        public SHA512WithRSAEncryption() {
            super(DigestFactory.createSHA512(), new RSABlindedEngine());
        }
    }

    public static class SHA512_224WithRSAEncryption
    extends X931SignatureSpi {
        public SHA512_224WithRSAEncryption() {
            super(DigestFactory.createSHA512_224(), new RSABlindedEngine());
        }
    }

    public static class SHA512_256WithRSAEncryption
    extends X931SignatureSpi {
        public SHA512_256WithRSAEncryption() {
            super(DigestFactory.createSHA512_256(), new RSABlindedEngine());
        }
    }

    public static class WhirlpoolWithRSAEncryption
    extends X931SignatureSpi {
        public WhirlpoolWithRSAEncryption() {
            super(new WhirlpoolDigest(), new RSABlindedEngine());
        }
    }
}

