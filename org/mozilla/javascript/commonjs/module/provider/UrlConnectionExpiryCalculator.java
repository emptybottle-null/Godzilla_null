/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

public interface UrlConnectionExpiryCalculator {
    public long calculateExpiry(URLConnection var1);
}

