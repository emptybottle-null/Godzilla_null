/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import javax.net.ssl.SSLSocketFactory;

public interface SSLSocketFactoryCreator {
    public SSLSocketFactory createFactory() throws Exception;

    public boolean isTrusted();
}

