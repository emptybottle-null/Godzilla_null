/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.tls.DigestInputBuffer;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.SecurityParameters;
import org.bouncycastle.crypto.tls.ServerDHParams;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.SignerInputBuffer;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsDHKeyExchange;
import org.bouncycastle.crypto.tls.TlsDHUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsSigner;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.io.TeeInputStream;

public class TlsDHEKeyExchange
extends TlsDHKeyExchange {
    protected TlsSignerCredentials serverCredentials = null;

    public TlsDHEKeyExchange(int n, Vector vector, DHParameters dHParameters) {
        super(n, vector, dHParameters);
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
        this.processServerCertificate(tlsCredentials.getCertificate());
        this.serverCredentials = (TlsSignerCredentials)tlsCredentials;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        if (this.dhParameters == null) {
            throw new TlsFatalAlert(80);
        }
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, digestInputBuffer);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
        Digest digest = TlsUtils.createHash(signatureAndHashAlgorithm);
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        digest.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        digest.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        digestInputBuffer.updateDigest(digest);
        byte[] byArray = new byte[digest.getDigestSize()];
        digest.doFinal(byArray, 0);
        byte[] byArray2 = this.serverCredentials.generateCertificateSignature(byArray);
        DigitallySigned digitallySigned = new DigitallySigned(signatureAndHashAlgorithm, byArray2);
        digitallySigned.encode(digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = new SignerInputBuffer();
        TeeInputStream teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        ServerDHParams serverDHParams = ServerDHParams.parse(teeInputStream);
        DigitallySigned digitallySigned = this.parseSignature(inputStream);
        Signer signer = this.initVerifyer(this.tlsSigner, digitallySigned.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(signer);
        if (!signer.verifySignature(digitallySigned.getSignature())) {
            throw new TlsFatalAlert(51);
        }
        this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(serverDHParams.getPublicKey());
        this.dhParameters = this.validateDHParameters(this.dhAgreePublicKey.getParameters());
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer signer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        signer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signer;
    }
}

