/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.agreement.DHStandardGroups;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.tls.AbstractTlsServer;
import org.bouncycastle.crypto.tls.DefaultTlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsEncryptionCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsPSKIdentityManager;
import org.bouncycastle.crypto.tls.TlsPSKKeyExchange;
import org.bouncycastle.crypto.tls.TlsUtils;

public class PSKTlsServer
extends AbstractTlsServer {
    protected TlsPSKIdentityManager pskIdentityManager;

    public PSKTlsServer(TlsPSKIdentityManager tlsPSKIdentityManager) {
        this(new DefaultTlsCipherFactory(), tlsPSKIdentityManager);
    }

    public PSKTlsServer(TlsCipherFactory tlsCipherFactory, TlsPSKIdentityManager tlsPSKIdentityManager) {
        super(tlsCipherFactory);
        this.pskIdentityManager = tlsPSKIdentityManager;
    }

    protected TlsEncryptionCredentials getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected DHParameters getDHParameters() {
        return DHStandardGroups.rfc7919_ffdhe2048;
    }

    protected int[] getCipherSuites() {
        return new int[]{49207, 49205, 178, 144};
    }

    public TlsCredentials getCredentials() throws IOException {
        int n = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (n) {
            case 13: 
            case 14: 
            case 24: {
                return null;
            }
            case 15: {
                return this.getRSAEncryptionCredentials();
            }
        }
        throw new TlsFatalAlert(80);
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        int n = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (n) {
            case 13: 
            case 14: 
            case 15: 
            case 24: {
                return this.createPSKKeyExchange(n);
            }
        }
        throw new TlsFatalAlert(80);
    }

    protected TlsKeyExchange createPSKKeyExchange(int n) {
        return new TlsPSKKeyExchange(n, this.supportedSignatureAlgorithms, null, this.pskIdentityManager, this.getDHParameters(), this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }
}

