/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.crypto.tls.AbstractTlsSigner;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsUtils;

public abstract class TlsDSASigner
extends AbstractTlsSigner {
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray) throws CryptoException {
        Signer signer = this.makeSigner(signatureAndHashAlgorithm, true, true, new ParametersWithRandom(asymmetricKeyParameter, this.context.getSecureRandom()));
        if (signatureAndHashAlgorithm == null) {
            signer.update(byArray, 16, 20);
        } else {
            signer.update(byArray, 0, byArray.length);
        }
        return signer.generateSignature();
    }

    public boolean verifyRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] byArray, AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray2) throws CryptoException {
        Signer signer = this.makeSigner(signatureAndHashAlgorithm, true, false, asymmetricKeyParameter);
        if (signatureAndHashAlgorithm == null) {
            signer.update(byArray2, 16, 20);
        } else {
            signer.update(byArray2, 0, byArray2.length);
        }
        return signer.verifySignature(byArray);
    }

    public Signer createSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.makeSigner(signatureAndHashAlgorithm, false, true, asymmetricKeyParameter);
    }

    public Signer createVerifyer(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.makeSigner(signatureAndHashAlgorithm, false, false, asymmetricKeyParameter);
    }

    protected CipherParameters makeInitParameters(boolean bl, CipherParameters cipherParameters) {
        return cipherParameters;
    }

    protected Signer makeSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, boolean bl, boolean bl2, CipherParameters cipherParameters) {
        if (signatureAndHashAlgorithm != null != TlsUtils.isTLSv12(this.context)) {
            throw new IllegalStateException();
        }
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() != this.getSignatureAlgorithm()) {
            throw new IllegalStateException();
        }
        short s = signatureAndHashAlgorithm == null ? (short)2 : (short)signatureAndHashAlgorithm.getHash();
        Digest digest = bl ? new NullDigest() : TlsUtils.createHash(s);
        DSADigestSigner dSADigestSigner = new DSADigestSigner(this.createDSAImpl(s), digest);
        dSADigestSigner.init(bl2, this.makeInitParameters(bl2, cipherParameters));
        return dSADigestSigner;
    }

    protected abstract short getSignatureAlgorithm();

    protected abstract DSA createDSAImpl(short var1);
}

