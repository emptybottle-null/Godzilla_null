/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.DigitallySigned;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsUtils;

public abstract class AbstractTlsKeyExchange
implements TlsKeyExchange {
    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;
    protected TlsContext context;

    protected AbstractTlsKeyExchange(int n, Vector vector) {
        this.keyExchange = n;
        this.supportedSignatureAlgorithms = vector;
    }

    protected DigitallySigned parseSignature(InputStream inputStream) throws IOException {
        DigitallySigned digitallySigned = DigitallySigned.parse(this.context, inputStream);
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = digitallySigned.getAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            TlsUtils.verifySupportedSignatureAlgorithm(this.supportedSignatureAlgorithms, signatureAndHashAlgorithm);
        }
        return digitallySigned;
    }

    public void init(TlsContext tlsContext) {
        block8: {
            ProtocolVersion protocolVersion;
            block7: {
                this.context = tlsContext;
                protocolVersion = tlsContext.getClientVersion();
                if (!TlsUtils.isSignatureAlgorithmsExtensionAllowed(protocolVersion)) break block7;
                if (this.supportedSignatureAlgorithms != null) break block8;
                switch (this.keyExchange) {
                    case 3: 
                    case 7: 
                    case 22: {
                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultDSSSignatureAlgorithms();
                        break block8;
                    }
                    case 16: 
                    case 17: {
                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultECDSASignatureAlgorithms();
                        break block8;
                    }
                    case 1: 
                    case 5: 
                    case 9: 
                    case 15: 
                    case 18: 
                    case 19: 
                    case 23: {
                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultRSASignatureAlgorithms();
                        break block8;
                    }
                    case 13: 
                    case 14: 
                    case 21: 
                    case 24: {
                        break block8;
                    }
                    default: {
                        throw new IllegalStateException("unsupported key exchange algorithm");
                    }
                }
            }
            if (this.supportedSignatureAlgorithms != null) {
                throw new IllegalStateException("supported_signature_algorithms not allowed for " + protocolVersion);
            }
        }
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.supportedSignatureAlgorithms == null) {
            // empty if block
        }
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        this.processServerCertificate(tlsCredentials.getCertificate());
    }

    public boolean requiresServerKeyExchange() {
        return false;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        if (this.requiresServerKeyExchange()) {
            throw new TlsFatalAlert(80);
        }
        return null;
    }

    public void skipServerKeyExchange() throws IOException {
        if (this.requiresServerKeyExchange()) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        if (!this.requiresServerKeyExchange()) {
            throw new TlsFatalAlert(10);
        }
    }

    public void skipClientCredentials() throws IOException {
    }

    public void processClientCertificate(Certificate certificate) throws IOException {
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        throw new TlsFatalAlert(80);
    }
}

