/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.crypto.tls.AbstractTlsClient;
import org.bouncycastle.crypto.tls.DefaultTlsCipherFactory;
import org.bouncycastle.crypto.tls.DefaultTlsSRPGroupVerifier;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsCipherFactory;
import org.bouncycastle.crypto.tls.TlsExtensionsUtils;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsSRPGroupVerifier;
import org.bouncycastle.crypto.tls.TlsSRPKeyExchange;
import org.bouncycastle.crypto.tls.TlsSRPUtils;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Arrays;

public class SRPTlsClient
extends AbstractTlsClient {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;

    public SRPTlsClient(byte[] byArray, byte[] byArray2) {
        this(new DefaultTlsCipherFactory(), new DefaultTlsSRPGroupVerifier(), byArray, byArray2);
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, byte[] byArray, byte[] byArray2) {
        this(tlsCipherFactory, new DefaultTlsSRPGroupVerifier(), byArray, byArray2);
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, TlsSRPGroupVerifier tlsSRPGroupVerifier, byte[] byArray, byte[] byArray2) {
        super(tlsCipherFactory);
        this.groupVerifier = tlsSRPGroupVerifier;
        this.identity = Arrays.clone(byArray);
        this.password = Arrays.clone(byArray2);
    }

    protected boolean requireSRPServerExtension() {
        return false;
    }

    public int[] getCipherSuites() {
        return new int[]{49182};
    }

    public Hashtable getClientExtensions() throws IOException {
        Hashtable hashtable = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(hashtable, this.identity);
        return hashtable;
    }

    public void processServerExtensions(Hashtable hashtable) throws IOException {
        if (!TlsUtils.hasExpectedEmptyExtensionData(hashtable, TlsSRPUtils.EXT_SRP, (short)47) && this.requireSRPServerExtension()) {
            throw new TlsFatalAlert(47);
        }
        super.processServerExtensions(hashtable);
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

    public TlsAuthentication getAuthentication() throws IOException {
        throw new TlsFatalAlert(80);
    }

    protected TlsKeyExchange createSRPKeyExchange(int n) {
        return new TlsSRPKeyExchange(n, this.supportedSignatureAlgorithms, this.groupVerifier, this.identity, this.password);
    }
}

