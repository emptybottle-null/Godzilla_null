/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.util.Set;
import javax.net.ssl.SSLSocketFactory;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.jcajce.ChannelBindingProvider;
import org.bouncycastle.est.jcajce.DefaultESTClient;
import org.bouncycastle.est.jcajce.DefaultESTClientSourceProvider;
import org.bouncycastle.est.jcajce.JsseHostnameAuthorizer;
import org.bouncycastle.est.jcajce.SSLSocketFactoryCreator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class DefaultESTHttpClientProvider
implements ESTClientProvider {
    private final JsseHostnameAuthorizer hostNameAuthorizer;
    private final SSLSocketFactoryCreator socketFactoryCreator;
    private final int timeout;
    private final ChannelBindingProvider bindingProvider;
    private final Set<String> cipherSuites;
    private final Long absoluteLimit;
    private final boolean filterCipherSuites;

    public DefaultESTHttpClientProvider(JsseHostnameAuthorizer jsseHostnameAuthorizer, SSLSocketFactoryCreator sSLSocketFactoryCreator, int n, ChannelBindingProvider channelBindingProvider, Set<String> set, Long l, boolean bl) {
        this.hostNameAuthorizer = jsseHostnameAuthorizer;
        this.socketFactoryCreator = sSLSocketFactoryCreator;
        this.timeout = n;
        this.bindingProvider = channelBindingProvider;
        this.cipherSuites = set;
        this.absoluteLimit = l;
        this.filterCipherSuites = bl;
    }

    @Override
    public ESTClient makeClient() throws ESTException {
        try {
            SSLSocketFactory sSLSocketFactory = this.socketFactoryCreator.createFactory();
            return new DefaultESTClient(new DefaultESTClientSourceProvider(sSLSocketFactory, this.hostNameAuthorizer, this.timeout, this.bindingProvider, this.cipherSuites, this.absoluteLimit, this.filterCipherSuites));
        } catch (Exception exception) {
            throw new ESTException(exception.getMessage(), exception.getCause());
        }
    }

    @Override
    public boolean isTrusted() {
        return this.socketFactoryCreator.isTrusted();
    }
}

