/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import org.bouncycastle.cert.jcajce.CertHelper;

class ProviderCertHelper
extends CertHelper {
    private final Provider provider;

    ProviderCertHelper(Provider provider) {
        this.provider = provider;
    }

    protected CertificateFactory createCertificateFactory(String string) throws CertificateException {
        return CertificateFactory.getInstance(string, this.provider);
    }
}

