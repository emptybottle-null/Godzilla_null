/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.socksServer;

import com.httpProxy.server.request.HttpRequest;
import com.httpProxy.server.response.HttpResponse;

public interface HttpRequestHandle {
    public HttpResponse sendHttpRequest(HttpRequest var1);
}

