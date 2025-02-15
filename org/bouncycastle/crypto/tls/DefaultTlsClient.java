/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.AbstractTlsClient;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsDHEKeyExchange;
import org.bouncycastle.crypto.tls.TlsDHKeyExchange;
import org.bouncycastle.crypto.tls.TlsECDHEKeyExchange;
import org.bouncycastle.crypto.tls.TlsECDHKeyExchange;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsRSAKeyExchange;
import org.bouncycastle.crypto.tls.TlsUtils;

public abstract class DefaultTlsClient
extends AbstractTlsClient {
    public DefaultTlsClient() {
    }

    public DefaultTlsClient(TlsCipherFactory tlsCipherFactory) {
        super(tlsCipherFactory);
    }

    public int[] getCipherSuites() {
        return new int[]{49195, 49187, 49161, 49199, 49191, 49171, 162, 64, 50, 158, 103, 51, 156, 60, 47};
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
        return new TlsDHKeyExchange(n, this.supportedSignatureAlgorithms, null);
    }

    protected TlsKeyExchange createDHEKeyExchange(int n) {
        return new TlsDHEKeyExchange(n, this.supportedSignatureAlgorithms, null);
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

