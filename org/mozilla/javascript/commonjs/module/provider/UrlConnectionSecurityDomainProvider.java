/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

public interface UrlConnectionSecurityDomainProvider {
    public Object getSecurityDomain(URLConnection var1);
}

