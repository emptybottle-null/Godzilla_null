/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTHijacker;
import org.bouncycastle.est.ESTSourceConnectionListener;
import org.bouncycastle.est.HttpUtil;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ESTRequest {
    final String method;
    final URL url;
    HttpUtil.Headers headers = new HttpUtil.Headers();
    final byte[] data;
    final ESTHijacker hijacker;
    final ESTClient estClient;
    final ESTSourceConnectionListener listener;

    ESTRequest(String string, URL uRL, byte[] byArray, ESTHijacker eSTHijacker, ESTSourceConnectionListener eSTSourceConnectionListener, HttpUtil.Headers headers, ESTClient eSTClient) {
        this.method = string;
        this.url = uRL;
        this.data = byArray;
        this.hijacker = eSTHijacker;
        this.listener = eSTSourceConnectionListener;
        this.headers = headers;
        this.estClient = eSTClient;
    }

    public String getMethod() {
        return this.method;
    }

    public URL getURL() {
        return this.url;
    }

    public Map<String, String[]> getHeaders() {
        return (Map)this.headers.clone();
    }

    public ESTHijacker getHijacker() {
        return this.hijacker;
    }

    public ESTClient getClient() {
        return this.estClient;
    }

    public ESTSourceConnectionListener getListener() {
        return this.listener;
    }

    public void writeData(OutputStream outputStream) throws IOException {
        if (this.data != null) {
            outputStream.write(this.data);
        }
    }
}

