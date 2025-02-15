/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.crypto.tls.AbstractTlsServer;
import org.bouncycastle.crypto.tls.DefaultTlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsSRPIdentityManager;
import org.bouncycastle.crypto.tls.TlsSRPKeyExchange;
import org.bouncycastle.crypto.tls.TlsSRPLoginParameters;
import org.bouncycastle.crypto.tls.TlsSRPUtils;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;
import org.bouncycastle.crypto.tls.TlsUtils;

public class SRPTlsServer
extends AbstractTlsServer {
    protected TlsSRPIdentityManager srpIdentityManager;
    protected byte[] srpIdentity = null;
    protected TlsSRPLoginParameters loginParameters = null;

    public SRPTlsServer(TlsSRPIdentityManager tlsSRPIdentityManager) {
        this(new DefaultTlsCipherFactory(), tlsSRPIdentityManager);
    }

    public SRPTlsServer(TlsCipherFactory tlsCipherFactory, TlsSRPIdentityManager tlsSRPIdentityManager) {
        super(tlsCipherFactory);
        this.srpIdentityManager = tlsSRPIdentityManager;
    }

    protected TlsSignerCredentials getDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected TlsSignerCredentials getRSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected int[] getCipherSuites() {
        return new int[]{49186, 49183, 49185, 49182, 49184, 49181};
    }

    public void processClientExtensions(Hashtable hashtable) throws IOException {
        super.processClientExtensions(hashtable);
        this.srpIdentity = TlsSRPUtils.getSRPExtension(hashtable);
    }

    public int getSelectedCipherSuite() throws IOException {
        int n = super.getSelectedCipherSuite();
        if (TlsSRPUtils.isSRPCipherSuite(n)) {
            if (this.srpIdentity != null) {
                this.loginParameters = this.srpIdentityManager.getLoginParameters(this.srpIdentity);
            }
            if (this.loginParameters == null) {
                throw new TlsFatalAlert(115);
            }
        }
        return n;
    }

    public TlsCredentials getCredentials() throws IOException {
        int n = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (n) {
            case 21: {
                return null;
            }
            case 22: {
                return this.getDSASignerCredentials();
            }
            case 23: {
                return this.getRSASignerCredentials();
            }
        }
        throw new TlsFatalAlert(80);
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        int n = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (n) {
            case 21: 
            case 22: 
            case 23: {
                return this.createSRPKeyExchange(n);
            }
        }
        throw new TlsFatalAlert(80);
    }

    protected TlsKeyExchange createSRPKeyExchange(int n) {
        return new TlsSRPKeyExchange(n, this.supportedSignatureAlgorithms, this.srpIdentity, this.loginParameters);
    }
}

