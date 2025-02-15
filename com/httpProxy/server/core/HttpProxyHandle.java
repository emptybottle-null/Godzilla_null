/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.httpProxy.server.core;

import com.httpProxy.server.request.HttpRequest;
import java.net.Socket;

public interface HttpProxyHandle {
    public void handler(Socket var1, HttpRequest var2) throws Exception;
}

