/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.net.URL;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTHijacker;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTSourceConnectionListener;
import org.bouncycastle.est.HttpUtil;
import org.bouncycastle.util.Arrays;

public class ESTRequestBuilder {
    private final String method;
    private URL url;
    private HttpUtil.Headers headers;
    ESTHijacker hijacker;
    ESTSourceConnectionListener listener;
    ESTClient client;
    private byte[] data;

    public ESTRequestBuilder(ESTRequest eSTRequest) {
        this.method = eSTRequest.method;
        this.url = eSTRequest.url;
        this.listener = eSTRequest.listener;
        this.data = eSTRequest.data;
        this.hijacker = eSTRequest.hijacker;
        this.headers = (HttpUtil.Headers)eSTRequest.headers.clone();
        this.client = eSTRequest.getClient();
    }

    public ESTRequestBuilder(String string, URL uRL) {
        this.method = string;
        this.url = uRL;
        this.headers = new HttpUtil.Headers();
    }

    public ESTRequestBuilder withConnectionListener(ESTSourceConnectionListener eSTSourceConnectionListener) {
        this.listener = eSTSourceConnectionListener;
        return this;
    }

    public ESTRequestBuilder withHijacker(ESTHijacker eSTHijacker) {
        this.hijacker = eSTHijacker;
        return this;
    }

    public ESTRequestBuilder withURL(URL uRL) {
        this.url = uRL;
        return this;
    }

    public ESTRequestBuilder withData(byte[] byArray) {
        this.data = Arrays.clone(byArray);
        return this;
    }

    public ESTRequestBuilder addHeader(String string, String string2) {
        this.headers.add(string, string2);
        return this;
    }

    public ESTRequestBuilder setHeader(String string, String string2) {
        this.headers.set(string, string2);
        return this;
    }

    public ESTRequestBuilder withClient(ESTClient eSTClient) {
        this.client = eSTClient;
        return this;
    }

    public ESTRequest build() {
        return new ESTRequest(this.method, this.url, this.data, this.hijacker, this.listener, this.headers, this.client);
    }
}

