/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app.data;

import com.kitfox.svg.app.data.Handler;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class HandlerFactory
implements URLStreamHandlerFactory {
    static Handler handler = new Handler();

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("data".equals(protocol)) {
            return handler;
        }
        return null;
    }
}

