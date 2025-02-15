/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.xmss;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTSigner;
import org.bouncycastle.pqc.jcajce.interfaces.StateAwareSignature;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSMTPrivateKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.BCXMSSMTPublicKey;
import org.bouncycastle.pqc.jcajce.provider.xmss.DigestUtil;

public class XMSSMTSignatureSpi
extends Signature
implements StateAwareSignature {
    private Digest digest;
    private XMSSMTSigner signer;
    private ASN1ObjectIdentifier treeDigest;
    private SecureRandom random;

    protected XMSSMTSignatureSpi(String string) {
        super(string);
    }

    protected XMSSMTSignatureSpi(String string, Digest digest, XMSSMTSigner xMSSMTSigner) {
        super(string);
        this.digest = digest;
        this.signer = xMSSMTSigner;
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCXMSSMTPublicKey)) {
            throw new InvalidKeyException("unknown public key passed to XMSSMT");
        }
        CipherParameters cipherParameters = ((BCXMSSMTPublicKey)publicKey).getKeyParams();
        this.treeDigest = null;
        this.digest.reset();
        this.signer.init(false, cipherParameters);
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        this.engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters cipherParameters;
        if (privateKey instanceof BCXMSSMTPrivateKey) {
            cipherParameters = ((BCXMSSMTPrivateKey)privateKey).getKeyParams();
            this.treeDigest = ((BCXMSSMTPrivateKey)privateKey).getTreeDigestOID();
            if (this.random != null) {
                cipherParameters = new ParametersWithRandom(cipherParameters, this.random);
            }
        } else {
            throw new InvalidKeyException("unknown private key passed to XMSSMT");
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
        byte[] byArray = DigestUtil.getDigestResult(this.digest);
        try {
            byte[] byArray2 = this.signer.generateSignature(byArray);
            return byArray2;
        } catch (Exception exception) {
            if (exception instanceof IllegalStateException) {
                throw new SignatureException(exception.getMessage());
            }
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        byte[] byArray2 = DigestUtil.getDigestResult(this.digest);
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

    public PrivateKey getUpdatedPrivateKey() {
        if (this.treeDigest == null) {
            throw new IllegalStateException("signature object not in a signing state");
        }
        BCXMSSMTPrivateKey bCXMSSMTPrivateKey = new BCXMSSMTPrivateKey(this.treeDigest, (XMSSMTPrivateKeyParameters)this.signer.getUpdatedPrivateKey());
        this.treeDigest = null;
        return bCXMSSMTPrivateKey;
    }

    public static class withSha256
    extends XMSSMTSignatureSpi {
        public withSha256() {
            super("SHA256withXMSSMT", new SHA256Digest(), new XMSSMTSigner());
        }
    }

    public static class withSha512
    extends XMSSMTSignatureSpi {
        public withSha512() {
            super("SHA512withXMSSMT", new SHA512Digest(), new XMSSMTSigner());
        }
    }

    public static class withShake128
    extends XMSSMTSignatureSpi {
        public withShake128() {
            super("SHAKE128withXMSSMT", new SHAKEDigest(128), new XMSSMTSigner());
        }
    }

    public static class withShake256
    extends XMSSMTSignatureSpi {
        public withShake256() {
            super("SHAKE256withXMSSMT", new SHAKEDigest(256), new XMSSMTSigner());
        }
    }
}

