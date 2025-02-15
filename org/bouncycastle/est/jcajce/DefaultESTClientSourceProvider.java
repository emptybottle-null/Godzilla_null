/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.bouncycastle.est.ESTClientSourceProvider;
import org.bouncycastle.est.Source;
import org.bouncycastle.est.jcajce.ChannelBindingProvider;
import org.bouncycastle.est.jcajce.JsseHostnameAuthorizer;
import org.bouncycastle.est.jcajce.LimitedSSLSocketSource;
import org.bouncycastle.util.Strings;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class DefaultESTClientSourceProvider
implements ESTClientSourceProvider {
    private final SSLSocketFactory sslSocketFactory;
    private final JsseHostnameAuthorizer hostNameAuthorizer;
    private final int timeout;
    private final ChannelBindingProvider bindingProvider;
    private final Set<String> cipherSuites;
    private final Long absoluteLimit;
    private final boolean filterSupportedSuites;

    public DefaultESTClientSourceProvider(SSLSocketFactory sSLSocketFactory, JsseHostnameAuthorizer jsseHostnameAuthorizer, int n, ChannelBindingProvider channelBindingProvider, Set<String> set, Long l, boolean bl) throws GeneralSecurityException {
        this.sslSocketFactory = sSLSocketFactory;
        this.hostNameAuthorizer = jsseHostnameAuthorizer;
        this.timeout = n;
        this.bindingProvider = channelBindingProvider;
        this.cipherSuites = set;
        this.absoluteLimit = l;
        this.filterSupportedSuites = bl;
    }

    @Override
    public Source makeSource(String string, int n) throws IOException {
        Object object;
        SSLSocket sSLSocket = (SSLSocket)this.sslSocketFactory.createSocket(string, n);
        sSLSocket.setSoTimeout(this.timeout);
        if (this.cipherSuites != null && !this.cipherSuites.isEmpty()) {
            if (this.filterSupportedSuites) {
                object = new HashSet();
                String[] stringArray = sSLSocket.getSupportedCipherSuites();
                for (int i = 0; i != stringArray.length; ++i) {
                    ((HashSet)object).add(stringArray[i]);
                }
                ArrayList<String> arrayList = new ArrayList<String>();
                for (String string2 : this.cipherSuites) {
                    if (!((HashSet)object).contains(string2)) continue;
                    arrayList.add(string2);
                }
                if (arrayList.isEmpty()) {
                    throw new IllegalStateException("No supplied cipher suite is supported by the provider.");
                }
                sSLSocket.setEnabledCipherSuites(arrayList.toArray(new String[arrayList.size()]));
            } else {
                sSLSocket.setEnabledCipherSuites(this.cipherSuites.toArray(new String[this.cipherSuites.size()]));
            }
        }
        sSLSocket.startHandshake();
        if (this.hostNameAuthorizer != null && !this.hostNameAuthorizer.verified(string, sSLSocket.getSession())) {
            throw new IOException("Host name could not be verified.");
        }
        object = Strings.toLowerCase(sSLSocket.getSession().getCipherSuite());
        if (((String)object).contains("_des_") || ((String)object).contains("_des40_") || ((String)object).contains("_3des_")) {
            throw new IOException("EST clients must not use DES ciphers");
        }
        if (Strings.toLowerCase(sSLSocket.getSession().getCipherSuite()).contains("null")) {
            throw new IOException("EST clients must not use NULL ciphers");
        }
        if (Strings.toLowerCase(sSLSocket.getSession().getCipherSuite()).contains("anon")) {
            throw new IOException("EST clients must not use anon ciphers");
        }
        if (Strings.toLowerCase(sSLSocket.getSession().getCipherSuite()).contains("export")) {
            throw new IOException("EST clients must not use export ciphers");
        }
        if (sSLSocket.getSession().getProtocol().equalsIgnoreCase("tlsv1")) {
            try {
                sSLSocket.close();
            } catch (Exception exception) {
                // empty catch block
            }
            throw new IOException("EST clients must not use TLSv1");
        }
        if (this.hostNameAuthorizer != null && !this.hostNameAuthorizer.verified(string, sSLSocket.getSession())) {
            throw new IOException("Hostname was not verified: " + string);
        }
        return new LimitedSSLSocketSource(sSLSocket, this.bindingProvider, this.absoluteLimit);
    }
}

