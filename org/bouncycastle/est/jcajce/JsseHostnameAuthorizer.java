/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.io.IOException;
import javax.net.ssl.SSLSession;

public interface JsseHostnameAuthorizer {
    public boolean verified(String var1, SSLSession var2) throws IOException;
}

