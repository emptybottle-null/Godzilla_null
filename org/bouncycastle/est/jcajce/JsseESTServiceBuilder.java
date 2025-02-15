/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.net.Socket;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.est.ESTClientProvider;
import org.bouncycastle.est.ESTService;
import org.bouncycastle.est.ESTServiceBuilder;
import org.bouncycastle.est.jcajce.ChannelBindingProvider;
import org.bouncycastle.est.jcajce.DefaultESTHttpClientProvider;
import org.bouncycastle.est.jcajce.JcaJceUtils;
import org.bouncycastle.est.jcajce.JsseDefaultHostnameAuthorizer;
import org.bouncycastle.est.jcajce.JsseHostnameAuthorizer;
import org.bouncycastle.est.jcajce.SSLSocketFactoryCreator;
import org.bouncycastle.est.jcajce.SSLSocketFactoryCreatorBuilder;

public class JsseESTServiceBuilder
extends ESTServiceBuilder {
    protected SSLSocketFactoryCreator socketFactoryCreator;
    protected JsseHostnameAuthorizer hostNameAuthorizer = new JsseDefaultHostnameAuthorizer(null);
    protected int timeoutMillis = 0;
    protected ChannelBindingProvider bindingProvider;
    protected Set<String> supportedSuites = new HashSet<String>();
    protected Long absoluteLimit;
    protected SSLSocketFactoryCreatorBuilder sslSocketFactoryCreatorBuilder;
    protected boolean filterCipherSuites = true;

    public JsseESTServiceBuilder(String string, SSLSocketFactoryCreator sSLSocketFactoryCreator) {
        super(string);
        if (sSLSocketFactoryCreator == null) {
            throw new NullPointerException("No socket factory creator.");
        }
        this.socketFactoryCreator = sSLSocketFactoryCreator;
    }

    public JsseESTServiceBuilder(String string) {
        super(string);
        this.sslSocketFactoryCreatorBuilder = new SSLSocketFactoryCreatorBuilder(JcaJceUtils.getTrustAllTrustManager());
    }

    public JsseESTServiceBuilder(String string, X509TrustManager x509TrustManager) {
        super(string);
        this.sslSocketFactoryCreatorBuilder = new SSLSocketFactoryCreatorBuilder(x509TrustManager);
    }

    public JsseESTServiceBuilder(String string, X509TrustManager[] x509TrustManagerArray) {
        super(string);
        this.sslSocketFactoryCreatorBuilder = new SSLSocketFactoryCreatorBuilder(x509TrustManagerArray);
    }

    public JsseESTServiceBuilder withHostNameAuthorizer(JsseHostnameAuthorizer jsseHostnameAuthorizer) {
        this.hostNameAuthorizer = jsseHostnameAuthorizer;
        return this;
    }

    public JsseESTServiceBuilder withClientProvider(ESTClientProvider eSTClientProvider) {
        this.clientProvider = eSTClientProvider;
        return this;
    }

    public JsseESTServiceBuilder withTimeout(int n) {
        this.timeoutMillis = n;
        return this;
    }

    public JsseESTServiceBuilder withReadLimit(long l) {
        this.absoluteLimit = l;
        return this;
    }

    public JsseESTServiceBuilder withChannelBindingProvider(ChannelBindingProvider channelBindingProvider) {
        this.bindingProvider = channelBindingProvider;
        return this;
    }

    public JsseESTServiceBuilder addCipherSuites(String string) {
        this.supportedSuites.add(string);
        return this;
    }

    public JsseESTServiceBuilder addCipherSuites(String[] stringArray) {
        this.supportedSuites.addAll(Arrays.asList(stringArray));
        return this;
    }

    public JsseESTServiceBuilder withTLSVersion(String string) {
        if (this.socketFactoryCreator != null) {
            throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
        }
        this.sslSocketFactoryCreatorBuilder.withTLSVersion(string);
        return this;
    }

    public JsseESTServiceBuilder withSecureRandom(SecureRandom secureRandom) {
        if (this.socketFactoryCreator != null) {
            throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
        }
        this.sslSocketFactoryCreatorBuilder.withSecureRandom(secureRandom);
        return this;
    }

    public JsseESTServiceBuilder withProvider(String string) throws NoSuchProviderException {
        if (this.socketFactoryCreator != null) {
            throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
        }
        this.sslSocketFactoryCreatorBuilder.withProvider(string);
        return this;
    }

    public JsseESTServiceBuilder withProvider(Provider provider) {
        if (this.socketFactoryCreator != null) {
            throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
        }
        this.sslSocketFactoryCreatorBuilder.withProvider(provider);
        return this;
    }

    public JsseESTServiceBuilder withKeyManager(KeyManager keyManager) {
        if (this.socketFactoryCreator != null) {
            throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
        }
        this.sslSocketFactoryCreatorBuilder.withKeyManager(keyManager);
        return this;
    }

    public JsseESTServiceBuilder withKeyManagers(KeyManager[] keyManagerArray) {
        if (this.socketFactoryCreator != null) {
            throw new IllegalStateException("Socket Factory Creator was defined in the constructor.");
        }
        this.sslSocketFactoryCreatorBuilder.withKeyManagers(keyManagerArray);
        return this;
    }

    public JsseESTServiceBuilder withFilterCipherSuites(boolean bl) {
        this.filterCipherSuites = bl;
        return this;
    }

    public ESTService build() {
        if (this.bindingProvider == null) {
            this.bindingProvider = new ChannelBindingProvider(){

                public boolean canAccessChannelBinding(Socket socket) {
                    return false;
                }

                public byte[] getChannelBinding(Socket socket, String string) {
                    return null;
                }
            };
        }
        if (this.socketFactoryCreator == null) {
            this.socketFactoryCreator = this.sslSocketFactoryCreatorBuilder.build();
        }
        if (this.clientProvider == null) {
            this.clientProvider = new DefaultESTHttpClientProvider(this.hostNameAuthorizer, this.socketFactoryCreator, this.timeoutMillis, this.bindingProvider, this.supportedSuites, this.absoluteLimit, this.filterCipherSuites);
        }
        return super.build();
    }
}

