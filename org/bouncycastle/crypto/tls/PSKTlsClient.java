/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.AbstractTlsClient;
import org.bouncycastle.crypto.tls.DefaultTlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsPSKIdentity;
import org.bouncycastle.crypto.tls.TlsPSKKeyExchange;
import org.bouncycastle.crypto.tls.TlsUtils;

public class PSKTlsClient
extends AbstractTlsClient {
    protected TlsPSKIdentity pskIdentity;

    public PSKTlsClient(TlsPSKIdentity tlsPSKIdentity) {
        this(new DefaultTlsCipherFactory(), tlsPSKIdentity);
    }

    public PSKTlsClient(TlsCipherFactory tlsCipherFactory, TlsPSKIdentity tlsPSKIdentity) {
        super(tlsCipherFactory);
        this.pskIdentity = tlsPSKIdentity;
    }

    public int[] getCipherSuites() {
        return new int[]{49207, 49205, 178, 144};
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

    public TlsAuthentication getAuthentication() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected TlsKeyExchange createPSKKeyExchange(int n) {
        return new TlsPSKKeyExchange(n, this.supportedSignatureAlgorithms, this.pskIdentity, null, null, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }
}

