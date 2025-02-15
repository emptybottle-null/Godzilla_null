/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.est.jcajce.SSLSocketFactoryCreator;

class SSLSocketFactoryCreatorBuilder {
    protected String tlsVersion = "TLS";
    protected Provider tlsProvider;
    protected KeyManager[] keyManagers;
    protected X509TrustManager[] trustManagers;
    protected SecureRandom secureRandom = new SecureRandom();

    public SSLSocketFactoryCreatorBuilder(X509TrustManager x509TrustManager) {
        if (x509TrustManager == null) {
            throw new NullPointerException("Trust managers can not be null");
        }
        this.trustManagers = new X509TrustManager[]{x509TrustManager};
    }

    public SSLSocketFactoryCreatorBuilder(X509TrustManager[] x509TrustManagerArray) {
        if (x509TrustManagerArray == null) {
            throw new NullPointerException("Trust managers can not be null");
        }
        this.trustManagers = x509TrustManagerArray;
    }

    public SSLSocketFactoryCreatorBuilder withTLSVersion(String string) {
        this.tlsVersion = string;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withProvider(String string) throws NoSuchProviderException {
        this.tlsProvider = Security.getProvider(string);
        if (this.tlsProvider == null) {
            throw new NoSuchProviderException("JSSE provider not found: " + string);
        }
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withProvider(Provider provider) {
        this.tlsProvider = provider;
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withKeyManager(KeyManager keyManager) {
        this.keyManagers = keyManager == null ? null : new KeyManager[]{keyManager};
        return this;
    }

    public SSLSocketFactoryCreatorBuilder withKeyManagers(KeyManager[] keyManagerArray) {
        this.keyManagers = keyManagerArray;
        return this;
    }

    public SSLSocketFactoryCreator build() {
        return new SSLSocketFactoryCreator(){

            public boolean isTrusted() {
                for (int i = 0; i != SSLSocketFactoryCreatorBuilder.this.trustManagers.length; ++i) {
                    X509TrustManager x509TrustManager = SSLSocketFactoryCreatorBuilder.this.trustManagers[i];
                    if (x509TrustManager.getAcceptedIssuers().length <= 0) continue;
                    return true;
                }
                return false;
            }

            public SSLSocketFactory createFactory() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
                SSLContext sSLContext = SSLSocketFactoryCreatorBuilder.this.tlsProvider != null ? SSLContext.getInstance(SSLSocketFactoryCreatorBuilder.this.tlsVersion, SSLSocketFactoryCreatorBuilder.this.tlsProvider) : SSLContext.getInstance(SSLSocketFactoryCreatorBuilder.this.tlsVersion);
                sSLContext.init(SSLSocketFactoryCreatorBuilder.this.keyManagers, SSLSocketFactoryCreatorBuilder.this.trustManagers, SSLSocketFactoryCreatorBuilder.this.secureRandom);
                return sSLContext.getSocketFactory();
            }
        };
    }
}

