/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.agreement.DHStandardGroups;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.tls.AbstractTlsServer;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsDHEKeyExchange;
import org.bouncycastle.crypto.tls.TlsDHKeyExchange;
import org.bouncycastle.crypto.tls.TlsECDHEKeyExchange;
import org.bouncycastle.crypto.tls.TlsECDHKeyExchange;
import org.bouncycastle.crypto.tls.TlsEncryptionCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsRSAKeyExchange;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;

public abstract class DefaultTlsServer
extends AbstractTlsServer {
    public DefaultTlsServer() {
    }

    public DefaultTlsServer(TlsCipherFactory tlsCipherFactory) {
        super(tlsCipherFactory);
    }

    protected TlsSignerCredentials getDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected TlsSignerCredentials getECDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected TlsEncryptionCredentials getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected TlsSignerCredentials getRSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected DHParameters getDHParameters() {
        return DHStandardGroups.rfc7919_ffdhe2048;
    }

    protected int[] getCipherSuites() {
        return new int[]{49200, 49199, 49192, 49191, 49172, 49171, 159, 158, 107, 103, 57, 51, 157, 156, 61, 60, 53, 47};
    }

    public TlsCredentials getCredentials() throws IOException {
        int n = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (n) {
            case 3: {
                return this.getDSASignerCredentials();
            }
            case 11: 
            case 20: {
                return null;
            }
            case 17: {
                return this.getECDSASignerCredentials();
            }
            case 5: 
            case 19: {
                return this.getRSASignerCredentials();
            }
            case 1: {
                return this.getRSAEncryptionCredentials();
            }
        }
        throw new TlsFatalAlert(80);
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        int n = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (n) {
            case 7: 
            case 9: 
            case 11: {
                return this.createDHKeyExchange(n);
            }
            case 3: 
            case 5: {
                return this.createDHEKeyExchange(n);
            }
            case 16: 
            case 18: 
            case 20: {
                return this.createECDHKeyExchange(n);
            }
            case 17: 
            case 19: {
                return this.createECDHEKeyExchange(n);
            }
            case 1: {
                return this.createRSAKeyExchange();
            }
        }
        throw new TlsFatalAlert(80);
    }

    protected TlsKeyExchange createDHKeyExchange(int n) {
        return new TlsDHKeyExchange(n, this.supportedSignatureAlgorithms, this.getDHParameters());
    }

    protected TlsKeyExchange createDHEKeyExchange(int n) {
        return new TlsDHEKeyExchange(n, this.supportedSignatureAlgorithms, this.getDHParameters());
    }

    protected TlsKeyExchange createECDHKeyExchange(int n) {
        return new TlsECDHKeyExchange(n, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    protected TlsKeyExchange createECDHEKeyExchange(int n) {
        return new TlsECDHEKeyExchange(n, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    protected TlsKeyExchange createRSAKeyExchange() {
        return new TlsRSAKeyExchange(this.supportedSignatureAlgorithms);
    }
}

