/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.GenericSigner;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.crypto.tls.AbstractTlsSigner;
import org.bouncycastle.crypto.tls.CombinedHash;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsUtils;

public class TlsRSASigner
extends AbstractTlsSigner {
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray) throws CryptoException {
        Signer signer = this.makeSigner(signatureAndHashAlgorithm, true, true, new ParametersWithRandom(asymmetricKeyParameter, this.context.getSecureRandom()));
        signer.update(byArray, 0, byArray.length);
        return signer.generateSignature();
    }

    public boolean verifyRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] byArray, AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray2) throws CryptoException {
        Signer signer = this.makeSigner(signatureAndHashAlgorithm, true, false, asymmetricKeyParameter);
        signer.update(byArray2, 0, byArray2.length);
        return signer.verifySignature(byArray);
    }

    public Signer createSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.makeSigner(signatureAndHashAlgorithm, false, true, new ParametersWithRandom(asymmetricKeyParameter, this.context.getSecureRandom()));
    }

    public Signer createVerifyer(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.makeSigner(signatureAndHashAlgorithm, false, false, asymmetricKeyParameter);
    }

    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof RSAKeyParameters && !asymmetricKeyParameter.isPrivate();
    }

    protected Signer makeSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, boolean bl, boolean bl2, CipherParameters cipherParameters) {
        if (signatureAndHashAlgorithm != null != TlsUtils.isTLSv12(this.context)) {
            throw new IllegalStateException();
        }
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() != 1) {
            throw new IllegalStateException();
        }
        Digest digest = bl ? new NullDigest() : (signatureAndHashAlgorithm == null ? new CombinedHash() : TlsUtils.createHash(signatureAndHashAlgorithm.getHash()));
        Signer signer = signatureAndHashAlgorithm != null ? new RSADigestSigner(digest, TlsUtils.getOIDForHashAlgorithm(signatureAndHashAlgorithm.getHash())) : new GenericSigner(this.createRSAImpl(), digest);
        signer.init(bl2, cipherParameters);
        return signer;
    }

    protected AsymmetricBlockCipher createRSAImpl() {
        return new PKCS1Encoding(new RSABlindedEngine());
    }
}

